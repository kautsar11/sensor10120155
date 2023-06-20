package com.kautsar.myapplication.adapter;

import com.google.gson.annotations.SerializedName;

/**
 * NIM        : 10121055
 * Nama     : Kautsar Teguh Dwi Putra
 * Kelas       :  IF-4
 */

public class Place {
    @SerializedName("name")
    private String name;

    @SerializedName("geometry")
    private Geometry geometry;

    public String getName() {
        return name;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
