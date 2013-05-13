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
import br.com.einsteinlimeira.beyond.mobile.Constantes;
import br.com.einsteinlimeira.beyond.mobile.R;
import br.com.einsteinlimeira.beyond.mobile.util.HttpUtils;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.protocol.Requisicao;
import br.com.einsteinlimeira.beyond.protocol.RequisicaoEvento;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class EventoServices {
	/**
	 * Retorna os Eventos disponíveis na aplicação remota.
	 * 
	 * @param contexto
	 *            Contexto da operação.
	 * 
	 * @return Lista de Eventos.
	 * 
	 * @throws IOException
	 *             se ocorrer algum problema de I/O ao realizar a requisição
	 *             remota.
	 */
	public List<Evento> getEventos(Context contexto) throws IOException {
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
				Requisicao.TIPO_EVENTO));
		parametros.add(new BasicNameValuePair(
				RequisicaoEvento.PARAMETRO_EVENTO,
				Requisicao.TODAS));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(parametros));

			Log.i(Constantes.TAG, "Chamando " + httpPost.getURI());

			HttpResponse httpResponse = httpClient.execute(httpPost);
      int statusCode = httpResponse.getStatusLine().getStatusCode();

      if (statusCode != 200) {
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        Log.e(Constantes.TAG, "A chamada falhou. Retorno: " + statusCode + ": " + reasonPhrase);
        throw new IllegalStateException("Falha na chamada HTTP. Retorno: " + statusCode + ": " + reasonPhrase);
      }
      else {
        String json = HttpUtils.getString(httpResponse);

        return new Gson().fromJson(json, new TypeToken<List<Evento>>() {}.getType());
      }
		} catch (IOException ioe) {
			Log.e(Constantes.TAG,
					contexto.getResources().getString(
							R.string.global_erro_requisicao), ioe);
			throw ioe;
		}
	}
}
