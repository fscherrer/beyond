package br.com.einsteinlimeira.beyond.mobile;

import br.com.einsteinlimeira.beyond.mobile.model.Listas;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;

public class CidadesActivity extends Activity {
	
	private ListView listViewListaCidades;
	private Button botaoVoltar, botaoAvancar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cidade);
		
		listViewListaCidades = (ListView)findViewById(R.id.lista_cidades);
		AdaptadorCidade adaptador = new AdaptadorCidade(Listas.cidades, CidadesActivity.this);
		listViewListaCidades.setAdapter(adaptador);	
		
		botaoVoltar = (Button) findViewById(R.id.botao_voltar_cidade);
		botaoVoltar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		botaoAvancar = (Button) findViewById(R.id.botao_avancar_cidade);
		botaoAvancar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
