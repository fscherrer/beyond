package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Entidade;
import java.util.List;

/**
 * Define métodos providos por implementações de DAO (<b>D</b>ata <b>A</b>ccess <b>O</b>bject) que 
 * trabalham com manipulação (CRUD (<b>C</b>reate, <b>R</b>etrieve, <b>U</b>pdate e <b>D</b>elete)) 
 * de {@link Entidade}.
 * 
 * @param <E> 
 *   Tipo da {@link Entidade} manipulada pela implementação.
 */
public interface EntidadeDAO<E extends Entidade> {

  /**
   * Método para inclusão de {@link Entidade}s do tipo específico.<br />
   * Corresponde ao <b>C</b> (<b>C</b>reate) do <i>CRUD</i>.
   * 
   * @param entidade
   *   {@link Entidade} a ser incluída.
   * 
   * @return 
   *   ID atribuído à {@link Entidade} incluída.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a inserção da entidade.
   */
  int inserir(E entidade) throws DAOException;

  /**
   * Retorna a {@link Entidade} correspondente ao <code>id</code>.<br />
   * Corresponde ao <b>R</b> (<b>R</b>etreive) do <i>CRUD</i>.
   * 
   * @param id
   *   ID da {@link Entidade} que deseja obter.
   * 
   * @return
   *   {@link Entidade} correspondente ao <code>id</code> informado.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção da entidade.
   */
  E getPeloId(int id) throws DAOException;

  /**
   * Retorna todas as {@link Entidade}s disponíveis do tipo específico.<br />
   * Corresponde ao <b>R</b> (<b>R</b>etreive) do <i>CRUD</i>.
   * 
   * @return
   *   Todas as {@link Entidade} disponíveis do tipo específico.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção das entidades.
   */
  List<E> listar() throws DAOException;

  /**
   * Atualiza a {@link Entidade} armazenada com o estado da <code>entidade</code>.<br />
   * Corresponde ao <b>U</b> (<b>U</b>pdate) do <i>CRUD</i>.
   * 
   * @param entidade
   *   {@link Entidade} a ser atualizada.
   * 
   * @throws DAOException  lançada caso ocorra algum problema durante a atualização da entidade.
   */
  void atualizar(E entidade) throws DAOException;

  /**
   * Remove a {@link Entidade} <code>entidade</code>.<br />
   * Corresponde ao <b>D</b> (<b>D</b>elete) do <i>CRUD</i>.
   * 
   * @param entidade
   *   {@link Entidade} a ser removida.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a remoção da entidade.
   */
  void remover(E entidade) throws DAOException;
}
