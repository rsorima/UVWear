package com.example.uvweariot;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.security.Permission;
import java.security.Permissions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment implements LocationListener {
    @Nullable
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_LOCATION = 123;
    protected LocationManager locationManager;
    View curView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        curView = inflater.inflate(R.layout.fragment_home, container, false); //View Creation

        //region Getting the date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        TextView dateText = curView.findViewById(R.id.DateView);
        String stringDate = dateFormat.format(calendar.getTime());
        dateText.setText(stringDate);
        //endregion

        //region Permission Request
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED){
            requestPermissions( new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.BLUETOOTH_ADMIN}, REQUEST_LOCATION);
        };
        //endregion

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

        return curView;
    }

    @Override
    public void onLocationChanged(Location location) {
        final TextView locationText = curView.findViewById(R.id.LocationView);
        locationText.setText("Latitude: " + location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
