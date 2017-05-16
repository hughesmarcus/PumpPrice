package com.nnc.hughes.pumpprice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcus on 5/16/17.
 */

public class HistoryListResponse {
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("history")
    @Expose
    private List<History> history = null;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
}
