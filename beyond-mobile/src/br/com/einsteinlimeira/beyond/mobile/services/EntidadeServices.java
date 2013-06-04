package br.com.einsteinlimeira.beyond.mobile.services;

import java.util.List;

import android.content.Context;
import br.com.einsteinlimeira.beyond.mobile.dao.EntidadeDAO;
import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * Provê serviços relacionados a Entidades.
 * 
 * @param <E>
 *   Tipo da Entidade.
 */
public abstract class EntidadeServices<E extends Entidade> {
  
  /**
   * Insere a <code>entidade</code>.
   * 
   * @param entidade
   *   Entidade a ser inserida.
   * @param context
   *   Contexto.
   *   
   * @return
   *   <code>True</code> se a entidade foi inserida com sucesso.
   */
  public boolean inserir(E entidade, Context context) {
    return getDAO().inserir(entidade, context);
  }
  
  /**
   * Remove todas as Entidades.
   * 
   * @param context
   *   Contexto.
   */
  public void removerTodas(Context context){
    getDAO().removerTodas(context);
  }
  
  /**
   * Lista as Entidades.
   * 
   * @param context
   *   Contexto.
   *   
   * @return
   *   Lista de Entidades.
   */
  public List<E> listar(Context context){
    return getDAO().listar(context);
  }
  
  /**
   * Retorna a implementação de DAO (<b>D</b>ata <b>A</b>cess <b>O</b>bject)
   * para manipulação da Entidade.
   * 
   * @return
   *   Implementação de DAO.
   */
  public abstract EntidadeDAO<E> getDAO();
}
