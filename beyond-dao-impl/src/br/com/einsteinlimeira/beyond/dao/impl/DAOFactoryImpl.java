package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.dao.UfDAO;
import br.com.einsteinlimeira.beyond.dao.UsuarioDAO;

/**
 * Implementação padrão de {@link DAOFactoryImpl}.
 */
public class DAOFactoryImpl extends DAOFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public UfDAO getUfDAO() {
    return new UfDAOImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UsuarioDAO getUsuarioDAO() {
    return new UsuarioDAOImpl();
  }
}