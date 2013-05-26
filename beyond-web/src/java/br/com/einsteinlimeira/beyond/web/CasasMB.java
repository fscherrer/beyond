package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Endereco;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.inject.Inject;
import org.primefaces.component.gmap.GMap;
import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * ManagedBean para manipulação de {@link Casa}.
 */
@ManagedBean
@ViewScoped
public class CasasMB extends BaseManagedBeanEntidade<Casa> {

  /**
   * Modelo para o mapa.
   */
  private MapModel mapModel;

  /**
   * LatLng utilizada quando não há uma coordenada definida.
   */
  private LatLng latLngInicial = new LatLng(-22.023527, -48.240967);

  /**
   * Mapa.
   */
  private UIComponent map;

  /**
   * Services de Casa.
   */
  @Inject
  private CasaServices casaServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Casa> getEntidadeServices() {
    return casaServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Casa getNovaEntidade() {
    Casa casa = new Casa();
    casa.setEndereco(new Endereco());
    return casa;
  }

  /**
   * Retorna a lista de Casas disponíveis.
   * 
   * @return 
   *   Lista de Casas disponíveis.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public List<Casa> getCasas() {
    return getEntidades();
  }

  /**
   * Define a referência a ser guardada a uma {@link Casa}.
   * 
   * @param casa  
   *   {@link Casa} a ser guardada para operação futura.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public void setCasa(Casa casa) {
    setEntidade(casa);
  }

  /**
   * Retorna uma {@link Casa} guardada.
   * 
   * @return 
   *   {@link Casa} previamente guardada através do {@link #setCasa(Casa)}.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public Casa getCasa() {
    return getEntidade();
  }

  /**
   * Retorna o modelo para o mapa.
   * 
   * @return 
   *   Modelo para o mapa.
   */
  public MapModel getMapModel() {
    return mapModel;
  }

  @Override
  public void editar() {
    super.editar();

    mapModel = new DefaultMapModel();

    Marker marker;
    String coordenada = entidade.getEndereco().getCoordenada();

    if (coordenada != null && !coordenada.trim().isEmpty()) {
      String[] latLng = coordenada.split(",");

      try {
        marker = new Marker(new LatLng(
            Double.parseDouble(latLng[0]),
            Double.parseDouble(latLng[1])));

        ((GMap) map).setCenter(coordenada);
        ((GMap) map).setZoom(15);
      }
      catch (NumberFormatException nfe) {
        marker = new Marker(latLngInicial);
      }
    }
    else {
      marker = new Marker(latLngInicial);
    }

    mapModel.addOverlay(marker);
    marker.setDraggable(true);
  }

  @Override
  public void incluir() {
    super.incluir();

    mapModel = new DefaultMapModel();
    Marker marker = new Marker(latLngInicial);
    mapModel.addOverlay(marker);
    marker.setDraggable(true);

    ((GMap) map).setCenter(latLngInicial.getLat() + "," + latLngInicial.getLng());
    ((GMap) map).setZoom(7);
  }

  public void pontoSelecionado(MarkerDragEvent event) {
    LatLng latlng = event.getMarker().getLatlng();
    entidade.getEndereco().setCoordenada(latlng.getLat() + "," + latlng.getLng());
  }

  public UIComponent getMap() {
    return map;
  }

  public void setMap(UIComponent map) {
    this.map = map;
  }
}