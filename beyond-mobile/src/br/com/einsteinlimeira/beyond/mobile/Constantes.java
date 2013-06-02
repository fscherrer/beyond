package br.com.einsteinlimeira.beyond.mobile;

/**
 * Define constantes utilizadas na aplicação como um todo.
 */
public class Constantes {
  /**
   * Define a TAG utilizada para log.
   */
  public static final String TAG = "[ARock]";
  
  public static final String CONFIGURACAO_SHARED_PREFERENCES = "BeyondPrefs";
  public static final String CONFIGURACAO_HOST = "host";
  public static final String CONFIGURACAO_PORTA = "porta";
  
  // Request Codes para comunicação entre activities
  public static final int REQUEST_CODE_FILTRO_CIDADE = 1;
  public static final int REQUEST_CODE_FILTRO_CASA = 2;
  public static final int REQUEST_CODE_FILTRO_ESTILO = 3;
  public static final int REQUEST_CODE_FILTRO_BANDA = 4;
  
  // Constantes para passagem de Extras em Intents
  public static final String EXTRA_CIDADES_FILTRADAS = "br.com.einsteinlimeira.beyond.mobile.cidadesFiltradas";
  public static final String EXTRA_CASAS_FILTRADAS = "br.com.einsteinlimeira.beyond.mobile.casasFiltradas";
  public static final String EXTRA_ESTILOS_FILTRADOS = "br.com.einsteinlimeira.beyond.mobile.estilosFiltrados";
  public static final String EXTRA_BANDAS_FILTRADAS = "br.com.einsteinlimeira.beyond.mobile.bandasFiltradas";
}
