package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.dao.UfDAO;

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
}