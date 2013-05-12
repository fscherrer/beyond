package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Evento}.
 */
@ManagedBean
@ViewScoped
public class EventosMB extends BaseManagedBeanEntidade<Evento> {

  /**
   * Armazena as bandas selecionadas.
   */
  private List<Banda> bandasSelecionadas;

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

  /**
   * Retorna as bandas selecionadas.
   * 
   * @return 
   *   As bandas selecionadas.
   */
  public List<Banda> getBandasSelecionadas() {
    return bandasSelecionadas;
  }

  /**
   * Define as bandas selecionadas.
   * 
   * @param bandasSelecionadas 
   *   Bandas selecionadas.
   */
  public void setBandasSelecionadas(List<Banda> bandasSelecionadas) {
    this.bandasSelecionadas = bandasSelecionadas;
  }

  /**
   * Apenas publica o método protegido da classe mãe.
   * 
   * {@inheritDoc}
   */
  @Override
  public void carregarEntidades() {
    super.carregarEntidades();
  }
}
