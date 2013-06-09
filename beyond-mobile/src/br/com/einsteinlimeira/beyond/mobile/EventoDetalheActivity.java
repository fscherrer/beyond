package br.com.einsteinlimeira.beyond.mobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.model.EventoDetalhadoDTO;
import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.mobile.util.EntidadeUtils;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventoDetalheActivity extends FragmentActivity {

  private CheckBox checkBoxExibirLocalizacao;
  private MarkerOptions markerOptions;
  private GoogleMap googleMap;
  private LocationListener locationListener;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_evento_detalhe);
    checkBoxExibirLocalizacao = (CheckBox) findViewById(R.id.evento_checkBoxLocalizacao);
    
    // só habilita se conseguir obter a localização do usuário
    checkBoxExibirLocalizacao.setEnabled(true);

    final EventoDetalhadoDTO evento = (EventoDetalhadoDTO) getIntent().getExtras()
        .getSerializable("evento");

    Resources resources = getResources();

    ((TextView) findViewById(R.id.evento_texto_titulo)).setText(evento
        .getNome());

    final CasaDTO casa = evento.getCasa();

    ((TextView) findViewById(R.id.evento_texto_casa)).setText(casa.getNome());

    ((TextView) findViewById(R.id.evento_texto_local_logradouro))
        .setText(resources.getString(R.string.evento_local_logradouro,
            casa.getLogradouro(), casa.getNumero(), casa.getBairro()));

    ((TextView) findViewById(R.id.evento_texto_local_cidade)).setText(resources
        .getString(R.string.evento_local_cidade, evento.getCidade(),
            evento.getSiglaUf(), casa.getCep()));

    ((TextView) findViewById(R.id.evento_texto_banda)).setText(resources
        .getString(R.string.evento_banda,
            EntidadeUtils.bandasToString(evento.getBandas())));

    ((TextView) findViewById(R.id.evento_texto_data)).setText(resources
        .getString(R.string.evento_data,
            DateUtils.dateHourFormat.format(evento.getDataHora())));

    ((TextView) findViewById(R.id.evento_texto_valor)).setText(resources
        .getString(R.string.evento_valor, evento.getValor()));
    
    View botaoCompartilhar = findViewById(R.id.evento_compartilhar);
    botaoCompartilhar.setOnClickListener(new OnClickListener() {
      
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
            getResources().getString(R.string.evento_compartilhar_assunto, casa.getNome(), evento.getNome()));
        intent.putExtra(android.content.Intent.EXTRA_TEXT, 
            getResources().getString(R.string.evento_compartilhar_texto, evento.getNome(),
                DateUtils.dateHourFormat.format(evento.getDataHora()),
                EntidadeUtils.bandasToString(evento.getBandas())));
        startActivity(Intent.createChooser(intent, getResources().getString(R.string.evento_compartilhar_via)));
      }
    });

    // Mapa
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.evento_mapa);

    boolean exibirMapa = false;

    String coordenada = casa.getCoordenada();

    if (coordenada != null && coordenada.trim().length() > 0) {
      String[] partesCoordenada = casa.getCoordenada().split(",");

      try {
        LatLng latLng = new LatLng(Double.parseDouble(partesCoordenada[0]),
            Double.parseDouble(partesCoordenada[1]));

        googleMap = mapFragment.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        googleMap.addMarker(new MarkerOptions().position(latLng)
            .title(casa.getNome()).snippet(evento.getNome()));

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        exibirMapa = true;
      }
      catch (NumberFormatException nfe) {

      }
    }

    if (exibirMapa) {
      // localização
      LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

      markerOptions = new MarkerOptions();

      locationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
          Log.i(Constantes.TAG, "Location: " + location.getLatitude() + ", "
              + location.getLongitude());
          markerOptions.position(new LatLng(location.getLatitude(), location
              .getLongitude()));
        }

        @Override
        public void onProviderDisabled(String provider) {
          Log.i(Constantes.TAG, "Provider desabilitado: " + provider);
        }

        @Override
        public void onProviderEnabled(String provider) {
          Log.i("loc", "Provider habilitado: " + provider);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
          Log.i(Constantes.TAG, "Status: " + status);
        }
      };

      locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
          0, locationListener);
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
          0, locationListener);
      
      // seleciona localização atual
      checkBoxExibirLocalizacao.setOnCheckedChangeListener(new OnCheckedChangeListener() {
        private Marker marker;
        private boolean markerAdicionado;

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

          if (isChecked) {
            // posição determinada
            if (markerOptions.getPosition() != null) {
              Log.i(Constantes.TAG, "Adicionado marcador da localização atual");
              marker = googleMap.addMarker(markerOptions
                  .title(getResources().getString(R.string.evento_minhaLocalizacao)));

              googleMap.animateCamera(CameraUpdateFactory.newLatLng(markerOptions.getPosition()));

              markerAdicionado = true;
            }
            // posição indeterminada
            else {
              new AlertDialog.Builder(EventoDetalheActivity.this).
                  setMessage(R.string.evento_localizacaoNaoDisponivel).
                  setCancelable(true).
                  setPositiveButton(R.string.evento_configurar, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                  }).
                  setNegativeButton(R.string.global_cancelar, new DialogInterface.OnClickListener() {
                    
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      // não faz nada
                    }
                  }).
                  create().
                  show();
              checkBoxExibirLocalizacao.setChecked(false);
            }
          }
          else if (markerAdicionado) {
            Log.i(Constantes.TAG, "Removido marcador da localização atual");
            marker.remove();
            
            markerAdicionado = false;
          }
        }
      });
    }
    // vai exibir "Mapa não definido"
    else {
      ((LinearLayout) findViewById(R.id.evento_linearLayout_mapa))
          .setVisibility(View.INVISIBLE);
      checkBoxExibirLocalizacao.setVisibility(View.INVISIBLE);
      ((TextView) findViewById(R.id.evento_texto_mapaNaoDefinido))
          .setVisibility(View.VISIBLE);
    }
  }
}
