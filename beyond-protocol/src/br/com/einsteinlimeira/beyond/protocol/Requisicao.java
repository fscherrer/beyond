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
   */
  public static final String TIPO_EVENTO = "evento";

   /**
   * Valor a ser informado no parâmetro {@link #PARAMETRO_TIPO} para requisições referentes a Casas.
   */
  public static final String TIPO_CASA = "casa";
  
  /**
   * Valor a ser informado no parâmetro {@link #PARAMETRO_TIPO} para requisições referentes a 
   * Cidades
   */
  public static final String TIPO_CIDADE = "cidade";
  
  /**
   * Valor a ser informado no parâmetro {@link #PARAMETRO_TIPO} obtenção dos dados para 
   * armazenamento em cache externo.
   */
  public static final String TIPO_CACHE = "cache";
  
  /**
   * Valor para indicar solicitações de todas as entidades de um tipo
   *@see Requisicao
   */
  public static final String TODAS = "todas";
}
