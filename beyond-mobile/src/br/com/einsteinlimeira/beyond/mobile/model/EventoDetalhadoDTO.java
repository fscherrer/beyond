package br.com.einsteinlimeira.beyond.mobile.model;

import java.util.Date;
import java.util.List;

import br.com.einsteinlimeira.beyond.model.Entidade;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

/**
 * DTO de Evento, específico, detalhado.
 */
public class EventoDetalhadoDTO extends Entidade {
  private String nome;

  private double valor;

  private Date dataHora;

  private List<BandaDTO> bandas;

  private CasaDTO casa;

  private String cidade;

  private String siglaUf;

  public EventoDetalhadoDTO(int id, String nome, double valor, Date dataHora,
      List<BandaDTO> bandas, CasaDTO casa, String cidade, String siglaUf) {
    super(id);
    this.nome = nome;
    this.valor = valor;
    this.dataHora = dataHora;
    this.bandas = bandas;
    this.casa = casa;
    this.cidade = cidade;
    this.siglaUf = siglaUf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Date getDataHora() {
    return dataHora;
  }

  public void setDataHora(Date dataHora) {
    this.dataHora = dataHora;
  }

  public List<BandaDTO> getBandas() {
    return bandas;
  }

  public void setBandas(List<BandaDTO> bandas) {
    this.bandas = bandas;
  }

  public CasaDTO getCasa() {
    return casa;
  }

  public void setCasa(CasaDTO casa) {
    this.casa = casa;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getSiglaUf() {
    return siglaUf;
  }

  public void setSiglaUf(String siglaUf) {
    this.siglaUf = siglaUf;
  }
}
