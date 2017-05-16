package com.nnc.hughes.pumpprice.model;

/**
 * Created by marcus on 5/15/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("code")
    @Expose
    private Integer code;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("message")
    @Expose
    private String message;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}