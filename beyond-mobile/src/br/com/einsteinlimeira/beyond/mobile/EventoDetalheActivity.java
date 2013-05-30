package br.com.einsteinlimeira.beyond.mobile;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.mobile.util.EntidadeUtils;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Endereco;
import br.com.einsteinlimeira.beyond.model.Evento;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventoDetalheActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evento_detalhe);

		Evento evento = (Evento) getIntent().getExtras().getSerializable(
				"evento");

		Resources resources = getResources();

		((TextView) findViewById(R.id.evento_texto_titulo)).setText(evento
				.getNome());
		
		Casa casa = evento.getCasa();
		Endereco endereco = casa.getEndereco();

		((TextView) findViewById(R.id.evento_texto_casa)).setText(casa.getNome());
		
		((TextView) findViewById(R.id.evento_texto_local_logradouro)).setText(resources
				.getString(R.string.evento_local_logradouro, endereco.getLogradouro(),
						endereco.getNumero(), endereco.getBairro()));
		
		((TextView) findViewById(R.id.evento_texto_local_cidade)).setText(resources
				.getString(R.string.evento_local_cidade, endereco.getCidade().getNome(),
						endereco.getCidade().getUf().getSigla(), endereco.getCep()));

		((TextView) findViewById(R.id.evento_texto_banda)).setText(resources
				.getString(R.string.evento_banda,
						EntidadeUtils.bandasToString(evento.getBandas())));

		((TextView) findViewById(R.id.evento_texto_data)).setText(resources
				.getString(R.string.evento_data,
						DateUtils.dateHourFormat.format(evento.getDatahora())));

		((TextView) findViewById(R.id.evento_texto_valor)).setText(resources
				.getString(R.string.evento_valor, evento.getValor()));
		
		// Mapa
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
		    .findFragmentById(R.id.evento_mapa);
		
		boolean exibirMapa = false;
		
    String coordenada = endereco.getCoordenada();
    
    if (coordenada != null && coordenada.trim().length() > 0) {
      String[] partesCoordenada = endereco.getCoordenada().split(",");

      try {
        LatLng latLng = new LatLng(Double.parseDouble(partesCoordenada[0]),
            Double.parseDouble(partesCoordenada[1]));

        GoogleMap googleMap = mapFragment.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions()
            .position(latLng)
            .title(casa.getNome())
            .snippet(evento.getNome()));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));
        
        exibirMapa = true;
      }
      catch (NumberFormatException nfe) {
        // vai exibir "Mapa não definido"
      }
    }
    
    if(!exibirMapa){
      ((LinearLayout)findViewById(R.id.evento_linearLayout_mapa)).setVisibility(View.INVISIBLE);
      ((TextView)findViewById(R.id.evento_texto_mapaNaoDefinido)).setVisibility(View.VISIBLE);
    }
	}
}
