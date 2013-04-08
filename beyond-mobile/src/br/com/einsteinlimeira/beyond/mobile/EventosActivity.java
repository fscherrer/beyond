package br.com.einsteinlimeira.beyond.mobile;

import br.com.einsteinlimeira.beyond.mobile.model.ListaEvento;
import br.com.einsteinlimeira.beyond.mobile.util.Constantes;
import br.com.einsteinlimeira.beyond.model.Evento;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class EventosActivity extends Activity {

	private ListView listViewListaEventos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);

		listViewListaEventos = (ListView) findViewById(R.id.lista_eventos);
		Adaptador adaptador = new Adaptador(ListaEvento.eventos, this);
		listViewListaEventos.setAdapter(adaptador);

		listViewListaEventos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Evento eventoSelecionado = (Evento) listViewListaEventos.getAdapter().getItem(position);
				
				Log.i(Constantes.TAG, "Evento selecionado: "+eventoSelecionado.getBanda());
				
				Intent intent = new Intent(EventosActivity.this, EventoDetalheActivity.class);
				intent.putExtra("evento", eventoSelecionado);
				
				startActivity(intent);
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}



}
