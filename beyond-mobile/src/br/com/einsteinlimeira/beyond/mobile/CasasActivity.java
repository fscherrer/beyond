package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import br.com.einsteinlimeira.beyond.mobile.model.ListaEvento;
import br.com.einsteinlimeira.beyond.model.Casa;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class CasasActivity extends Activity {

	private ListView listViewListaCasas;
	private Button botaoVoltar, botaoAvancar;
	private List<Casa> casas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_casas);
		
		listViewListaCasas = (ListView) findViewById(R.id.lista_casas);
		AdaptadorCasa adaptador = new AdaptadorCasa(ListaEvento.casas, this);
		listViewListaCasas.setAdapter(adaptador);
		
		botaoVoltar = (Button) findViewById(R.id.botao_voltar);
		botaoVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		botaoAvancar = (Button) findViewById(R.id.botao_avancar);
		botaoAvancar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
	}

}
