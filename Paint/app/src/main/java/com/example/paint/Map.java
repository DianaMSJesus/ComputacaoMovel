package com.example.paint;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    //FusedLocationProviderClient user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        //user = LocationServices.getFusedLocationProviderClient(this);

        //getCurrentLocation();
       /* if (ActivityCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }else{
            ActivityCompat.requestPermissions(Map.this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},44);
        }*/

        supportMapFragment.getMapAsync(this);
    }

   /* private void getCurrentLocation() {
        //if (ActivityCompat.checkSelfPermission(Map.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Task<Location> task = user.getLastLocation();
            task.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(@NonNull GoogleMap googleMap) {
                                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                                MarkerOptions options = new MarkerOptions().position(latLng).title("I'm here");
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                                googleMap.addMarker(options);
                            }
                        });
                    }
                }
            });
        // }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }*/

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }
}