package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Entidade;
import java.util.List;

public interface EntidadeDAO<E extends Entidade> {

  public int inserir(E entidade) throws DAOException;

  public void remover(E entidade) throws DAOException;

  public E getPeloId(int id) throws DAOException;

  public List<E> listar() throws DAOException;

  public void atualizar(E entidade) throws DAOException;
}
