package br.com.einsteinlimeira.beyond.model;

import java.util.List;

public class Casa extends Entidade {

  private String nome;

  private int cnpj;

  private String responsavel;

  private Endereco endereco;

  private Casa matriz;

  private List<Evento> eventos;

  private List<Usuario> usuarios;

  private List<Contato> contatos;

  public Casa() {
  }

  public Casa(int id, String nome, int cnpj, String responsavel, Endereco endereco, Casa matriz,
    List<Evento> eventos, List<Usuario> usuarios, List<Contato> contatos) {
    super(id);
    this.nome = nome;
    this.cnpj = cnpj;
    this.responsavel = responsavel;
    this.endereco = endereco;
    this.matriz = matriz;
    this.eventos = eventos;
    this.usuarios = usuarios;
    this.contatos = contatos;
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

  public List<Evento> getEventos() {
    return eventos;
  }

  public void setEventos(List<Evento> eventos) {
    this.eventos = eventos;
  }

  public List<Usuario> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(List<Usuario> usuarios) {
    this.usuarios = usuarios;
  }

  public List<Contato> getContatos() {
    return contatos;
  }

  public void setContatos(List<Contato> contatos) {
    this.contatos = contatos;
  }
}
