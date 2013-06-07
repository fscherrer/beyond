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
   *   
   * @return
   *   Eventos presentes na base.
   */
  public List<EventoSimplificadoDTO> listar(Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

    Cursor cursor = readableDatabase.query("evento", new String[] { "_id", "nome",
        "datahora" }, null, null, null, null, "datahora");

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
        readableDatabase.query("evento", null, "_id = " + id, null, null, null, "_id");

    EventoDetalhadoDTO dto = null;

    int idColumnIndex = cursor.getColumnIndex("_id");
    int nomeColumnIndex = cursor.getColumnIndex("nome");
    int valorColumnIndex = cursor.getColumnIndex("valor");
    int datahoraColumnIndex = cursor.getColumnIndex("datahora");
    int casaidColumnIndex = cursor.getColumnIndex("casaid");
    int bandaidColumnIndex = cursor.getColumnIndex("bandaid");

    CasaDAO casaDAO = new CasaDAO();
    BandaDAO bandaDAO = new BandaDAO();

    while (cursor.moveToNext()) {
      try {
        CasaDTO casaDTO = casaDAO.getPeloId(context, cursor.getInt(casaidColumnIndex));

        dto = new EventoDetalhadoDTO(id,
            cursor.getString(nomeColumnIndex),
            cursor.getDouble(valorColumnIndex),
            DataBaseHelper.ISO_8601_FORMAT.parse(cursor.getString(datahoraColumnIndex)),
            new ArrayList<BandaDTO>(),
            casaDTO);
      }
      catch (ParseException e) {
        Log.e(Constantes.TAG, "Falha ao parsear datahora: " +
            cursor.getString(datahoraColumnIndex) + " do Evento de id: " +
            cursor.getInt(idColumnIndex)
            + ". Evento ignorado e NÃO adicionado à base local", e);
        readableDatabase.close();
        return null;
      }

      if (!cursor.isNull(bandaidColumnIndex)) {
        dto.getBandas().add(bandaDAO.getPeloId(context, cursor.getInt(bandaidColumnIndex)));
      }
    }

    readableDatabase.close();
    return dto;
  }
}
