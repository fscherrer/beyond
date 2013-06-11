package br.com.einsteinlimeira.beyond.mobile.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.einsteinlimeira.beyond.mobile.Constantes;
import br.com.einsteinlimeira.beyond.mobile.database.DataBaseHelper;
import br.com.einsteinlimeira.beyond.mobile.model.EventoDetalhadoDTO;
import br.com.einsteinlimeira.beyond.mobile.model.EventoSimplificadoDTO;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;
import br.com.einsteinlimeira.beyond.model.dto.EventoDTO;

/**
 * DAO (<b>D</b>ata <b>A</b>cess <b>O</b>bject) para manipulação de Evento.
 */
public class EventoDAO {

  /**
   * Insere o Evento na base local.
   * 
   * @param evento
   *   Objeto representando uma Evento.
   * @param context
   *   Contexto.
   *   
   * @return
   *   <code>True</code> se o Evento foi inserido.
   */
  public boolean inserir(EventoDTO evento, Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

    ContentValues contentValues = new ContentValues();
    contentValues.put("_id", evento.getId());
    contentValues.put("nome", evento.getNome());
    contentValues.put("valor", evento.getValor());
    contentValues.put("datahora", DataBaseHelper.ISO_8601_FORMAT.format(evento.getDataHora()));
    contentValues.put("casaid", evento.getIdCasa());

    // se o Evento for inserido com sucesso, insere os registros em eventobanda
    if (writableDatabase.insert("evento", null, contentValues) > 0) {
      for (int idBanda : evento.getIdsBandas()) {
        contentValues = new ContentValues();

        contentValues.put("eventoid", evento.getId());
        contentValues.put("bandaid", idBanda);

        if (writableDatabase.insert("eventobanda", null, contentValues) <= 0) {
          writableDatabase.close();
          return false;
        }
      }

      writableDatabase.close();
      return true;
    }

    writableDatabase.close();
    return false;
  }

  /**
   * Remove todos os Eventos presentes na base.
   * 
   * @param context
   *   Contexto.
   */
  public void removerTodos(Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

    writableDatabase.delete("eventobanda", null, null);
    writableDatabase.delete("evento", null, null);

    writableDatabase.close();
  }

  /**
   * Lista os Eventos presentes na base.
   * 
   * @param context
   *   Context.
   * @param idsCasas
   *   IDs das Casas das quais deseja obter os Eventos (filtro).
   * @param idsBandas
   *   IDs das Bandas das quais deseja obter os Eventos (filtro).
   *   
   * @return
   *   Eventos presentes na base.
   */
  public List<EventoSimplificadoDTO> listar(Context context, int[] idsCasas, int[] idsBandas) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();
    
    // Log.w(Constantes.TAG, "casas: " + (idsCasas == null ? "null" : idsCasas.length));
    // Log.w(Constantes.TAG, "bandas: " + (idsBandas == null ? "null" : idsBandas.length));

    StringBuilder where = new StringBuilder();

    // filtro casas
    if (idsCasas != null && idsCasas.length > 0) {
      where.append(DAOUtils.getFiltroQuery("evento.casaid", idsCasas));
    }

    // filtro bandas (especial, já que um evento pode ter várias bandas)
    if (idsBandas != null && idsBandas.length > 0) {
      if (where.length() > 0) {
        where.append(" and ");
      }

      // não posso simplesmente filtrar eventobanda.bandaid pois um evento pode ter várias bandas
      // no caso de filtrado "Stratovários", as demais bandas de um evento em que "Stratovários"
      // participa devem aparecer junto
      where.append("exists (select 0 from eventobanda where eventoid = evento._id and ");
      where.append(DAOUtils.getFiltroQuery("eventobanda.bandaid", idsBandas));
      where.append(")");
    }

    if (where.length() > 0) {
      where.insert(0, " where ");
    }

    String query = "" +
        " select distinct " +
        "   evento._id, " +
        "   evento.nome, " +
        "   evento.dataHora " +
        " from " +
        "   evento " +
        "     left join eventobanda " +
        "       on eventobanda.eventoid = evento._id " +
        " " + where.toString() +
        " order by " +
        "   datahora ";
    
    // DEBUG
    // Log.i(Constantes.TAG, query);

    Cursor cursor = readableDatabase.rawQuery(query, null);

    List<EventoSimplificadoDTO> dtos = new ArrayList<EventoSimplificadoDTO>();

    EventoSimplificadoDTO dto = null;

    int idColumnIndex = cursor.getColumnIndex("_id");
    int nomeColumnIndex = cursor.getColumnIndex("nome");
    int datahoraColumnIndex = cursor.getColumnIndex("datahora");

    while (cursor.moveToNext()) {
      try {
        dto = new EventoSimplificadoDTO(cursor.getInt(idColumnIndex),
            cursor.getString(nomeColumnIndex),
            DataBaseHelper.ISO_8601_FORMAT.parse(cursor.getString(datahoraColumnIndex)));

        dtos.add(dto);
      }
      catch (ParseException e) {
        Log.e(Constantes.TAG, "Falha ao parsear datahora: " +
            cursor.getString(datahoraColumnIndex) + " do Evento de id: " +
            cursor.getInt(idColumnIndex)
            + ". Evento ignorado e NÃO adicionado à lista de Eventos", e);
        continue;
      }
    }

    readableDatabase.close();

    return dtos;
  }

  /**
   * Retorna os detalhes do Evento.
   * 
   * @param context
   *   Context.
   *   
   * @return
   *   Detalhes do Evento.
   */
  public EventoDetalhadoDTO getDetalhes(int id, Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

    Cursor cursor =
        readableDatabase.rawQuery("" +
            " select " +
            "   evento.*, " +
            "   eventobanda.bandaid," +
            "   cidade.nome as cidade, " +
            "   cidade.siglauf " +
            " from " +
            "   evento " +
            "     left join eventobanda " +
            "       on eventobanda.eventoid = " + id +
            "     join casa " +
            "       on casa._id = evento.casaid " +
            "     join cidade " +
            "       on cidade._id = casa.cidadeid " +
            " where " +
            "   evento._id = " + id, null);

    EventoDetalhadoDTO dto = null;

    int idColumnIndex = cursor.getColumnIndex("_id");
    int nomeColumnIndex = cursor.getColumnIndex("nome");
    int valorColumnIndex = cursor.getColumnIndex("valor");
    int datahoraColumnIndex = cursor.getColumnIndex("datahora");
    int casaidColumnIndex = cursor.getColumnIndex("casaid");
    int bandaidColumnIndex = cursor.getColumnIndex("bandaid");
    int cidadeColumnIndex = cursor.getColumnIndex("cidade");
    int siglaufColumnIndex = cursor.getColumnIndex("siglauf");

    CasaDAO casaDAO = new CasaDAO();
    BandaDAO bandaDAO = new BandaDAO();

    if (cursor.moveToNext()) {
      try {
        CasaDTO casaDTO = casaDAO.getPeloId(context, cursor.getInt(casaidColumnIndex));

        List<BandaDTO> bandas = new ArrayList<BandaDTO>();

        dto = new EventoDetalhadoDTO(id,
            cursor.getString(nomeColumnIndex),
            cursor.getDouble(valorColumnIndex),
            DataBaseHelper.ISO_8601_FORMAT.parse(cursor.getString(datahoraColumnIndex)),
            bandas,
            casaDTO,
            cursor.getString(cidadeColumnIndex),
            cursor.getString(siglaufColumnIndex));

        do {
          if (!cursor.isNull(bandaidColumnIndex)) {
            bandas.add(bandaDAO.getPeloId(context, cursor.getInt(bandaidColumnIndex)));
          }
        } while (cursor.moveToNext());
      }
      catch (ParseException e) {
        Log.e(Constantes.TAG, "Falha ao parsear datahora: " +
            cursor.getString(datahoraColumnIndex) + " do Evento de id: " +
            cursor.getInt(idColumnIndex)
            + ". Detalhes do Evento NÃO retornados", e);
        readableDatabase.close();
        return null;
      }
    }

    readableDatabase.close();
    return dto;
  }
}
