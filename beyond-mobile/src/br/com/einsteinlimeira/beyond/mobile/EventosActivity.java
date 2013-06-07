package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.einsteinlimeira.beyond.mobile.model.EventoSimplificadoDTO;
import br.com.einsteinlimeira.beyond.mobile.services.EventoServices;

public class EventosActivity extends GlobalActivity {

	private ListView listaEventos;
	private AdaptadorEvento adaptador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);
		
		@SuppressWarnings("unchecked")
		List<EventoSimplificadoDTO> eventos = (List<EventoSimplificadoDTO>) getIntent().getExtras().getSerializable("eventos");
		
		listaEventos = (ListView) findViewById(R.id.lista_eventos);
		adaptador = new AdaptadorEvento(eventos, this);
		listaEventos.setAdapter(adaptador);

		listaEventos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				EventoSimplificadoDTO eventoSelecionado = (EventoSimplificadoDTO) listaEventos.getAdapter().getItem(position);
				
				Intent intent = new Intent(EventosActivity.this, EventoDetalheActivity.class);
				intent.putExtra("evento", new EventoServices().getDetalhes(
				    eventoSelecionado.getId(), EventosActivity.this));
				
				startActivity(intent);
			}
		});
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//	  super.onCreateOptionsMenu(menu);
//	  
//	  MenuItem menuItem = menu.add(R.string.global_atualizar);
//	  menuItem.setOnMenuItemClickListener(new OnMenuItemClickListener() {
//      
//      @Override
//      public boolean onMenuItemClick(MenuItem item) {
//        atualizarEventos();
//        return true;
//      }
//    });
//	  
//	  return true;
//	}
//	
//	private void atualizarEventos(){
//	  new ObtemEventosAsyncTask(this) {
//      private ProgressDialog progressDialog;
//
//      @Override
//      protected void onPreExecute() {
//        progressDialog = new ProgressDialog(EventosActivity.this);
//        progressDialog.setMessage(getResources().getString(
//            R.string.global_aguarde));
//        progressDialog.show();
//      }
//
//      @Override
//      protected void onPostExecute(ArrayList<Evento> result) {
//        progressDialog.dismiss();
//
//        if (problema) {
//          Toast.makeText(EventosActivity.this,
//              getResources().getString(R.string.global_erro_requisicao), Toast.LENGTH_LONG)
//              .show();
//        }
//        else {
//          adaptador.setEventos(result);
//          adaptador.notifyDataSetChanged();
//        }
//      }
//    }.execute();
//	}
}
