package com.nnc.hughes.pumpprice.ui;

import com.nnc.hughes.pumpprice.model.Station;
import com.nnc.hughes.pumpprice.model.StationsListResponse;

import java.util.List;

/**
 * Created by marcus on 5/10/17.
 */

public interface GasListView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getStationListSuccess(StationsListResponse stationsListResponse);
}
