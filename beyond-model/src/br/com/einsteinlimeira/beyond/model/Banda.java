package br.com.einsteinlimeira.beyond.model;

import java.util.Date;
import java.util.List;

public class Banda extends Entidade {

  private String nome;

  private Date dataformacao;

  // músico desenvolvendo uma função (guitarrista, baixista, baterista, vocalista, etc)
  private List<MusicoBanda> musicos;

  private List<Contato> contatos;

  private List<Evento> eventos;

  public Banda() {
  }

  public Banda(int id, String nome, Date dataformacao, List<MusicoBanda> musicos,
    List<Contato> contatos, List<Evento> eventos) {
    super(id);
    this.nome = nome;
    this.dataformacao = dataformacao;
    this.musicos = musicos;
    this.contatos = contatos;
    this.eventos = eventos;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataformacao() {
    return dataformacao;
  }

  public void setDataformacao(Date dataformacao) {
    this.dataformacao = dataformacao;
  }

  public List<MusicoBanda> getMusicos() {
    return musicos;
  }

  public void setMusicos(List<MusicoBanda> musicos) {
    this.musicos = musicos;
  }

  public List<Contato> getContatos() {
    return contatos;
  }

  public void setContatos(List<Contato> contatos) {
    this.contatos = contatos;
  }

  public List<Evento> getEventos() {
    return eventos;
  }

  public void setEventos(List<Evento> eventos) {
    this.eventos = eventos;
  }
}
