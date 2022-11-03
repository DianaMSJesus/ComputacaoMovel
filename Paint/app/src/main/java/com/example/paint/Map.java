package com.example.paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;


public class Map extends AppCompatActivity implements OnMapReadyCallback{

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient user;
    GoogleMap googleMap;
    LatLng currentPossition;
    Polyline polylineDraw;

    boolean validation;

    ArrayList<LatLng> polylineList = new ArrayList<>();
    Button btnStop,btnStart;
    Integer draw = 0;

    LocationManager locationManager;
    private LocationListener locationListener = this::getCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        btnStop = findViewById(R.id.btn_stopDrawing);
        btnStart = findViewById(R.id.btn_startDrawing);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        supportMapFragment.getMapAsync(this);
        user = LocationServices.getFusedLocationProviderClient(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    }

    private void getCurrentLocation(Location location) {
        try {
            if (validation){
                if(location != null){
                    LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    currentPossition = latLng;
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    polylineList.add(currentPossition);
                    if(draw == 1){
                        if(polylineDraw != null){
                            polylineDraw.remove();
                        }
                        polylineDraw = googleMap.addPolyline(new PolylineOptions().clickable(false).addAll(polylineList));
                    }
                }
            }
        }catch (SecurityException e){}

    }

    public void validations(){
        if (ActivityCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            validation = true;
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3000,5,locationListener);
        }else{
            ActivityCompat.requestPermissions(Map.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    public void startDrawing(View view){
        btnStart.setVisibility(View.GONE);
        btnStop.setVisibility(View.VISIBLE);
        draw = 1;
    }

    public void stopDrawing(View view){
        btnStart.setVisibility(View.VISIBLE);
        btnStop.setVisibility(View.GONE);
        polylineList = new ArrayList<>();
        draw = 0;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        validations();
        try {
            if (validation){
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            }else{
                googleMap.setMyLocationEnabled(false);
                googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                validations();
            }
        }catch (SecurityException e){}

    }

}