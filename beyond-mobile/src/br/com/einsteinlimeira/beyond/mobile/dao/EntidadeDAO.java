package br.com.einsteinlimeira.beyond.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.einsteinlimeira.beyond.mobile.database.DataBaseHelper;
import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * DAO (<b>D</b>ata <b>A</b>cess <b>O</b>bject) para manipulação de {@link Entidade}
 * na base de dados local (SQLite).
 *
 * @param <E>
 *   Tipo da Entidade.
 */
public abstract class EntidadeDAO<E extends Entidade> {
  
  /**
   * Insere a entidade na base local.
   * 
   * @param entidade
   *   Objeto representando uma entidade.
   * @param context
   *   Contexto.
   *   
   * @return
   *   <code>True</code> se a entidade foi inserida.
   */
  public boolean inserir(E entidade, Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

    ContentValues contentValues = getContentValues(entidade);

    return writableDatabase.insert(getNomeTabela(), null, contentValues) > 0;
  }
  
  /**
   * Retorna o nome da tabela na base de dados local.
   * 
   * @return
   *   Nome da tabela.
   */
  public abstract String getNomeTabela();
  
  /**
   * Retorna um objeto contendo as colunas e valores.<br />.
   * Exemplo:
   * <code>
   *  ContentValues contentValues = new ContentValues();
   *  contentValues.put("_id", entidade.getId());
   *  contentValues.put("nome", entidade.getNome());
   *  return contentValues;
   * </code>
   * 
   * @param entidade
   *   Entidade da qual deseja-se obter um ContentValues.
   *  
   * @return
   *   ContentValues devidamente preenchido.
   */
  public abstract ContentValues getContentValues(E entidade);

  /**
   * Remove todas as Entidades presentes na base.
   * 
   * @param context
   *   Contexto.
   */
  public void removerTodas(Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase writableDatabase = dataBaseHelper.getWritableDatabase();

    writableDatabase.delete(getNomeTabela(), null, null);
  }


  /**
   * Lista as Entidades presentes na base.
   * 
   * @param context
   *   Context.
   *   
   * @return
   *   Entidades presentes na base.
   */
  public List<E> listar(Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

    Cursor cursor =
        readableDatabase.query(false, getNomeTabela(), null, null, null, null, null, null, null);
    
    List<E> dtos = new ArrayList<E>();
    
    while(cursor.moveToNext()){
      dtos.add(getAPartirDoCursor(cursor));
    }
    
    return dtos;
  }
  
  /**
   * Returna uma Entidade a partir do <code>cursor</code>.
   * 
   * @param cursor
   *   Cursor do qual a Entidade deve ser obtida. <b>IMPORTANTE:</b> apenas obter os dados
   *   da posição atual, não movimentar esse cursor.
   *   
   * @return
   *   Entidade.
   */
  public abstract E getAPartirDoCursor(Cursor cursor);
}
