package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.services.BandaServices;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;

public class BandaActivity extends Activity {

  private ListView listViewListaBandas;
  private ListAdapterGenerico<BandaDTO, Integer> adaptador;

  @SuppressWarnings("unchecked")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.listagem_elemento);

    Intent intentOrigem = getIntent();

    List<String> estilosFiltrados = null;

    if (intentOrigem.hasExtra(Constantes.EXTRA_ESTILOS_FILTRADOS)) {
      estilosFiltrados = ((ParcelableList<String>) intentOrigem
          .getParcelableExtra(Constantes.EXTRA_ESTILOS_FILTRADOS))
          .getList();

      if (!estilosFiltrados.isEmpty()) {
        TextView textViewInformacaoExtra =
            (TextView) findViewById(R.id.listagem_elemento_textViewInformacaoExtra);
        textViewInformacaoExtra.setVisibility(View.VISIBLE);
        textViewInformacaoExtra.setText(R.string.activity_bandas_informacaoFiltro);
      }
    }

    listViewListaBandas = (ListView) findViewById(R.id.listagem_elemento_listView);
    adaptador = new ListAdapterGenerico<BandaDTO, Integer>(
        new BandaServices().listar(this, estilosFiltrados), BandaActivity.this) {

      @Override
      public String getText(BandaDTO entidade) {
        return entidade.getNome();
      }

      @Override
      public Integer getIdentificador(BandaDTO elemento) {
        return elemento.getId();
      }
    };

    if (intentOrigem.hasExtra(Constantes.EXTRA_BANDAS_FILTRADAS)) {
      adaptador.setIdentificadoresElementosSelecionados(((ParcelableList<Integer>) intentOrigem
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
    // define o resultado para a activity que chamou essa
    Intent retorno = new Intent();
    retorno.putExtra(Constantes.EXTRA_BANDAS_FILTRADAS,
        new ParcelableList<Integer>(adaptador.
            getIdentificadoresElementosSelecionados()));
    setResult(RESULT_OK, retorno);
    super.finish();
  }
}
