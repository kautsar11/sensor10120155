package com.kautsar.myapplication.adapter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * NIM        : 10121055
 * Nama     : Kautsar Teguh Dwi Putra
 * Kelas       :  IF-4
 */

public interface PlacesApiService {
    @GET
    Call<NearbyPlacesResponse> getNearbyPlaces(@Url String url);
}
