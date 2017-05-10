package com.nnc.hughes.pumpprice.network;
import com.nnc.hughes.pumpprice.app.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by marcus on 5/8/17.
 */

public interface gasAPI {
    @GET("/stations/details/api_key=" + Constants.API_KEY)
    Call<Station> getFoodzList();
}
