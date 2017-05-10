package com.nnc.hughes.pumpprice.network;
import com.nnc.hughes.pumpprice.app.Constants;
import com.nnc.hughes.pumpprice.model.Station;
import com.nnc.hughes.pumpprice.model.Stations;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marcus on 5/8/17.
 */

public interface gasAPI {
    @GET("/stations/radius/{lat}/{long}/{dis}/{fuel}/{sort_by}/rfej9napna.json?" + Constants.API_KEY)
    Call<Stations> getStationsList(@Path("lat") Station latitude,@Path("long")String longitude,
                                   @Path("dis") String distance, @Path("fuel") String fuel_type,
                                   @Path("sort_by") String sortBy);
}
