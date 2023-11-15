package edu.fandm.pcettina.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Location_activity extends AppCompatActivity {

    private String TAG = "locTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        boolean hasCoarse = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        boolean hasFine = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(hasCoarse == false || hasFine == false){

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }




    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull android.location.Location location) {
            TextView loc_tv = (TextView) findViewById(R.id.Location_textv);
            loc_tv.setText(getLocationName(location));
            Log.d(TAG, "Location Changed!: " + location);
        }


    };

    public void turnOnLocationUpdating(View view){

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        try{

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } catch (SecurityException se){
            Log.d(TAG, "Couldn't turn on location listener due to security exception");
            se.printStackTrace();
        }

    }
    private String getLocationName(Location location){
        Geocoder gc = new Geocoder(this, Locale.getDefault());

        StringBuilder sb = new StringBuilder();
        try{
            int n = 4;
            List<Address> addrs = gc.getFromLocation(location.getLatitude(), location.getLongitude(), n);

            for(int i = 0; i < n; i++){
                sb.append(addrs.get(i).getLocality());
                sb.append(" : ");
                sb.append(addrs.get(i).getAddressLine(0));
                sb.append("\n");

            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        return sb.toString();
    }
}