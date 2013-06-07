package br.com.einsteinlimeira.beyond.mobile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.einsteinlimeira.beyond.mobile.database.DataBaseHelper;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;

public class BandaDAO extends EntidadeDAO<BandaDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getNomeTabela() {
		return "banda";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ContentValues getContentValues(BandaDTO entidade) {
		ContentValues contentValues = new ContentValues();
		contentValues.put("_id", entidade.getId());
		contentValues.put("nome", entidade.getNome());
		contentValues.put("estilo", entidade.getEstilos());
		
		return contentValues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BandaDTO getAPartirDoCursor(Cursor cursor) {
		return new BandaDTO(cursor.getInt(cursor.getColumnIndex("_id")), 
				cursor.getString(cursor.getColumnIndex("nome")),
				cursor.getString(cursor.getColumnIndex("estilo")));
	}


  /**
   * Retorna um DTO da Banda do <code>id</code> informado.
   * 
   * @param context
   *   Contexto.
   * @param id
   *   ID da Banda.
   *   
   * @return
   *   DTO obtido.
   */
  public BandaDTO getPeloId(Context context, int id) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

    Cursor cursor =
        readableDatabase.query(false, getNomeTabela(), null, "_id = " + id, null, null, null, null, null);
    
    BandaDTO dto = null;
    
    if(cursor.moveToNext()){
      dto = getAPartirDoCursor(cursor);
    }
    
    return dto;
  }
}
