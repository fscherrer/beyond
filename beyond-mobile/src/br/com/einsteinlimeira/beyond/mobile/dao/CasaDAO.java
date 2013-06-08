package br.com.einsteinlimeira.beyond.mobile.dao;

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
}
