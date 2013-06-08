package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.services.CasaServices;
import br.com.einsteinlimeira.beyond.mobile.util.CollectionUtilities;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

public class CasasActivity extends Activity {

	private ListView listViewListaCasas;
	private ListAdapterGenerico<CasaDTO, Integer> adaptador;

	@Override
	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_elemento);

		Intent intentOrigem = getIntent();
		
		int[] idsCidadesFiltradas = null;

    if (intentOrigem.hasExtra(Constantes.EXTRA_CIDADES_FILTRADAS)) {
      List<Integer> idsCidadesFiltradasList = ((ParcelableList<Integer>) intentOrigem
          .getParcelableExtra(Constantes.EXTRA_CIDADES_FILTRADAS))
          .getList();

      idsCidadesFiltradas = CollectionUtilities.getArrayPrimitivo(idsCidadesFiltradasList);

      if (idsCidadesFiltradas.length > 0) {
        TextView textViewInformacaoExtra = 
            (TextView) findViewById(R.id.listagem_elemento_textViewInformacaoExtra);
        textViewInformacaoExtra.setVisibility(View.VISIBLE);
        textViewInformacaoExtra.setText(R.string.activity_casas_informacaoFiltro);
      }
    }

		listViewListaCasas = (ListView) findViewById(R.id.listagem_elemento_listView);
    adaptador = new ListAdapterGenerico<CasaDTO, Integer>(
        new CasaServices().listar(this, idsCidadesFiltradas),
        CasasActivity.this) {

      @Override
      public String getText(CasaDTO entidade) {
        return entidade.getNome();
      }

      @Override
      public Integer getIdentificador(CasaDTO elemento) {
        return elemento.getId();
      }
    };


		// restaura seleção anterior
		if (intentOrigem.hasExtra(Constantes.EXTRA_CASAS_FILTRADAS)) {
			adaptador
					.setIdentificadoresElementosSelecionados(((ParcelableList<Integer>) intentOrigem
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
						.getIdentificadoresElementosSelecionados()));
		setResult(RESULT_OK, retorno);

		super.finish();
	}
}
