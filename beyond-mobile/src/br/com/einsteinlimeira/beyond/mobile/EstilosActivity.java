package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import br.com.einsteinlimeira.beyond.mobile.services.BandaServices;
import br.com.einsteinlimeira.beyond.mobile.util.ParcelableList;

public class EstilosActivity extends Activity {

  private ListView listViewListaCidades;
  private ListAdapterGenerico<String, String> adaptador;

  @Override
  @SuppressWarnings("unchecked")
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.listagem_elemento);

    listViewListaCidades = (ListView) findViewById(R.id.listagem_elemento_listView);
    adaptador = new ListAdapterGenerico<String, String>(
        new BandaServices().getEstilos(EstilosActivity.this),
        EstilosActivity.this) {

      @Override
      public String getText(String elemento) {
        return elemento;
      }

      @Override
      public String getIdentificador(String elemento) {
        return elemento;
      }
    };

    Intent intentOrigem = getIntent();

    if (intentOrigem.hasExtra(Constantes.EXTRA_ESTILOS_FILTRADOS)) {
      adaptador.setIdentificadoresElementosSelecionados(((ParcelableList<String>) intentOrigem.
          getParcelableExtra(Constantes.EXTRA_ESTILOS_FILTRADOS)).getList());
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
    retorno.putExtra(Constantes.EXTRA_ESTILOS_FILTRADOS,
        new ParcelableList<String>(adaptador.getIdentificadoresElementosSelecionados()));
    setResult(RESULT_OK, retorno);

    super.finish();
  }
}
