package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Activity para configuração da aplicação.
 */
public class ConfiguracaoActivity extends Activity {
  private EditText editTextHost;
  private EditText editTextPorta;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_configuracao);

    editTextHost = (EditText) findViewById(R.id.configuracao_editTextHost);
    editTextPorta = (EditText) findViewById(R.id.configuracao_editTextPorta);

    SharedPreferences sharedPreferences = getSharedPreferences(
        Constantes.CONFIGURACAO_SHARED_PREFERENCES, MODE_PRIVATE);

    String host = sharedPreferences.getString(Constantes.CONFIGURACAO_HOST,
        getResources().getString(R.string.configuracao_host_padrao));
    int porta = sharedPreferences.getInt(Constantes.CONFIGURACAO_PORTA,
        Integer.parseInt(getResources().getString(R.string.configuracao_porta_padrao)));

    editTextHost.setText(host);
    editTextPorta.setText(String.valueOf(porta));
  }

  @Override
  protected void onDestroy() {
    SharedPreferences sharedPreferences = getSharedPreferences(Constantes.CONFIGURACAO_SHARED_PREFERENCES,
        MODE_PRIVATE);
    Editor editor = sharedPreferences.edit();
    editor.putString(Constantes.CONFIGURACAO_HOST, editTextHost.getText().toString());
    editor.putInt(Constantes.CONFIGURACAO_PORTA, Integer.parseInt(editTextPorta.getText().toString()));
    editor.commit();

    super.onDestroy();
  }
}
