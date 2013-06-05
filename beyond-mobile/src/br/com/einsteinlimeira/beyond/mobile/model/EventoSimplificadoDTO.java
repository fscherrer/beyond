package br.com.einsteinlimeira.beyond.mobile.model;

import java.util.Date;

import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * DTO de Evento, simplificado, apenas com id, nome e data hora.
 */
public class EventoSimplificadoDTO extends Entidade {
  private String nome;
  private Date dataHora;

  public EventoSimplificadoDTO(int id, String nome, Date dataHora) {
    super(id);
    this.nome = nome;
    this.dataHora = dataHora;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataHora() {
    return dataHora;
  }

  public void setDataHora(Date dataHora) {
    this.dataHora = dataHora;
  }

}
