package br.com.einsteinlimeira.beyond.mobile.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Provê métodos para trabalho com I/O. 
 */
public class IOUtils {
  private static final int TAMANHO_PADRAO_BUFFERS = 1024;

  /**
   * Obtém uma String representando o conteúdo disponível na <code>inputStream</code>
   * 
   * @param inputStream
   *   Stream de onde dados serão lidos para construir a String de retorno.
   *   
   * @return
   *   String construída a partir dos dados lidos da <code>inputStream</code>.
   * 
   * @throws IOException se ocorrer algum erro de I/O ao ler o conteúdo da <code>inputStream</code>.
   */
  public static String getString(InputStream inputStream) throws IOException {
    byte[] buffer = new byte[TAMANHO_PADRAO_BUFFERS];

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    int bytesLidos = 0;

    while ((bytesLidos = inputStream.read(buffer)) > 0) {
      byteArrayOutputStream.write(buffer, 0, bytesLidos);
    }

    String stringLida = new String(byteArrayOutputStream.toByteArray());

    // a implementação de ByteArrayOutputStream da Sun não faz nada no close, 
    // mas chamando para "respeitar" boas práticas
    byteArrayOutputStream.close();
    
    return stringLida;
  }
}
