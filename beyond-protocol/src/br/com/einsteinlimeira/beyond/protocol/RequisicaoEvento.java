package br.com.einsteinlimeira.beyond.protocol;

/**
 * Definie parâmetros suportados para requisições de Eventos.
 */
public class RequisicaoEvento {

  /**
   * Representa o parâmetro onde deve ser informado o Evento que deseja-se requisitar.<br />
   * O Evento desejado deve ser requisitado informando, para esse parâmetro, o ID de um Evento
   * conhecido ou {@link #EVENTO_TODOS} para obter todos os Eventos disponíveis.
   */
  public static final String PARAMETRO_EVENTO = "evento";
  
  /**
   * Valor a ser informado no parâmetro {@link #PARAMETRO_EVENTO} para obtenção de todos os Eventos 
   * disponíveis.
   */
  public static final String EVENTO_TODOS = "todos";

}