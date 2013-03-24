package br.com.einsteinlimeira.beyond.model;

public class Casa extends Entidade {

  private String nome;

  private int cnpj;

  private String responsavel;

  private Endereco endereco;

  private Casa matriz;

  public Casa() {
  }

  public Casa(int id, String nome, int cnpj, String responsavel, Endereco endereco, Casa matriz) {
    super(id);
    this.nome = nome;
    this.cnpj = cnpj;
    this.responsavel = responsavel;
    this.endereco = endereco;
    this.matriz = matriz;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getCnpj() {
    return cnpj;
  }

  public void setCnpj(int cnpj) {
    this.cnpj = cnpj;
  }

  public String getResponsavel() {
    return responsavel;
  }

  public void setResponsavel(String responsavel) {
    this.responsavel = responsavel;
  }

  public Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

  public Casa getMatriz() {
    return matriz;
  }

  public void setMatriz(Casa matriz) {
    this.matriz = matriz;
  }
}
