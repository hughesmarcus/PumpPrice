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
import android.view.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.nnc.hughes.pumpprice.data.GasPalPreferences
import com.nnc.hughes.pumpprice.ui.station.DetailActivity

class MainActivity : AppCompatActivity(), GasListView {
    private lateinit var list: RecyclerView
    @Inject
    lateinit var service: Service
    lateinit var presenter: GasListPresenter
    internal lateinit var progressBar: ProgressBar
    var PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    private val mBundleRecyclerViewState: Bundle? = null
    private val KEY_RECYCLER_STATE = "recycler_state"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PumpPriceApplication).appComponent.inject(this)
        renderView()
        init()
        val coords = GasPalPreferences.getLocationCoordinates(this)
        presenter = GasListPresenter(service!!, this)
        presenter.getStationList(coords[0], coords[1])
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return true
    }

   override fun onOptionsItemSelected(item:MenuItem):Boolean {
        /* Get the ID of the clicked item */
        val id = item.getItemId()
        /* Settings menu item clicked */
        if (id == R.id.search)
        {
            try
            {
                val intent = PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                        .build(this)
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE)
            }
            catch (e: GooglePlayServicesRepairableException) {
                // TODO: Handle the error.
            }
            catch (e: GooglePlayServicesNotAvailableException) {
                // TODO: Handle the error.
            }
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override protected fun onActivityResult(requestCode:Int, resultCode:Int, data:Intent) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                val place = PlaceAutocomplete.getPlace(this, data)
                Log.i(TAG, "Place: " + place.getName())
                GasPalPreferences.resetLocationCoordinates(this)
                GasPalPreferences.setLocationDetails(this,java.lang.Double.toString(place.latLng.latitude), java.lang.Double.toString(place.latLng.longitude))
                presenter.getStationList(java.lang.Double.toString(place.latLng.latitude), java.lang.Double.toString(place.latLng.longitude))

            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
            {
                val status = PlaceAutocomplete.getStatus(this, data)
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage())
            }
            else if (resultCode == RESULT_CANCELED)
            {
                // The user canceled the operation.
            }
        }
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
    fun launchStationDetail(station:Station) {
        DetailActivity.launch(this, station)
    }
    override fun getStationListSuccess(stationsListResponse: StationsListResponse?) {
        if (stationsListResponse != null && stationsListResponse.data != null && stationsListResponse.data.size > 0) {
            Log.d("Hello", Integer.toString(stationsListResponse.data.size))
            val adapter = StationListAdapater(applicationContext, stationsListResponse.data,

                    object : StationListAdapater.OnItemClickListener {
                        override fun onClick(Item: Station) {
                            launchStationDetail(Item)
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
