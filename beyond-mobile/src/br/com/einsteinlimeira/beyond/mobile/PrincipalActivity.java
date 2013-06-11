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
import br.com.einsteinlimeira.beyond.mobile.services.BandaServices;
import br.com.einsteinlimeira.beyond.mobile.services.CasaServices;
import br.com.einsteinlimeira.beyond.mobile.services.EventoServices;
import br.com.einsteinlimeira.beyond.mobile.util.CollectionUtilities;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;
import br.com.einsteinlimeira.beyond.model.EntidadeUtils;

public class PrincipalActivity extends GlobalActivity {
	
	private ImageButton botaoCasa, botaoCidade, botaoBanda, botaoEstilo;
	private ImageButton botaoVisualizarEventos;
	
	private List<Integer> idsCidadesFiltradas = new ArrayList<Integer>();
	private List<Integer> idsCasasFiltradas = new ArrayList<Integer>();
	private List<String> estilosFiltrados = new ArrayList<String>();
	private List<Integer> idsBandasFiltradas = new ArrayList<Integer>();

	private TextView textViewCidadesFiltradas;
	private TextView textViewCasasFiltradas;
	private TextView textViewEstilosFiltrados;
	private TextView textViewBandasFiltradas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		textViewCidadesFiltradas = (TextView) findViewById(R.id.principal_textViewCidadesFiltradas);
		textViewCidadesFiltradas.setVisibility(View.INVISIBLE);

		textViewCasasFiltradas = (TextView) findViewById(R.id.principal_textViewCasasFiltradas);
		textViewCasasFiltradas.setVisibility(View.INVISIBLE);

		textViewEstilosFiltrados = (TextView) findViewById(R.id.principal_textViewEstilosFiltrados);
		textViewEstilosFiltrados.setVisibility(View.INVISIBLE);
		
		textViewBandasFiltradas = (TextView) findViewById(R.id.principal_textViewBandasFiltradas);
		textViewBandasFiltradas.setVisibility(View.INVISIBLE);
		
		botaoCasa = (ImageButton) findViewById(R.id.btn_casa);
		botaoCasa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, CasasActivity.class);
				intent.putExtra(Constantes.EXTRA_CASAS_FILTRADAS,
						new ParcelableList<Integer>(idsCasasFiltradas));
				intent.putExtra(Constantes.EXTRA_CIDADES_FILTRADAS,
				    new ParcelableList<Integer>(idsCidadesFiltradas));
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
				intent.putExtra(Constantes.EXTRA_BANDAS_FILTRADAS,
						new ParcelableList<Integer>(idsBandasFiltradas));
        intent.putExtra(Constantes.EXTRA_ESTILOS_FILTRADOS,
            new ParcelableList<String>(estilosFiltrados));
				startActivityForResult(intent, Constantes.REQUEST_CODE_FILTRO_BANDA);
			}
		});

		botaoEstilo = (ImageButton) findViewById(R.id.btn_estilo);
		botaoEstilo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
        Intent intent = new Intent(PrincipalActivity.this, EstilosActivity.class);
        intent.putExtra(Constantes.EXTRA_ESTILOS_FILTRADOS,
            new ParcelableList<String>(estilosFiltrados));
        startActivityForResult(intent, Constantes.REQUEST_CODE_FILTRO_ESTILO);
			}
		});
		
		botaoVisualizarEventos = (ImageButton)findViewById(R.id.btn_vis_eventos);
		botaoVisualizarEventos.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        int[] idsCasasFiltrar = null;
        int[] idsBandasFiltrar = null;
        
        // casas filtradas diretamente
        if(!idsCasasFiltradas.isEmpty()){
          idsCasasFiltrar = CollectionUtilities.getArrayPrimitivo(idsCasasFiltradas);
        }
        // casas filtradas indiretamente através das Cidades filtradas
        else if(!idsCidadesFiltradas.isEmpty()){
          idsCasasFiltrar = EntidadeUtils.getIDs(new CasaServices().listar(
              PrincipalActivity.this, CollectionUtilities.getArrayPrimitivo(idsCidadesFiltradas)));
        }
        
        // bandas filtradas diretamente
        if(!idsBandasFiltradas.isEmpty()){
          idsBandasFiltrar = CollectionUtilities.getArrayPrimitivo(idsBandasFiltradas);
        }
        // bandas filtradas indiretamente através dos Estilos filtrados
        else if(!estilosFiltrados.isEmpty()){
          idsBandasFiltrar = EntidadeUtils.getIDs(new BandaServices().listar(
              PrincipalActivity.this, estilosFiltrados));
        }
        
        Intent intent = new Intent(PrincipalActivity.this, EventosActivity.class);
        
        // TODO: usar Parcelable ao invés de Serializable 
        intent.putExtra("eventos", new ArrayList<EventoSimplificadoDTO>(
            new EventoServices().listar(
                PrincipalActivity.this,
                idsCasasFiltrar,
                idsBandasFiltrar)));
        startActivity(intent);
      }
    });
		
		boolean pintarBotoesParaDepuracao = false;
    if(pintarBotoesParaDepuracao){
		  int cor = getResources().getColor(R.color.verde);
		  
		  botaoCidade.setBackgroundColor(cor);
		  botaoCasa.setBackgroundColor(cor);
		  botaoEstilo.setBackgroundColor(cor);
		  botaoBanda.setBackgroundColor(cor);
		  botaoVisualizarEventos.setBackgroundColor(cor);
		}
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
        	
        // filtro de Estilos
        case Constantes.REQUEST_CODE_FILTRO_ESTILO:
          if(data.hasExtra(Constantes.EXTRA_ESTILOS_FILTRADOS)){
            estilosFiltrados = ((ParcelableList<String>)data.getParcelableExtra
                (Constantes.EXTRA_ESTILOS_FILTRADOS)).getList();
            filtroEstilosAlterado();
          }
          break;
        	
        // filtro de Bandas
        case Constantes.REQUEST_CODE_FILTRO_BANDA:
        	if(data.hasExtra(Constantes.EXTRA_BANDAS_FILTRADAS)){
        		idsBandasFiltradas = ((ParcelableList<Integer>)data.getParcelableExtra(
        				Constantes.EXTRA_BANDAS_FILTRADAS)).getList();
        		filtroBandasAlterado();
        	}
          

        default:
          Log.w(Constantes.TAG, "Request Code desconhecido: " + requestCode);
          break;
      }
	  }
	}

	/**
	 * Reprocessa as cidades filtradas para redefinir o balãozinho com a
	 * quantidade filtrada e reseta as Casas filtradas.
	 */
	private void filtroCidadesAlterado() {
		textViewCidadesFiltradas.setVisibility(idsCidadesFiltradas.isEmpty()
		    ? View.INVISIBLE
		    : View.VISIBLE);

		textViewCidadesFiltradas.setText(idsCidadesFiltradas.isEmpty()
		    ? null
			: String.valueOf(idsCidadesFiltradas.size()));
		
		idsCasasFiltradas.clear();
		filtroCasasAlterado();
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
	
	/**
	 * Reprocessa os estilos filtradas para redefinir o balãozinho com a
	 * quantidade filtrada e reseta as Bandas filtradas..
	 */
	private void filtroEstilosAlterado() {
	  textViewEstilosFiltrados
	  .setVisibility(estilosFiltrados.isEmpty() ? View.INVISIBLE
	      : View.VISIBLE);
	  
	  textViewEstilosFiltrados.setText(estilosFiltrados.isEmpty() ? null
	      : String.valueOf(estilosFiltrados.size()));
	   
    idsBandasFiltradas.clear();
    filtroBandasAlterado();
	}
	
	 
  /**
   * Reprocessa as bandas filtradas para redefinir o balãozinho com a
   * quantidade filtrada.
   */
	private void filtroBandasAlterado(){
		textViewBandasFiltradas
			.setVisibility(idsBandasFiltradas.isEmpty() ? View.INVISIBLE
					: View.VISIBLE);
		
		textViewBandasFiltradas.setText(idsBandasFiltradas.isEmpty() ? null
				: String.valueOf(idsBandasFiltradas.size()));
	}
}
