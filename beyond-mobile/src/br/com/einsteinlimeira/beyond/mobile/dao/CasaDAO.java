package br.com.einsteinlimeira.beyond.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.einsteinlimeira.beyond.mobile.database.DataBaseHelper;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

public class CasaDAO extends EntidadeDAO<CasaDTO> {
  /**
   * {@inheritDoc}
   */
  @Override
  public String getNomeTabela() {
    return "casa";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentValues getContentValues(CasaDTO entidade) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("_id", entidade.getId());
    contentValues.put("nome", entidade.getNome());
    contentValues.put("cidadeid", entidade.getIdCidade());
    contentValues.put("bairro", entidade.getBairro());
    contentValues.put("logradouro", entidade.getLogradouro());
    contentValues.put("cep", entidade.getCep());
    contentValues.put("coordenada", entidade.getCoordenada());
    contentValues.put("numero", entidade.getNumero());

    return contentValues;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CasaDTO getAPartirDoCursor(Cursor cursor) {
    return new CasaDTO(cursor.getInt(cursor.getColumnIndex("_id")),
        cursor.getString(cursor.getColumnIndex("nome")),
        cursor.getString(cursor.getColumnIndex("numero")),
        cursor.getString(cursor.getColumnIndex("bairro")),
        cursor.getString(cursor.getColumnIndex("logradouro")),
        cursor.getString(cursor.getColumnIndex("cep")), 
        cursor.getString(cursor.getColumnIndex("coordenada")),
        cursor.getInt(cursor.getColumnIndex("cidadeid")));
  }
  

  /**
   * Retorna um DTO da Casa do <code>id</code> informado.
   * 
   * @param context
   *   Contexto.
   * @param id
   *   ID da Casa.
   *   
   * @return
   *   DTO obtido.
   */
  public CasaDTO getPeloId(Context context, int id) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

    Cursor cursor =
        readableDatabase.query(false, getNomeTabela(), null, "_id = " + id, null, null, null, null, null);
    
    CasaDTO dto = null;
    
    if(cursor.moveToNext()){
      dto = getAPartirDoCursor(cursor);
    }
    
    readableDatabase.close();
    return dto;
  }
  
  /**
   * Lista as Entidades presentes na base cuja cidade esteja entre <code>idsCidades</code> 
   *   (se informado algum ID de Cidade).
   * 
   * @param context
   *   Context.
   * @param idsCidades
   *   Ids das Cidades das quais deseja-se obter as Casas (filtro).
   *   
   * @return
   *   Entidades presentes na base cuja cidade esteja entre <code>idsCidades</code> 
   *   (se informado algum ID de Cidade).
   */
  public List<CasaDTO> listar(Context context, int[] idsCidades) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();
    
    String filtro = null;

    if (idsCidades != null && idsCidades.length > 0) {
      filtro = DAOUtils.getFiltroQuery(getNomeTabela() + ".cidadeid", idsCidades);
    }

    Cursor cursor =
        readableDatabase.query(false, getNomeTabela(), null, filtro, null, null, null, null, null);
    
    List<CasaDTO> dtos = new ArrayList<CasaDTO>();
    
    while(cursor.moveToNext()){
      dtos.add(getAPartirDoCursor(cursor));
    }
    
    readableDatabase.close();
    return dtos;
  }
}
