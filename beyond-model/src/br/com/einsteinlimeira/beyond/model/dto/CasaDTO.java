package br.com.einsteinlimeira.beyond.model.dto;

import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * DTO de Casa.
 */
public class CasaDTO extends Entidade {

  private String nome;

  private String logradouro;

  private String numero;

  private String bairro;

  private String cep;

  private String coordenada;

  private int idCidade;

  public CasaDTO(int id, String nome, String logradouro, String numero, String bairro, String cep,
      String coordenada, int idCidade) {
    super(id);
    this.nome = nome;
    this.logradouro = logradouro;
    this.numero = numero;
    this.bairro = bairro;
    this.cep = cep;
    this.coordenada = coordenada;
    this.idCidade = idCidade;
  }

  public String getNome() {
    return nome;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public String getNumero() {
    return numero;
  }

  public String getBairro() {
    return bairro;
  }

  public String getCep() {
    return cep;
  }

  public String getCoordenada() {
    return coordenada;
  }

  public int getIdCidade() {
    return idCidade;
  }
}