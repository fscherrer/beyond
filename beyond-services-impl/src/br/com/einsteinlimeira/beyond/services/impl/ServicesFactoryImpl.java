package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.services.BandaServices;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import br.com.einsteinlimeira.beyond.services.EventoServices;
import br.com.einsteinlimeira.beyond.services.MusicoServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import br.com.einsteinlimeira.beyond.services.UfServices;
import br.com.einsteinlimeira.beyond.services.UsuarioServices;

/**
 * Implementação padrão de {@link ServicesFactory}.
 */
public class ServicesFactoryImpl extends ServicesFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public BandaServices getBandaServices() {
    return new BandaServicesImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CasaServices getCasaServices() {
    return new CasaServicesImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public EventoServices getEventoServices() {
    return new EventoServicesImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MusicoServices getMusicoServices() {
    return new MusicoServicesImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UfServices getUfServices() {
    return new UfServicesImpl();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UsuarioServices getUsuarioServices() {
    return new UsuarioServicesImpl();
  }
}