package br.com.einsteinlimeira.beyond.mobile;

import br.com.einsteinlimeira.beyond.mobile.services.BandaServices;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;
import android.os.Bundle;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;

public class BandaActivity extends Activity {

	private ListView listViewListaBandas;
	private EntidadeListAdapter<BandaDTO> adaptador;
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_entidade);
	
		listViewListaBandas = (ListView)findViewById(R.id.listagem_entidade_listView);
		adaptador = new EntidadeListAdapter<BandaDTO>(
				new BandaServices().listar(this), BandaActivity.this) {
			
			@Override
			public String getText(BandaDTO entidade) {
				// TODO Auto-generated method stub
				return entidade.getNome();
			}
		};
		
		Intent intentOrigem = getIntent();
		
		if(intentOrigem.hasExtra(Constantes.EXTRA_BANDAS_FILTRADAS)){
			adaptador.setIdsEntidadesSelecionadas(((ParcelableList<Integer>) intentOrigem
					.getParcelableExtra(Constantes.EXTRA_BANDAS_FILTRADAS))
					.getList());
		}
		
		listViewListaBandas.setAdapter(adaptador);
	}
	
	/**
	 * Define o resultado  via {@link #setResult(int, intent)}. <br />
	 * . {@inheritDoc}
	 */
	@Override
	public void finish() {
		//define o resultado para a activity que chamou essa
		Intent retorno = new Intent();
		retorno.putExtra(Constantes.EXTRA_BANDAS_FILTRADAS,
				new ParcelableList<Integer>(adaptador.
						getIdsEntidadesSelecionadas()));
		setResult(RESULT_OK, retorno);
		super.finish();
	}
}
