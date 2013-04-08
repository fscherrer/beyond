package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.einsteinlimeira.beyond.mobile.model.ListaEvento;
import br.com.einsteinlimeira.beyond.model.Evento;

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
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	  switch (item.getItemId()) {
      case R.id.menu_configuracoes:
        exibirActivityConfiguracoes();
        break;
    }
	  
	  return true;
	}
	
	/**
	 * Exibe a Activity para realização de configurações.
	 */
	private void exibirActivityConfiguracoes(){
	  Intent intent = new Intent(this, ConfiguracaoActivity.class);
	  startActivity(intent);
	}
}
