package com.nnc.hughes.pumpprice.ui.stationlist

/**
 * Created by marcus on 5/17/17.
 */

import com.nnc.hughes.pumpprice.network.GasAPI

import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by marcus on 5/15/17.
 */

class GasListPresenter(private val view: GasListView, private val gasAPI: GasAPI) {
    protected var compositeDisposable: CompositeDisposable? = null

    init {
        this.compositeDisposable = CompositeDisposable()
    }


    fun getStationList(lat: String, log: String) {

        compositeDisposable!!.add(gasAPI.getStationsList(lat, log, "10", "reg", "price")
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stationsListResponse -> view.getStationListSuccess(stationsListResponse) }))


    }

    fun clearSubscriptions() {
        compositeDisposable!!.clear()
    }
}
