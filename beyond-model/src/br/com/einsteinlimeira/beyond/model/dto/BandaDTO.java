package br.com.einsteinlimeira.beyond.model.dto;

import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * DTO de Banda.
 */
public class BandaDTO extends Entidade {

  private String nome;

  private String estilos;

  public BandaDTO(int id, String nome, String estilos) {
    super(id);
    this.nome = nome;
    this.estilos = estilos;
  }

  public String getNome() {
    return nome;
  }

  public String getEstilos() {
    return estilos;
  }
}