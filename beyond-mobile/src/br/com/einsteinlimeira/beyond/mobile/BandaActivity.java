package br.com.einsteinlimeira.beyond.mobile;

import br.com.einsteinlimeira.beyond.mobile.model.Listas;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;

public class BandaActivity extends Activity {

	private ListView listaBandas;
	private Button botaoVoltar, botaoAvancar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_banda);

		listaBandas = (ListView) findViewById(R.id.lista_bandas);
		AdaptadorBandas adptador = new AdaptadorBandas(Listas.bandas, BandaActivity.this);
		listaBandas.setAdapter(adptador);
		
		botaoVoltar = (Button) findViewById(R.id.botao_voltar_bandas);
		botaoVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		botaoAvancar = (Button) findViewById(R.id.botao_avancar_bandas);
		botaoAvancar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}

}
