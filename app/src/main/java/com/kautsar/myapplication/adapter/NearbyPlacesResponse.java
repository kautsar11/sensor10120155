package com.kautsar.myapplication.adapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * NIM        : 10121055
 * Nama     : Kautsar Teguh Dwi Putra
 * Kelas       :  IF-4
 */

public class NearbyPlacesResponse {
    @SerializedName("results")
    private List<Place> results;

    public List<Place> getResults() {
        return results;
    }
}
