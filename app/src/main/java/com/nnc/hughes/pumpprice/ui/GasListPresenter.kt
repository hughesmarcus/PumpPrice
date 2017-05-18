package com.nnc.hughes.pumpprice.ui

/**
 * Created by marcus on 5/17/17.
 */

import android.content.Context

import com.nnc.hughes.pumpprice.app.PumpPriceApplication
import com.nnc.hughes.pumpprice.model.Station
import com.nnc.hughes.pumpprice.model.StationsListResponse
import com.nnc.hughes.pumpprice.network.GasAPI

import javax.inject.Inject
import com.annimon.stream.Collectors
import com.annimon.stream.Stream
import com.nnc.hughes.pumpprice.network.NetworkError
import com.nnc.hughes.pumpprice.network.Service

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rx.Subscription
import rx.subscriptions.CompositeSubscription

/**
 * Created by marcus on 5/15/17.
 */

class GasListPresenter(private val service: Service, private val view: GasListView) {
    private val subscriptions: CompositeSubscription

    init {
        this.subscriptions = CompositeSubscription()
    }

    fun getStationList(lat: String, log: String) {
        view.showWait()

        val subscription = service.getStationsList(object : Service.GetCityListCallback {
            override fun onSuccess(stationsListResponse: StationsListResponse) {
                view.removeWait()
                view.getStationListSuccess(stationsListResponse)
            }

            override fun onError(networkError: NetworkError) {
                view.removeWait()
                view.onFailure(networkError.appErrorMessage)
            }

        }, lat, log)

        subscriptions.add(subscription)
    }

    fun onStop() {
        subscriptions.unsubscribe()
    }
}