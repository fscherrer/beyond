package br.com.einsteinlimeira.beyond.model.dto;

import java.util.List;

/**
 * Objeto para cache externo.
 */
public class CacheExterno {

  private List<CidadeDTO> cidades;

  private List<CasaDTO> casas;

  private List<BandaDTO> bandas;

  private List<EventoDTO> eventos;

  public List<CidadeDTO> getCidades() {
    return cidades;
  }

  public void setCidades(List<CidadeDTO> cidades) {
    this.cidades = cidades;
  }

  public List<CasaDTO> getCasas() {
    return casas;
  }

  public void setCasas(List<CasaDTO> casas) {
    this.casas = casas;
  }

  public List<BandaDTO> getBandas() {
    return bandas;
  }

  public void setBandas(List<BandaDTO> bandas) {
    this.bandas = bandas;
  }

  public List<EventoDTO> getEventos() {
    return eventos;
  }

  public void setEventos(List<EventoDTO> eventos) {
    this.eventos = eventos;
  }
}