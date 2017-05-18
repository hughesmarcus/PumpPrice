package com.nnc.hughes.pumpprice.ui

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast


import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.app.Constants
import com.nnc.hughes.pumpprice.app.PumpPriceApplication
import com.nnc.hughes.pumpprice.model.Station
import com.nnc.hughes.pumpprice.model.StationsListResponse
import com.nnc.hughes.pumpprice.network.Service

import java.io.IOException
import java.util.ArrayList

import javax.inject.Inject
import com.nnc.hughes.pumpprice.network.GasAPI
import butterknife.BindView
import butterknife.ButterKnife

import android.R.attr.tag

class MainActivity : AppCompatActivity(), GasListView {
    private var list: RecyclerView? = null
    @Inject
    lateinit var service: Service
    lateinit var presenter: GasListPresenter
    internal lateinit var progressBar: ProgressBar

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PumpPriceApplication).appComponent.inject(this)
        renderView()
        init()
        presenter = GasListPresenter(service!!, this)
        presenter.getStationList("33.4834075", "-84.3500434")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query != "") {
                    var addressList: List<android.location.Address>? = null
                    val geocoder = Geocoder(this@MainActivity)
                    try {
                        addressList = geocoder.getFromLocationName(query, 1)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    val address = addressList!![0]
                    val latLng = LatLng(address.latitude, address.longitude)
                    Log.e(TAG, java.lang.Double.toString(address.latitude))
                    presenter.getStationList(java.lang.Double.toString(address.latitude), java.lang.Double.toString(address.longitude))
                }
                return true
            }

            override fun onQueryTextChange(query: String): Boolean {


                return false

            }

        })
        return true
    }


    fun renderView() {
        setContentView(R.layout.activity_main)
        list = findViewById(R.id.activity_stations_recyclerView) as RecyclerView
        progressBar = findViewById(R.id.activity_stations_progressBar) as ProgressBar
    }

    fun init() {
        val adapter = StationListAdapater(applicationContext, ArrayList<Station>(),
                object : StationListAdapater.OnItemClickListener {
                    override fun onClick(Item: Station) {

                    }
                })
        list!!.adapter = adapter
        list!!.layoutManager = LinearLayoutManager(this)
    }

    override fun showWait() {
        progressBar.visibility = View.VISIBLE
    }

    override fun removeWait() {
        progressBar.visibility = View.GONE
    }

    override fun onFailure(appErrorMessage: String) {

    }

    override fun getStationListSuccess(stationsListResponse: StationsListResponse?) {
        if (stationsListResponse != null && stationsListResponse.data != null && stationsListResponse.data.size > 0) {
            Log.d("Hello", Integer.toString(stationsListResponse.data.size))
            val adapter = StationListAdapater(applicationContext, stationsListResponse.data,

                    object : StationListAdapater.OnItemClickListener {
                        override fun onClick(Item: Station) {
                            Toast.makeText(applicationContext, Item.station,
                                    Toast.LENGTH_LONG).show()
                        }
                    })

            list!!.adapter = adapter
            adapter.notifyDataSetChanged()
        } else {
            Toast.makeText(applicationContext, "NOOOOOOOO",
                    Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private val TAG = "MAIN_ACTIVITY"
    }

}
