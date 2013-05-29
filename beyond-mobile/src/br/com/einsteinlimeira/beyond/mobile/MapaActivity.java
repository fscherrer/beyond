package br.com.einsteinlimeira.beyond.mobile;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mapa);

    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        .findFragmentById(R.id.mapa);

    GoogleMap googleMap = mapFragment.getMap();
    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

    googleMap.addMarker(new MarkerOptions()
        .position(new LatLng(-22.546563, -47.387727))
        .title("Teste")
        .snippet("Snipet!"));

    googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-22.546563,-47.387727)));
    googleMap.moveCamera(CameraUpdateFactory.zoomTo(16));
  }
}
