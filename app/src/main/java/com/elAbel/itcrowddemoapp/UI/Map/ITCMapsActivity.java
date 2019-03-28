package com.elAbel.itcrowddemoapp.UI.Map;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.elAbel.itcrowddemoapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ITCMapsActivity extends FragmentActivity implements OnMapReadyCallback {
    public final static String MAP_LAT = "lat";
    public final static String MAP_LON = "lon";
    public final static String MAP_CITY_NAME = "city";

    private GoogleMap mMap;

    private ITCMapsViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itcmaps);

        vm = ViewModelProviders.of(this).get(ITCMapsViewModel.class);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            vm.setLat(bundle.getDouble(MAP_LAT));
            vm.setLon(bundle.getDouble(MAP_LON));
            vm.setCityName(bundle.getString(MAP_CITY_NAME));
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(vm.getLat(), vm.getLon());
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in " + vm.getCityName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
