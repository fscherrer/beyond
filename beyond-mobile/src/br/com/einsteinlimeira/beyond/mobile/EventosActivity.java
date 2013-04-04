package br.com.einsteinlimeira.beyond.mobile;

import br.com.einsteinlimeira.beyond.mobile.model.ListaEvento;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class EventosActivity extends Activity {

	private ListView listViewListaEventos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);

		listViewListaEventos = (ListView) findViewById(R.id.lista_eventos);
		Adaptador adaptador = new Adaptador(ListaEvento.eventos, this);
		listViewListaEventos.setAdapter(adaptador);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
