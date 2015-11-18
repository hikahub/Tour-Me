package com.example.queene.tourmeapp.adapter;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Queene on 11/11/2015.
 */
public class Locations implements LocationListener {
    private LocationManager locationManager;
    private String provider;
    View activity;
    double lat;
    double lng;

    /** Called when the activity is first created. */

    public Locations(View Test)
    {
        activity = Test;

        locationManager = (LocationManager) activity.getContext().getSystemService(Context.LOCATION_SERVICE);


        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);


        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        }
        else {
            Log.d("Please", "Location not available");
            Log.d("Please", "Location not available");
        }
    }

    protected void onResume() {
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }



    protected void onPause() {
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        lat = (double) (location.getLatitude());
        lng = (double) (location.getLongitude());

    }


    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(activity.getContext(), "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(activity.getContext(), "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
