package com.nnc.hughes.pumpprice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus on 5/15/17.
 */

public class StationsListResponse {

    @SerializedName("status")
    @Expose
    private Status status;

    @SerializedName("geoLocation")
    @Expose
    private transient GeoLocation geoLocation;
    @SerializedName("stations")
    @Expose
    private List<Station> stations = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public GeoLocation getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(GeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public List<Station> getData() {
        return stations;
    }

    public void setData(List<Station> stations) {
        this.stations = stations;
    }
}
