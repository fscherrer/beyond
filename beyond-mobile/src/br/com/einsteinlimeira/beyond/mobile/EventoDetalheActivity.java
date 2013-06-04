package br.com.einsteinlimeira.beyond.mobile;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
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

	private CheckBox checkloc;
	private MarkerOptions markerOptions;
	private GoogleMap googleMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evento_detalhe);
		checkloc = (CheckBox) findViewById(R.id.check_loc);

		Evento evento = (Evento) getIntent().getExtras().getSerializable(
				"evento");

		Resources resources = getResources();

		((TextView) findViewById(R.id.evento_texto_titulo)).setText(evento
				.getNome());

		Casa casa = evento.getCasa();
		Endereco endereco = casa.getEndereco();

		((TextView) findViewById(R.id.evento_texto_casa)).setText(casa
				.getNome());

		((TextView) findViewById(R.id.evento_texto_local_logradouro))
				.setText(resources.getString(R.string.evento_local_logradouro,
						endereco.getLogradouro(), endereco.getNumero(),
						endereco.getBairro()));

		((TextView) findViewById(R.id.evento_texto_local_cidade))
				.setText(resources.getString(R.string.evento_local_cidade,
						endereco.getCidade().getNome(), endereco.getCidade()
								.getUf().getSigla(), endereco.getCep()));

		((TextView) findViewById(R.id.evento_texto_banda)).setText(resources
				.getString(R.string.evento_banda,
						EntidadeUtils.bandasToString(evento.getBandas())));

		((TextView) findViewById(R.id.evento_texto_data)).setText(resources
				.getString(R.string.evento_data,
						DateUtils.dateHourFormat.format(evento.getDatahora())));

		((TextView) findViewById(R.id.evento_texto_valor)).setText(resources
				.getString(R.string.evento_valor, evento.getValor()));

		// Mapa

		// localização
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		markerOptions = new MarkerOptions();

		Log.i("loc", "Rodando");
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						Log.i("loc", "Location: " + location.getLatitude()
								+ ", " + location.getLongitude());
						markerOptions.position(new LatLng(location
								.getLatitude(), location.getLongitude()));
					}

					@Override
					public void onProviderDisabled(String provider) {
						Log.i("loc", "Provider desabilitado: " + provider);

					}

					@Override
					public void onProviderEnabled(String provider) {
						Log.i("loc", "Provider habilitado: " + provider);

					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						Log.i("loc", "Status: " + status);

					}

				});

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.evento_mapa);

		boolean exibirMapa = false;

		String coordenada = endereco.getCoordenada();

		if (coordenada != null && coordenada.trim().length() > 0) {
			String[] partesCoordenada = endereco.getCoordenada().split(",");

			try {
				LatLng latLng = new LatLng(
						Double.parseDouble(partesCoordenada[0]),
						Double.parseDouble(partesCoordenada[1]));

				GoogleMap googleMap = mapFragment.getMap();
				googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

				googleMap.addMarker(new MarkerOptions().position(latLng)
						.title(casa.getNome()).snippet(evento.getNome()));

				googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
				googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));

				exibirMapa = true;
			} catch (NumberFormatException nfe) {

			}
			// seleciona localização atual
			checkloc.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (checkloc.isChecked()) {
						Log.i("loc", "Adicionado marcador");
						googleMap.addMarker(markerOptions);

					} else
						remove();
				}

				private void remove() {
					Log.i("loc", "Removido marcador");
					googleMap.clear();
				}

			});

		}
		// vai exibir "Mapa nÃ£o definido"
		if (!exibirMapa) {
			((LinearLayout) findViewById(R.id.evento_linearLayout_mapa))
					.setVisibility(View.INVISIBLE);
			((TextView) findViewById(R.id.evento_texto_mapaNaoDefinido))
					.setVisibility(View.VISIBLE);
		}
	}
}
