package com.nnc.hughes.pumpprice.network;

/**
 * Created by marcus on 5/15/17.
 */

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("status")
    public String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @SuppressWarnings({"unused", "used by Retrofit"})
    public Response() {
    }

    public Response(String status) {
        this.status = status;
    }
}