package br.com.einsteinlimeira.beyond.model.dto;

import br.com.einsteinlimeira.beyond.model.Entidade;
import java.util.Date;
import java.util.List;

/**
 * DTO de Evento.
 */
public class EventoDTO extends Entidade {

  private String nome;

  private double valor;

  private Date dataHora;

  private List<Integer> idsBandas;

  private int idCasa;

  public EventoDTO(int id, String nome, double valor, Date dataHora, List<Integer> idsBandas,
      int idCasa) {
    super(id);
    this.nome = nome;
    this.valor = valor;
    this.dataHora = dataHora;
    this.idsBandas = idsBandas;
    this.idCasa = idCasa;
  }

  public String getNome() {
    return nome;
  }

  public double getValor() {
    return valor;
  }

  public Date getDataHora() {
    return dataHora;
  }

  public List<Integer> getIdsBandas() {
    return idsBandas;
  }

  public int getIdCasa() {
    return idCasa;
  }
}