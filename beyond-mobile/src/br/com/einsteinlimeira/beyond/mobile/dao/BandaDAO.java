package br.com.einsteinlimeira.beyond.mobile.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    readableDatabase.close();
    return dto;
  }
  
  /**
   * Retorna os estilos encontrados nas bandas.
   * 
   * @param context
   *   Contexto.
   *   
   * @return
   *   Lista de estilos.
   */
  public List<String> getEstilos(Context context) {
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();

    Cursor cursor =
        readableDatabase.query(false, getNomeTabela(), new String[]{"estilo"}, 
            null, null, null, null, null, null);
    
    Set<String> estilos = new HashSet<String>();
    
    String estilo;
    while(cursor.moveToNext()){
      estilo = cursor.getString(0);
      
      if(estilo != null && estilo.trim().length() > 0){
        for(String estiloSplitado: estilo.split(",")){
          estilos.add(estiloSplitado.trim());
        }
      }
    }
    
    readableDatabase.close();
    
    List<String> estilosOrdenados = new ArrayList<String>(estilos);
    Collections.sort(estilosOrdenados);
    
    return estilosOrdenados;
  }

  /**
   * Retorna as Bandas contendo algum dos <code>estilos</code> informados.
   * 
   * @param context
   *   Contexto.
   * @param estilos
   *   Estilos a filtrar as bandas.
   *   
   * @return
   *   Bandas que contém algum dos <code>estilos</code>.
   */
  public List<BandaDTO> listar(Context context, List<String> estilos) {
    // se não filtrou nenhum estilo retorna todas
    if(estilos == null || estilos.isEmpty()){
      return listar(context);
    }
    
    DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
    SQLiteDatabase readableDatabase = dataBaseHelper.getReadableDatabase();
    
    Cursor cursor =
        readableDatabase.query(false, getNomeTabela(), null, null, null, null, null, null, null);
    
    List<BandaDTO> bandas = new ArrayList<BandaDTO>();
    
    int indexColunaEstilo = cursor.getColumnIndex("estilo");
    
    String estilo;
    while(cursor.moveToNext()){
      estilo = cursor.getString(indexColunaEstilo);
      
      if(estilo != null && estilo.trim().length() > 0){
        for(String estiloSplitado: estilo.split(",")){
          if(estilos.contains(estiloSplitado)){
            bandas.add(getAPartirDoCursor(cursor));
            break;
          }
        }
      }
    }
    
    readableDatabase.close();
    return bandas;
  }
}
