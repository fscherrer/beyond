package br.com.einsteinlimeira.beyond.mobile.dao;

import android.content.ContentValues;
import android.database.Cursor;
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

}
