package br.com.einsteinlimeira.beyond.web.model.dao;

import br.com.einsteinlimeira.beyond.model.Entidade;
import java.util.List;

public interface EntidadeDAO<E extends Entidade> {

  public void inserir(E entidade) throws DAOException;

  public boolean remover(E entidade) throws DAOException;

  public E getPeloId(int id) throws DAOException;

  public List<E> listar() throws DAOException;

  public boolean atualizar(E entidade) throws DAOException;
}
