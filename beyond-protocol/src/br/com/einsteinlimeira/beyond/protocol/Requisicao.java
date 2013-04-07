package br.com.einsteinlimeira.beyond.protocol;

/**
 * Define requisições suportadas.
 */
public class Requisicao {

  /**
   * Representa o parâmetro onde deve ser informado o tipo de requisição desejado.<br />
   */
  public static final String PARAMETRO_TIPO = "tipo";

  /**
   * Valor a ser informado no parâmetro {@link #PARAMETRO_TIPO} para requisições referentes a 
   * Eventos.
   * 
   * @see RequisicaoEvento
   */
  public static final int TIPO_EVENTO = 1;
}