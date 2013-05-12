package br.com.einsteinlimeira.beyond.protocol;

/**
 * Definie parâmetros suportados para requisições de Eventos.
 */
public class RequisicaoEvento {

  /**
   * Representa o parâmetro onde deve ser informado o Evento que deseja-se requisitar.<br />
   * O Evento desejado deve ser requisitado informando, para esse parâmetro, o ID de um Evento
   * conhecido ou {@link Requisicao#TODAS} para obter todos os Eventos disponíveis.
   */
  public static final String PARAMETRO_EVENTO = "evento";
  
}