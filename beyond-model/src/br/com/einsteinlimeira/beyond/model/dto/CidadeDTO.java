package br.com.einsteinlimeira.beyond.model.dto;

import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * DTO de Cidade.
 */
public class CidadeDTO extends Entidade {

  private String nome;

  private String sigalUf;

  public CidadeDTO(int id, String nome, String sigalUf) {
    super(id);
    this.nome = nome;
    this.sigalUf = sigalUf;
  }

  public String getNome() {
    return nome;
  }

  public String getSigalUf() {
    return sigalUf;
  }
}