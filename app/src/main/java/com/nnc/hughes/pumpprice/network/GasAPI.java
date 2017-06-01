package com.nnc.hughes.pumpprice.network;

import com.nnc.hughes.pumpprice.app.Constants;
import com.nnc.hughes.pumpprice.model.HistoryListResponse;
import com.nnc.hughes.pumpprice.model.Station;
import com.nnc.hughes.pumpprice.model.Stations;
import com.nnc.hughes.pumpprice.model.StationsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by marcus on 5/8/17.
 */

public interface GasAPI {
    @GET("/stations/radius/{lat}/{long}/{dis}/{fuel}/{sort_by}/0tgarntmf9.json?")
    Observable<StationsListResponse> getStationsList(@Path("lat") String latitude, @Path("long") String longitude,
                                                     @Path("dis") String distance, @Path("fuel") String fuel_type,
                                                     @Path("sort_by") String sortBy);

    @GET("/locations/pricehistory/{id}/0tgarntmf9.json?")
    Observable<HistoryListResponse> getHistoryList(@Path("id") String id);
}
