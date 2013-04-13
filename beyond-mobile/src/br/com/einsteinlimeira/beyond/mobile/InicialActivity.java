package br.com.einsteinlimeira.beyond.mobile;

import java.util.ArrayList;

import android.app.ProgressDialog;
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
import br.com.einsteinlimeira.beyond.mobile.services.EventoServices;
import br.com.einsteinlimeira.beyond.model.Evento;

public class InicialActivity extends GlobalActivity {
	private TextView textViewInformacoes;
	private Button buttonTentarNovamente;
	private ImageView imageViewLogo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inicial);
		imageViewLogo = (ImageView) findViewById(R.id.inicial_Logo);
		textViewInformacoes = (TextView) findViewById(R.id.inicial_textViewInformacoes);
		buttonTentarNovamente = (Button) findViewById(R.id.inicial_buttonTentarNovamente);

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				new ObtemEventosAsyncTask().execute();
			}
		};

		imageViewLogo.setOnClickListener(onClickListener);
		buttonTentarNovamente.setOnClickListener(onClickListener);
		textViewInformacoes.setOnClickListener(onClickListener);
		findViewById(R.id.inicial).setOnClickListener(onClickListener);

		textViewInformacoes.setText(R.string.toque_inicial);
	}

	private class ObtemEventosAsyncTask extends
			AsyncTask<Void, Void, ArrayList<Evento>> {
		private ProgressDialog progressDialog;
		private boolean problema;

		@Override
		protected void onPreExecute() {
			textViewInformacoes.setText("");
			buttonTentarNovamente.setVisibility(Button.INVISIBLE);
			progressDialog = new ProgressDialog(InicialActivity.this);
			progressDialog.setMessage(getResources().getString(
					R.string.global_aguarde));
			progressDialog.show();
		}

		@Override
		protected ArrayList<Evento> doInBackground(Void... params) {
			try {
				return new ArrayList<Evento>(
						new EventoServices().getEventos(InicialActivity.this));
			} catch (Exception e) {
				Log.e(Constantes.TAG,
						getResources().getString(
								R.string.global_erro_requisicao), e);
				problema = true;
			}

			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<Evento> result) {
			progressDialog.hide();

			if (problema) {
				Toast.makeText(
						InicialActivity.this,
						getResources().getString(
								R.string.global_erro_requisicao),
						Toast.LENGTH_LONG).show();
				textViewInformacoes.setText(R.string.global_erro_requisicao);
				buttonTentarNovamente.setVisibility(Button.VISIBLE);
			} else {
				Intent intent = new Intent(InicialActivity.this,
						EventosActivity.class);
				intent.putExtra("eventos", result);
				startActivity(intent);
				InicialActivity.this.finish();
			}
		}
	}
}
