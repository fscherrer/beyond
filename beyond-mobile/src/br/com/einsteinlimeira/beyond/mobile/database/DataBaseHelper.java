package br.com.einsteinlimeira.beyond.mobile.database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Helper para acesso à base de dados local da aplicação.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

  /**
   * Nome do banco de dados.
   */
  public static final String NOME_BANCO_DADOS = "beyond";

  /**
   * Versão do banco de dados.
   */
  public static final int VERSAO_BANCO_DADOS = 2;
  
  /**
   * DateFormat para se trabalhar com data e hora como text no SQLite, já que não
   * suporta nada como datetime.<br />
   * Detalhes vide: http://www.sqlite.org/datatype3.html
   */
  public static final DateFormat ISO_8601_FORMAT =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

  /**
   * Cria um novo helper para manipulação do banco de dados local (banco {@link #NOME_BANCO_DADOS},
   * versão {@link #VERSAO_BANCO_DADOS}).
   * 
   * @param context
   *   Usado para abrir ou criar a base de dados.
   */
  public DataBaseHelper(Context context) {
    super(context, NOME_BANCO_DADOS, null, VERSAO_BANCO_DADOS);
  }

  /*
   * 1.
   * http://www.vogella.com/articles/AndroidSQLite/article.html
   * It is best practice to create a separate class per table. This class defines static onCreate()
   * and onUpgrade() methods. These methods are called in the corresponding methods of 
   * SQLiteOpenHelper. This way your implementation of SQLiteOpenHelper will stay readable, even 
   * if you have several tables.
   * 
   * 2.
   * The database tables should use the identifier _id for the primary key of the table. Several 
   * Android functions rely on this standard.
   * 
   * 3.
   * A Cursor needs to be closed with the close() method call.
   * 
   * 4.
   * é possível rodar queries via rawQuery, que retorna um Cursor
   * 
   * 5.
   * ver SimpleCursorAdapter
   */

  @Override
  public void onCreate(SQLiteDatabase db) {
    // Casa
    db.execSQL("create table casa (" +
        "_id integer not null primary key, " +
        "nome text not null, " +
        "logradouro text not null, " +
        "numero text not null, " +
        "bairro text, " +
        "cep text not null, " +
        "coordenada text, " +
        "cidadeid integer not null);");
    
    // Cidade
    db.execSQL("create table cidade (" +
        "_id integer not null primary key, " +
        "nome text not null, " +
        "siglauf text not null);");
    
    // Banda
    db.execSQL("create table banda (" +
        "_id integer not null primary key, " +
        "nome text not null);");
    
    // Evento
    db.execSQL("create table evento (" +
        "_id integer not null primary key, " +
        "nome text not null, " +
        "valor real not null, " +
        "datahora text not null, " +
        "casaid integer not null);");
    
    // EventoBanda
    db.execSQL("create table eventobanda (" +
        "eventoid integer not null, " +
        "bandaid integer not null);");
    
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table casa");
    db.execSQL("drop table cidade");
    db.execSQL("drop table banda");
    db.execSQL("drop table evento");
    db.execSQL("drop table eventobanda");
    
    onCreate(db);
  }
}
