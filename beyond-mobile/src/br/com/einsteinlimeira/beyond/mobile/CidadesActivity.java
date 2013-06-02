package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import br.com.einsteinlimeira.beyond.mobile.model.Listas;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;
import br.com.einsteinlimeira.beyond.model.Cidade;

public class CidadesActivity extends Activity {
	
	private ListView listViewListaCidades;
  private EntidadeListAdapter<Cidade> adaptador;

  @Override
  @SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_entidade);
		
		listViewListaCidades = (ListView)findViewById(R.id.listagem_entidade_listView);
		adaptador = new EntidadeListAdapter<Cidade>(Listas.cidades, CidadesActivity.this) {
      
      @Override
      public String getText(Cidade entidade) {
        return entidade.getNome();
      }
    };
    
    Intent intentOrigem = getIntent();
    
    if(intentOrigem.hasExtra(Constantes.EXTRA_CIDADES_FILTRADAS)){
      adaptador.setIdsEntidadesSelecionadas(((ParcelableList<Integer>)intentOrigem.
          getParcelableExtra(Constantes.EXTRA_CIDADES_FILTRADAS)).getList());
    }
    
		listViewListaCidades.setAdapter(adaptador);	
	}

	/**
	 * Define o resultado via {@link #setResult(int, Intent)}.
	 * <br />.
	 * {@inheritDoc}
	 */
  @Override
  public void finish() {
    // define o resultado para a activity que chamou essa
    Intent retorno = new Intent();
    retorno.putExtra(Constantes.EXTRA_CIDADES_FILTRADAS, 
        new ParcelableList<Integer>(adaptador.getIdsEntidadesSelecionadas()));
    setResult(RESULT_OK, retorno);
    
    super.finish();
  }
}
