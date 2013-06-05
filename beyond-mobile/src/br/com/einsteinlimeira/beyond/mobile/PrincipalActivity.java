package br.com.einsteinlimeira.beyond.mobile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.model.EventoSimplificadoDTO;
import br.com.einsteinlimeira.beyond.mobile.services.EventoServices;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;

public class PrincipalActivity extends GlobalActivity {
	
	private ImageButton botaoCasa, botaoCidade, botaoBanda, botaoEstilo;
	private ImageButton botaoVisualizarEventos;
	
	private List<Integer> idsCidadesFiltradas;
	private List<Integer> idsCasasFiltradas;
	private List<String> estilosFiltrados;
	private List<Integer> idsBandasFiltradas;

	private TextView textViewCidadesFiltradas;
	private TextView textViewCasasFiltradas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		textViewCidadesFiltradas = (TextView) findViewById(R.id.principal_textViewCidadesFiltradas);
		textViewCidadesFiltradas.setVisibility(View.INVISIBLE);

		textViewCasasFiltradas = (TextView) findViewById(R.id.principal_textViewCasasFiltradas);
		textViewCasasFiltradas.setVisibility(View.INVISIBLE);

		botaoCasa = (ImageButton) findViewById(R.id.btn_casa);
		botaoCasa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, CasasActivity.class);
				intent.putExtra(Constantes.EXTRA_CASAS_FILTRADAS,
						new ParcelableList<Integer>(idsCasasFiltradas));
				startActivityForResult(intent, Constantes.REQUEST_CODE_FILTRO_CASA);
			}
		});

		botaoCidade = (ImageButton) findViewById(R.id.btn_cidade);
		botaoCidade.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, CidadesActivity.class);
				intent.putExtra(Constantes.EXTRA_CIDADES_FILTRADAS,
				    new ParcelableList<Integer>(idsCidadesFiltradas));
				startActivityForResult(intent, Constantes.REQUEST_CODE_FILTRO_CIDADE);
			}
		});

		botaoBanda = (ImageButton) findViewById(R.id.btn_banda);
		botaoBanda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, BandaActivity.class);
				startActivity(intent);
			}
		});

		botaoEstilo = (ImageButton) findViewById(R.id.btn_estilo);
		botaoEstilo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO intent for open activity estilo.

			}
		});
		
		botaoVisualizarEventos = (ImageButton)findViewById(R.id.btn_vis_eventos);
		botaoVisualizarEventos.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(PrincipalActivity.this, EventosActivity.class);
        // TODO: aplicar filtros
        // TODO: usar Parcelable ao invés de Serializable 
        intent.putExtra("eventos", new ArrayList<EventoSimplificadoDTO>(
            new EventoServices().listar(PrincipalActivity.this)));
        startActivity(intent);
      }
    });
	}

	@Override
  @SuppressWarnings("unchecked")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if(resultCode == RESULT_OK){
	    switch (requestCode) {
	    // filtro de Cidades
        case Constantes.REQUEST_CODE_FILTRO_CIDADE:
          if(data.hasExtra(Constantes.EXTRA_CIDADES_FILTRADAS)){
            idsCidadesFiltradas = ((ParcelableList<Integer>)data.getParcelableExtra(
                Constantes.EXTRA_CIDADES_FILTRADAS)).getList();
            filtroCidadesAlterado();           
          }
          break;
        // filtro de Casas
        case Constantes.REQUEST_CODE_FILTRO_CASA:
        	if(data.hasExtra(Constantes.EXTRA_CASAS_FILTRADAS)){
        		idsCasasFiltradas = ((ParcelableList<Integer>)data.getParcelableExtra
        				(Constantes.EXTRA_CASAS_FILTRADAS)).getList();
        		filtroCasasAlterado();
        	}
        	break;
          

        default:
          Log.w(Constantes.TAG, "Request Code desconhecido: " + requestCode);
          break;
      }
	  }
	}

	/**
	 * Reprocessa as cidades filtradas para redefinir o balãozinho com a
	 * quantidade filtrada.
	 */
	private void filtroCidadesAlterado() {
		textViewCidadesFiltradas.setVisibility(idsCidadesFiltradas.isEmpty()
		    ? View.INVISIBLE
		    : View.VISIBLE);

		textViewCidadesFiltradas.setText(idsCidadesFiltradas.isEmpty()
		    ? null
			: String.valueOf(idsCidadesFiltradas.size()));
	}
	
	/**
	 * Reprocessa as casas filtradas para redefinir o balãozinho com a
	 * quantidade filtrada.
	 */
	private void filtroCasasAlterado() {
		textViewCasasFiltradas
				.setVisibility(idsCasasFiltradas.isEmpty() ? View.INVISIBLE
						: View.VISIBLE);

		textViewCasasFiltradas.setText(idsCasasFiltradas.isEmpty() ? null
				: String.valueOf(idsCasasFiltradas.size()));
	}
}
