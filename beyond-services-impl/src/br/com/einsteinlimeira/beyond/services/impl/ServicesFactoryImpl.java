package br.com.einsteinlimeira.beyond.services.impl;

import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import br.com.einsteinlimeira.beyond.services.UfServices;

/**
 * Implementação padrão de {@link ServicesFactory}.
 */
public class ServicesFactoryImpl extends ServicesFactory {

  /**
   * {@inheritDoc}
   */
  @Override
  public UfServices getUfServices() {
    return new UfServicesImpl();
  }
}