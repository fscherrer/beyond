package br.com.einsteinlimeira.beyond.mobile.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class HttpUtils {
  /**
   * Retorma uma String representando o conteúdo de <code>httpResponse</code>.
   * 
   * @param httpResponse
   *   HttpResponse da qual deseja-se obter o conteúdo.
   *   
   * @return
   *   Conteúdo lido.
   *   
   * @throws IOException se ocorrer algum problema ao obter o conteúdo da <code>httpResponse</code>.
   */
  public static String getString(HttpResponse httpResponse) throws IOException {
    HttpEntity entity = httpResponse.getEntity();

    if (entity == null) {
      return null;
    }

    InputStream contentInputStream = null;

    try {
      contentInputStream = entity.getContent();

      return IOUtils.getString(contentInputStream);
    }
    finally {
      if (contentInputStream != null) {
        contentInputStream.close();
      }
    }
  }
}
