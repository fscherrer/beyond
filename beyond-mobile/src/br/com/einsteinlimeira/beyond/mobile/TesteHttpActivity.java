package br.com.einsteinlimeira.beyond.mobile;

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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import br.com.einsteinlimeira.beyond.mobile.util.HttpUtils;
import br.com.einsteinlimeira.beyond.protocol.Requisicao;
import br.com.einsteinlimeira.beyond.protocol.RequisicaoEvento;

public class TesteHttpActivity extends Activity {
  private TextView textViewHttpResponse;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_teste_http);

    textViewHttpResponse = (TextView) findViewById(R.id.textViewHttpResponse);

    new AsyncTask<Void, Void, String>() {
      private ProgressDialog progressDialog;
      private boolean problema;

      @Override
      protected void onPreExecute() {
        progressDialog = new ProgressDialog(TesteHttpActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.global_aguarde));
        progressDialog.show();
      }

      @Override
      protected String doInBackground(Void... params) {
        SharedPreferences sharedPreferences = getSharedPreferences(
            Constantes.CONFIGURACAO_SHARED_PREFERENCES, MODE_PRIVATE);
        
        String host = sharedPreferences.getString(Constantes.CONFIGURACAO_HOST, 
            getResources().getString(R.string.configuracao_host_padrao));
        int porta = sharedPreferences.getInt(Constantes.CONFIGURACAO_PORTA, 
            Integer.parseInt(getResources().getString(R.string.configuracao_porta_padrao)));
        
        
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://" + host + ":" + porta + "/beyond-web/FrontControllerServlet");

        List<NameValuePair> parametros = new ArrayList<NameValuePair>();
        parametros.add(new BasicNameValuePair(Requisicao.PARAMETRO_TIPO, Requisicao.TIPO_EVENTO));
        parametros.add(new BasicNameValuePair(RequisicaoEvento.PARAMETRO_EVENTO,
            RequisicaoEvento.EVENTO_TODOS));

        try {
          httpPost.setEntity(new UrlEncodedFormEntity(parametros));
          
          Log.i(Constantes.TAG, "Chamando " + httpPost.getURI());
          
          HttpResponse httpResponse = httpClient.execute(httpPost);

          return HttpUtils.getString(httpResponse);

        }
        catch (IOException ioe) {
          Log.e(Constantes.TAG, getResources().getString(R.string.global_erro_requisicao), ioe);
          problema = true;
          return null;
        }
      }

      @Override
      protected void onPostExecute(String result) {
        progressDialog.hide();

        if (problema) {
          textViewHttpResponse.setText("");
          Toast.makeText(TesteHttpActivity.this, getResources().getString(R.string.global_erro_requisicao), 
              Toast.LENGTH_LONG).show();
        }
        else {
          textViewHttpResponse.setText(result);
        }
      }
    }.execute();
  }
}
