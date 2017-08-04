package com.nnc.hughes.pumpprice.ui.map;

import com.nnc.hughes.pumpprice.model.StationsListResponse;

/**
 * Created by marcus on 6/6/17.
 */

public interface MapView {
    void onFailure(String appErrorMessage);

    void getStationListSuccess(StationsListResponse stationsListResponse);
}
