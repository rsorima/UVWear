package com.example.uvweariot;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
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

import java.io.IOException;
import java.security.Permission;
import java.security.Permissions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment{
    @Nullable

    View curView;
    TextView locationText;
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

        Bundle bundle = this.getArguments();
        String fullAddress;
        fullAddress = bundle.getString("fullAddress");
        locationText = curView.findViewById(R.id.LocationView);
        locationText.setText(fullAddress);
        return curView;
    }


}
