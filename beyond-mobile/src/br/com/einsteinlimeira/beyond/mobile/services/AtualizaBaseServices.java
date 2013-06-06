package br.com.einsteinlimeira.beyond.mobile.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import br.com.einsteinlimeira.beyond.mobile.Constantes;
import br.com.einsteinlimeira.beyond.mobile.R;
import br.com.einsteinlimeira.beyond.mobile.util.HttpUtils;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;
import br.com.einsteinlimeira.beyond.model.dto.CacheExterno;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;
import br.com.einsteinlimeira.beyond.model.dto.CidadeDTO;
import br.com.einsteinlimeira.beyond.model.dto.EventoDTO;
import br.com.einsteinlimeira.beyond.protocol.Requisicao;

import com.google.gson.Gson;

public class AtualizaBaseServices {
  /**
   * Atualiza a base de dados local a partir dos dados remotos.
   * 
   * @param contexto
   *   Contexto da operação.
   * 
   * @throws IOException se ocorrer algum problema de I/O ao realizar a requisição remota.
   */
  public boolean atualiza(Context contexto) throws IOException {
    SharedPreferences sharedPreferences = contexto.getSharedPreferences(
        Constantes.CONFIGURACAO_SHARED_PREFERENCES,
        Context.MODE_PRIVATE);

    String host = sharedPreferences.getString(
        Constantes.CONFIGURACAO_HOST,
        contexto.getResources().getString(
            R.string.configuracao_host_padrao));
    int porta = sharedPreferences.getInt(
        Constantes.CONFIGURACAO_PORTA,
        Integer.parseInt(contexto.getResources().getString(
            R.string.configuracao_porta_padrao)));

    HttpClient httpClient = new DefaultHttpClient();
    HttpPost httpPost = new HttpPost("http://" + host + ":" + porta
        + "/beyond-web/FrontControllerServlet");

    List<NameValuePair> parametros = new ArrayList<NameValuePair>();
    parametros.add(new BasicNameValuePair(Requisicao.PARAMETRO_TIPO,
        Requisicao.TIPO_CACHE));

    try {
      httpPost.setEntity(new UrlEncodedFormEntity(parametros));

      Log.i(Constantes.TAG, "Chamando " + httpPost.getURI());

      HttpResponse httpResponse = httpClient.execute(httpPost);
      int statusCode = httpResponse.getStatusLine().getStatusCode();

      if (statusCode != 200) {
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        Log.e(Constantes.TAG, "A chamada falhou. Retorno: " + statusCode + ": " + reasonPhrase);
        return false;
      }
      else {
        String json = HttpUtils.getString(httpResponse);

        CacheExterno cacheExterno = new Gson().fromJson(json, CacheExterno.class);
        
         // O approach tomado é, por ora, remover todos os dados atualmente na base e 
         // inserir tudo novamente.
        
        // Cidades
        CidadeServices cidadeServices = new CidadeServices();
        // remove as cidades já na base
        cidadeServices.removerTodas(contexto);
        
        List<CidadeDTO> cidades = cacheExterno.getCidades();
        if(!cidades.isEmpty()){
          
          for (CidadeDTO cidade : cidades) {
            if(!cidadeServices.inserir(cidade, contexto)){
              Toast.makeText(contexto, "Falha ao incluir cidade no cache", Toast.LENGTH_LONG).show();
              return false;
            }
          }
        }

        // Casas
        CasaServices casaServices = new CasaServices();
        // remove as casas j� na base
        casaServices.removerTodas(contexto);
        
        List<CasaDTO> casas = cacheExterno.getCasas();
        if(!casas.isEmpty()){
          
          for (CasaDTO casa : casas) {
            if(!casaServices.inserir(casa, contexto)){
              Toast.makeText(contexto, "Falha ao incluir casa no cache", Toast.LENGTH_LONG).show();
              return false;
            }
          }
        }

        
        // Eventos
        EventoServices eventoServices = new EventoServices();
        // remove os eventos já na base
        eventoServices.removerTodos(contexto);
        
        List<EventoDTO> eventos = cacheExterno.getEventos();
        // TODO: habilitar
//        if(!eventos.isEmpty()){
//          
//          for (EventoDTO evento : eventos) {
//            if(!eventoServices.inserir(evento, contexto)){
//              Toast.makeText(contexto, "Falha ao incluir evento no cache", Toast.LENGTH_LONG).show();
//              return false;
//            }
//          }
//        }

        // Bandas
        BandaServices bandaServices = new BandaServices();
        //remove as bandas já na base
        bandaServices.removerTodas(contexto);
        
        List<BandaDTO> bandas = cacheExterno.getBandas();
        if(!bandas.isEmpty()){
        	
        	for(BandaDTO banda : bandas){
        		if(!bandaServices.inserir(banda, contexto)){
        			Toast.makeText(contexto, "Falha ao incluir banda no cache", Toast.LENGTH_LONG).show();
        			return false;
        		}
        	}
        }        
        
        Log.i(Constantes.TAG, "Atualização da base de dados local realizada com sucesso");
        
        return true;
      }
    }
    catch (IOException ioe) {
      Log.e(Constantes.TAG,
          contexto.getResources().getString(
              R.string.global_erro_requisicao), ioe);
      throw ioe;
    }
  }
}
