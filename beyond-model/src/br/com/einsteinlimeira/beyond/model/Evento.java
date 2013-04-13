package br.com.einsteinlimeira.beyond.model;

import java.util.Date;
import java.util.List;

public class Evento extends Entidade {

  private Date datahora;
  private String nome;
  private double valor;
  private Casa casa;
  private List<Banda> bandas;
  private Cidade cidade;

  public Evento() {
  }

  public Evento(int id, String nome, Date datahora, double valor, Casa casa, List<Banda> bandas,Cidade cidade) {
    super(id);
    this.datahora = datahora;
    this.valor = valor;
    this.casa = casa;
    this.bandas = bandas;
    this.nome = nome;
    this.cidade = cidade;
  }

  public Date getDatahora() {
    return datahora;
  }

  public void setDatahora(Date datahora) {
    this.datahora = datahora;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Casa getCasa() {
    return casa;
  }

  public void setCasa(Casa casa) {
    this.casa = casa;
  }

  public List<Banda> getBandas() {
    return bandas;
  }

  public void setBandas(List<Banda> bandas) {
    this.bandas = bandas;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }
   
}
