package com.cristian.ciudadcity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MostrarDatosActivity extends AppCompatActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Bundle parametros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        parametros = getIntent().getExtras();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        String lactitud = parametros.getString("lactitud");
        String longitud = parametros.getString("longitud");
        String nombre = parametros.getString("nombre");
        UiSettings uiSettings = mMap.getUiSettings();
        LatLng coordenada = new LatLng(Double.parseDouble(lactitud), Double.parseDouble(longitud));
        mMap.addMarker(new MarkerOptions().position(coordenada).title(nombre));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordenada, 15));
    }
}
