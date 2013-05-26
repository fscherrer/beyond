package br.com.einsteinlimeira.beyond.mobile;

import br.com.einsteinlimeira.beyond.mobile.model.Listas;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class CasasActivity extends Activity {

	private ListView listViewListaCasas;
	private Button botaoVoltar, botaoAvancar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_casas);
		
		listViewListaCasas = (ListView) findViewById(R.id.lista_casas);
		AdaptadorCasa adaptador = new AdaptadorCasa(Listas.casas, this);
		listViewListaCasas.setAdapter(adaptador);
		
		botaoVoltar = (Button) findViewById(R.id.botao_voltar_casa);
		botaoVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		botaoAvancar = (Button) findViewById(R.id.botao_avancar_casa);
		botaoAvancar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}

}
