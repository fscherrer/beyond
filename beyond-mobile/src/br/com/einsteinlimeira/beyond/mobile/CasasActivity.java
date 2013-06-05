package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import br.com.einsteinlimeira.beyond.mobile.services.CasaServices;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

public class CasasActivity extends Activity {

	private ListView listViewListaCasas;
	private EntidadeListAdapter<CasaDTO> adaptador;

	@Override
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_entidade);

		listViewListaCasas = (ListView) findViewById(R.id.listagem_entidade_listView);
		adaptador = new EntidadeListAdapter<CasaDTO>(
				new CasaServices().listar(this),
				CasasActivity.this) {

			@Override
			public String getText(CasaDTO entidade) {
				return entidade.getNome();
			}
		};

		Intent intentOrigem = getIntent();

		if (intentOrigem.hasExtra(Constantes.EXTRA_CASAS_FILTRADAS)) {
			adaptador
					.setIdsEntidadesSelecionadas(((ParcelableList<Integer>) intentOrigem
							.getParcelableExtra(Constantes.EXTRA_CASAS_FILTRADAS))
							.getList());
		}

		listViewListaCasas.setAdapter(adaptador);
	}

	/**
	 * Define o resultado via {@link #setResult(int, Intent)}. <br />
	 * . {@inheritDoc}
	 */
	@Override
	public void finish() {
		// define o resultado para a activity que chamou essa
		Intent retorno = new Intent();
		retorno.putExtra(
				Constantes.EXTRA_CASAS_FILTRADAS,
				new ParcelableList<Integer>(adaptador
						.getIdsEntidadesSelecionadas()));
		setResult(RESULT_OK, retorno);

		super.finish();
	}
}
