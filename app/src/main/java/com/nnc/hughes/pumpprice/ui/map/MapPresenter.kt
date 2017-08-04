package com.nnc.hughes.pumpprice.ui.map

import com.nnc.hughes.pumpprice.network.GasAPI
import com.nnc.hughes.pumpprice.ui.stationlist.GasListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by marcus on 6/1/17.
 */

class MapPresenter(private val view: MapView, private val gasAPI:GasAPI){

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
