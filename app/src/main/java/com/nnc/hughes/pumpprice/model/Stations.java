package com.nnc.hughes.pumpprice.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcus on 5/10/17.
 */

public class Stations {

    @SerializedName("results")
    private List<Station> stations = new ArrayList<>();

    public List<Station> getStations() {
        return stations;
    }
}