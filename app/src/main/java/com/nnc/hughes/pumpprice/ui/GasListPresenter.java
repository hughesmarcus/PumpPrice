package com.nnc.hughes.pumpprice.ui;

import android.content.Context;

import com.nnc.hughes.pumpprice.app.PumpPriceApplication;
import com.nnc.hughes.pumpprice.model.Station;
import com.nnc.hughes.pumpprice.model.StationsListResponse;
import com.nnc.hughes.pumpprice.network.GasAPI;

import java.util.List;

import javax.inject.Inject;
import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.nnc.hughes.pumpprice.network.NetworkError;
import com.nnc.hughes.pumpprice.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by marcus on 5/15/17.
 */

public class GasListPresenter {
    private final Service service;
    private final GasListView view;
    private CompositeSubscription subscriptions;

    public GasListPresenter(Service service, GasListView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getStationList() {
        view.showWait();

        Subscription subscription = service.getStationsList(new Service.GetCityListCallback() {
            @Override
            public void onSuccess(StationsListResponse stationsListResponse) {
                view.removeWait();
                view.getStationListSuccess(stationsListResponse);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getAppErrorMessage());
            }

        });

        subscriptions.add(subscription);
    }
    public void onStop() {
        subscriptions.unsubscribe();
    }
}
