package com.nnc.hughes.pumpprice.network;

/**
 * Created by marcus on 5/15/17.
 */

import android.util.Log;

import com.nnc.hughes.pumpprice.model.StationsListResponse;

import rx.Observable;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by ennur on 6/25/16.
 */
public class Service {
    private final GasAPI gasAPI;

    public Service(GasAPI gasAPI) {
        this.gasAPI = gasAPI;
    }

    public Subscription getStationsList(final GetCityListCallback callback) {

        return gasAPI.getStationsList("33.4834075","-84.3500434","10","reg","price")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends StationsListResponse>>() {
                    @Override
                    public Observable<? extends StationsListResponse> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<StationsListResponse>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(StationsListResponse stationsListResponse) {
                        callback.onSuccess(stationsListResponse);

                    }
                });
    }

    public interface GetCityListCallback{
        void onSuccess(StationsListResponse stationsListResponse);

        void onError(NetworkError networkError);
    }
}