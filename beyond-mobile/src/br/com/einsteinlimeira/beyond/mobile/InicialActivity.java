package br.com.einsteinlimeira.beyond.mobile;

import java.io.IOException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.einsteinlimeira.beyond.mobile.services.AtualizaBaseServices;

public class InicialActivity extends GlobalActivity {
  private TextView textViewInformacoes;
  private Button buttonTentarNovamente;
  private ImageView imageViewLogo;
  private boolean atualizando;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inicial);
    imageViewLogo = (ImageView) findViewById(R.id.inicial_Logo);
    textViewInformacoes = (TextView) findViewById(R.id.inicial_textViewInformacoes);
    buttonTentarNovamente = (Button) findViewById(R.id.inicial_buttonTentarNovamente);

    OnClickListener onClickListener = new OnClickListener() {
      protected boolean sucesso;

      @Override
      public void onClick(View v) {
        if (atualizando) {
          Log.i(Constantes.TAG,
              "Tentativa de atualização com outra atualização já em curso. Ignorando.");
        }
        else {
          new AsyncTask<Void, Void, Void>() {
            private ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
              Log.i(Constantes.TAG, "Atualizando");
              atualizando = true;
              textViewInformacoes.setText("");
              buttonTentarNovamente.setVisibility(Button.INVISIBLE);
              progressDialog = new ProgressDialog(InicialActivity.this);
              progressDialog.setMessage(getResources().getString(
                  R.string.global_aguarde));
              progressDialog.show();
            }

            @Override
            protected Void doInBackground(Void... params) {
              try {
                sucesso = new AtualizaBaseServices().atualiza(InicialActivity.this);
              }
              catch (IOException e) {
                Log.e(Constantes.TAG,
                    InicialActivity.this.getResources().getString(R.string.global_erro_requisicao),
                    e);
              }

              return null;
            }

            @Override
            protected void onPostExecute(Void result) {
              progressDialog.dismiss();

              if (!sucesso) {
                Toast.makeText(InicialActivity.this,
                    getResources().getString(R.string.global_erro_requisicao), Toast.LENGTH_LONG)
                    .show();
                textViewInformacoes.setText(R.string.global_erro_requisicao);
                buttonTentarNovamente.setVisibility(Button.VISIBLE);
                
                new AlertDialog.Builder(InicialActivity.this).
                    setMessage(R.string.inicial_ContinuarCache)                    .
                    setCancelable(true)                    .
                    setPositiveButton(R.string.global_sim,
                        new DialogInterface.OnClickListener() {

                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                            startPrincipalETermina();
                          }
                        }).
                    setNegativeButton(R.string.global_nao,
                        new DialogInterface.OnClickListener() {

                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                            // não faz nada
                          }
                        }).
                    create().
                    show();
              }
              else {
                startPrincipalETermina();
              }

              Log.i(Constantes.TAG, "Atualizado");
              atualizando = false;
            }
          }.execute();
        }
      }
    };

    imageViewLogo.setOnClickListener(onClickListener);
    buttonTentarNovamente.setOnClickListener(onClickListener);
    textViewInformacoes.setOnClickListener(onClickListener);
    findViewById(R.id.inicial).setOnClickListener(onClickListener);

    textViewInformacoes.setText(R.string.toque_inicial);
  }

  /**
   * Inicial a {@link PrincipalActivity} e chama finish() dessa.
   */
  private void startPrincipalETermina(){
    startActivity(new Intent(InicialActivity.this, PrincipalActivity.class));
    InicialActivity.this.finish();
  }
}
