package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Evento}.
 */
@ManagedBean
@ViewScoped
public class EventosMB extends BaseManagedBeanEntidade<Evento> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Evento> getEntidadeServices() {
    return ServicesFactory.getFactory().getEventoServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Evento getNovaEntidade() {
    return new Evento();
  }
}
