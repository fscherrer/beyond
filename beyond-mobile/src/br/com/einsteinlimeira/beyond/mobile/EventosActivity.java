package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.einsteinlimeira.beyond.mobile.util.EntidadeUtils;
import br.com.einsteinlimeira.beyond.model.Evento;

public class EventosActivity extends GlobalActivity {

	private ListView listViewListaEventos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);
		
		@SuppressWarnings("unchecked")
		List<Evento> eventos = (List<Evento>) getIntent().getExtras().getSerializable("eventos");
		
		listViewListaEventos = (ListView) findViewById(R.id.lista_eventos);
		Adaptador adaptador = new Adaptador(eventos, this);
		listViewListaEventos.setAdapter(adaptador);

		listViewListaEventos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Evento eventoSelecionado = (Evento) listViewListaEventos.getAdapter().getItem(position);
				
				Log.i(Constantes.TAG, "Evento selecionado: " + EntidadeUtils.bandasToString(eventoSelecionado.getBandas()));
				
				Intent intent = new Intent(EventosActivity.this, EventoDetalheActivity.class);
				intent.putExtra("evento", eventoSelecionado);
				
				startActivity(intent);
			}
		});
	}
}
