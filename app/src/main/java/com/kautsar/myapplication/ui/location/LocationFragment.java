package com.kautsar.myapplication.ui.location;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kautsar.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * NIM        : 10121055
 * Nama     : Kautsar Teguh Dwi Putra
 * Kelas       :  IF-4
 */
public class LocationFragment extends Fragment {

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        @Override
        public void onMapReady(GoogleMap googleMap) {
            getCurrentLocation(googleMap);
        }
    };

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationCallback locationCallback;
    private List<MarkerOptions> markerOptionsList;

    private static final int REQUEST_LOCATION_PERMISSION = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_location, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext());
            mapFragment.getMapAsync(callback);
        }
    }

    private void getCurrentLocation(GoogleMap googleMap) {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            return;
        }

        googleMap.setMyLocationEnabled(true);

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //locationRequest.setInterval(5000); // Update location every 5 seconds

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

                for (Location location : locationResult.getLocations()) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
                }
            }
        };

        // Add custom markers with coordinates and labels
        markerOptionsList = new ArrayList<>();
        markerOptionsList.add(new MarkerOptions().position(new LatLng(-6.884715109267266, 107.61354061168979)).title("MCD Simpang Dago"));
        markerOptionsList.add(new MarkerOptions().position(new LatLng(-6.887668911730548, 107.61513788498154)).title("Richeese Factory Dipatiukur"));
        markerOptionsList.add(new MarkerOptions().position(new LatLng(-6.885600553718818, 107.61307256022614)).title("Warunk Upnormal Sumur Bandung"));
        markerOptionsList.add(new MarkerOptions().position(new LatLng(-6.884715819792712, 107.61640839088064)).title("Dapur Eyang"));
        markerOptionsList.add(new MarkerOptions().position(new LatLng(-6.888649286365903, 107.61316890190571)).title("Mie Gacoan Dago"));

        for (MarkerOptions markerOptions : markerOptionsList) {
            googleMap.addMarker(markerOptions);
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (fusedLocationProviderClient != null && locationCallback != null) {
            fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        }
    }
}
