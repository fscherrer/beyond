package br.com.einsteinlimeira.beyond.mobile.dao;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.einsteinlimeira.beyond.model.dto.CidadeDTO;

/**
 * DAO (<b>D</b>ata <b>A</b>cess <b>O</b>bject) para manipulação de Cidade.
 */
public class CidadeDAO extends EntidadeDAO<CidadeDTO> {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getNomeTabela() {
    return "cidade";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ContentValues getContentValues(CidadeDTO entidade) {
    ContentValues contentValues = new ContentValues();
    contentValues.put("_id", entidade.getId());
    contentValues.put("nome", entidade.getNome());
    contentValues.put("siglauf", entidade.getSigalUf());

    return contentValues;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CidadeDTO getAPartirDoCursor(Cursor cursor) {
    return new CidadeDTO(
        cursor.getInt(cursor.getColumnIndex("_id")),
        cursor.getString(cursor.getColumnIndex("nome")),
        cursor.getString(cursor.getColumnIndex("siglauf")));
  }
}
