package br.com.einsteinlimeira.beyond.model;

public class Endereco extends Entidade {

  private String bairro;

  private String cep;

  private String logradouro;

  private String complemento;

  private String numero;

  private Casa casa;

  private Cidade cidade;

  private String coordenada;

  public Endereco() {
  }

  public Endereco(int id, String bairro, String cep, String logradouro, String complemento,
      String numero, Casa casa, Cidade cidade, String coordenada) {
    super(id);
    this.bairro = bairro;
    this.cep = cep;
    this.logradouro = logradouro;
    this.complemento = complemento;
    this.numero = numero;
    this.casa = casa;
    this.cidade = cidade;
    this.coordenada = coordenada;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Casa getCasa() {
    return casa;
  }

  public void setCasa(Casa casa) {
    this.casa = casa;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }

  public String getCoordenada() {
    return coordenada;
  }

  public void setCoordenada(String coordenada) {
    this.coordenada = coordenada;
  }
}
