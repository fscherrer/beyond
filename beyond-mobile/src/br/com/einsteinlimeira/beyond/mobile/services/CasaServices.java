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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import br.com.einsteinlimeira.beyond.mobile.Constantes;
import br.com.einsteinlimeira.beyond.mobile.R;
import br.com.einsteinlimeira.beyond.mobile.util.HttpUtils;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.protocol.Requisicao;
import br.com.einsteinlimeira.beyond.protocol.RequisicaoCasa;

public class CasaServices {

	public List<Casa> getCasas(Context contexto) throws IOException{
		SharedPreferences sharedPreferences = contexto.getSharedPreferences(
				Constantes.CONFIGURACAO_SHARED_PREFERENCES, Context.MODE_PRIVATE);

		String host = sharedPreferences.getString(
				Constantes.CONFIGURACAO_HOST, contexto.getResources().
				getString(R.string.configuracao_host_padrao));
		
		int porta = sharedPreferences.getInt(Constantes.CONFIGURACAO_PORTA,
				Integer.parseInt(contexto.getString(R.string.configuracao_porta_padrao)));
		
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("http://" + host + ":" + porta +
				"/beyond-web/FrontControllerServlet");
		
		List<NameValuePair> parametros = new ArrayList<NameValuePair>();
		parametros.add(new BasicNameValuePair(Requisicao.PARAMETRO_TIPO,
				Requisicao.TIPO_CASA));
		parametros.add(new BasicNameValuePair(RequisicaoCasa.PARAMETRO_CASA,
				Requisicao.TODAS));
		
		try{
			httpPost.setEntity(new UrlEncodedFormEntity(parametros));
			Log.i(Constantes.TAG, "Chamando: " + httpPost.getURI());
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			
			if(statusCode != 200){
				String reasonPharse = httpResponse.getStatusLine().getReasonPhrase();
				Log.e(Constantes.TAG, "A chamada falhou: " + statusCode + ":" + reasonPharse);
				throw new IllegalStateException("" + statusCode + ":" + reasonPharse);
			}else{
				String json = HttpUtils.getString(httpResponse);
				
				return new Gson().fromJson(json, new TypeToken<List<Casa>>(){}.getType());
			}
		}catch (IOException ioe) {
			Log.e(Constantes.TAG, contexto.getResources()
					.getString(R.string.global_erro_requisicao), ioe);
			throw ioe;
		}
		}
}
