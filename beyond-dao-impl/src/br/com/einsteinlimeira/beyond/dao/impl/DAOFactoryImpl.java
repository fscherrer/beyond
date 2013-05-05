package br.com.einsteinlimeira.beyond.dao.impl;

import br.com.einsteinlimeira.beyond.dao.BandaDAO;
import br.com.einsteinlimeira.beyond.dao.CasaDAO;
import br.com.einsteinlimeira.beyond.dao.CidadeDAO;
import br.com.einsteinlimeira.beyond.dao.DAOFactory;
import br.com.einsteinlimeira.beyond.dao.EnderecoDAO;
import br.com.einsteinlimeira.beyond.dao.EventoDAO;
import br.com.einsteinlimeira.beyond.dao.MusicoDAO;
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
  public BandaDAO getBandaDAO() {
    return new BandaDAOImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CasaDAO getCasaDAO() {
    return new CasaDAOImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CidadeDAO getCidadeDAO() {
    return new CidadeDAOImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EnderecoDAO getEnderecoDAO() {
    return new EnderecoDAOImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EventoDAO getEventoDAO() {
    return new EventoDAOImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MusicoDAO getMusicoDAO() {
    return new MusicoDAOImpl();
  }

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