package com.newsapp.weatherapp.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.newsapp.weatherapp.WeatherViewModelFactory;
import com.newsapp.weatherapp.model.BookMark;
import com.newsapp.weatherapp.viewModel.BookMarkViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class MapFragment extends SupportMapFragment implements OnMapReadyCallback,
        OnMarkerClickListener {
    private GoogleMap mMap;
    @Inject
    WeatherViewModelFactory factory;
    BookMarkViewModel viewModel;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AndroidSupportInjection.inject(this);
        viewModel = ViewModelProviders.of(this, factory).get(BookMarkViewModel.class);

    }

    @Override
    public void onResume() {
        super.onResume();

        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if (mMap == null) {
            getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(this::onMapClick);


    }

    public void placeMarker(LatLng position) {
        mMap.addMarker(new MarkerOptions().position(position));
    }

    public void saveLocation(LatLng position) {
        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);
            for (Address address :addresses) {
                if (address !=null) {
                    BookMark bookMark = new BookMark();
                    bookMark.setCountry(address.getCountryName());
                    bookMark.setName(address.getLocality());
                    bookMark.setLatitude(address.getLatitude());
                    bookMark.setLongitude(address.getLongitude());
                    viewModel.saveBookMark(bookMark);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    private void onMapClick(LatLng position) {
        MapFragment.this.placeMarker(position);
        saveLocation(position);
    }
}
