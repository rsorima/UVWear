package com.example.uvweariot;

import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment{
    @Nullable
    private FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View curView = inflater.inflate(R.layout.fragment_home, container, false); //View Creation

        //region Getting the date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        TextView dateText = curView.findViewById(R.id.DateView);
        String stringDate = dateFormat.format(calendar.getTime());
        dateText.setText(stringDate);
        //endregion
        final TextView locationText = curView.findViewById(R.id.LocationView);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    locationText.setText("Location Acquired");
                }
            }
        });

        return curView;
    }
}
