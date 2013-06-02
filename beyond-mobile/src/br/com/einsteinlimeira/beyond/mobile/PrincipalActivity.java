package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;

public class PrincipalActivity extends GlobalActivity {
	
	private ImageButton botaoCasa, botaoCidade, botaoBanda, botaoEstilo;
	
	private List<Integer> idsCidadesFiltradas;
	private List<Integer> idsCasasFiltradas;
	private List<String> estilosFiltrados;
	private List<Integer> idsBandasFiltradas;
	
	private TextView textViewCidadesFiltradas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		textViewCidadesFiltradas = (TextView)findViewById(R.id.principal_textViewCidadesFiltradas);
		textViewCidadesFiltradas.setVisibility(View.INVISIBLE);
		
		botaoCasa = (ImageButton) findViewById(R.id.btn_casa);
		botaoCasa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, CasasActivity.class);
				
				startActivity(intent);
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

        default:
          Log.w(Constantes.TAG, "Request Code desconhecido: " + requestCode);
          break;
      }
	  }
	}
  
  /**
   * Reprocessa as cidades filtrada para redefinir o balãozinho com a 
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
}
