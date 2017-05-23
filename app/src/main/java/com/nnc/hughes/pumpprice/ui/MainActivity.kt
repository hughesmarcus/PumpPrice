package com.nnc.hughes.pumpprice.ui


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast

import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.app.PumpPriceApplication
import com.nnc.hughes.pumpprice.model.Station
import com.nnc.hughes.pumpprice.model.StationsListResponse

import java.util.ArrayList

import javax.inject.Inject

import android.support.design.widget.BottomNavigationView
import android.view.*
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.nnc.hughes.pumpprice.data.GasPalPreferences
import com.nnc.hughes.pumpprice.network.GasAPI
import com.nnc.hughes.pumpprice.ui.station.DetailActivity
class MainActivity : AppCompatActivity(), GasListView {
    private lateinit var list: RecyclerView
    @Inject
    lateinit var gasApi: GasAPI
    lateinit var presenter: GasListPresenter
    internal lateinit var progressBar: ProgressBar
    var PLACE_AUTOCOMPLETE_REQUEST_CODE = 1
    private val mBundleRecyclerViewState: Bundle? = null
    private val KEY_RECYCLER_STATE = "recycler_state"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as PumpPriceApplication).appComponent!!.inject(this)
        initNavigation()
        renderView()
        init()
        initSearch()
        val coords = GasPalPreferences.getLocationCoordinates(this)
        presenter = GasListPresenter(this, gasApi)
        presenter.getStationList(coords[0], coords[1])
    }
    fun initSearch(){
        val autocompleteFragment = getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment
        autocompleteFragment.setOnPlaceSelectedListener(object: PlaceSelectionListener {
          override  fun onPlaceSelected(place: Place) {
              showWait()
              GasPalPreferences.resetLocationCoordinates(this@MainActivity)
              GasPalPreferences.setLocationDetails(this@MainActivity,java.lang.Double.toString(place.latLng.latitude), java.lang.Double.toString(place.latLng.longitude))
              presenter.getStationList(java.lang.Double.toString(place.latLng.latitude), java.lang.Double.toString(place.latLng.longitude))
            }
           override fun onError(status: Status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status)
            }
        })
    }
    fun initNavigation() {


        val navigationView = findViewById(R.id.bottom_navigation) as? BottomNavigationView
        navigationView?.setOnNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            when (id) {
                R.id.home_screen -> {
                    Toast.makeText(applicationContext, "You Clicked Home", Toast.LENGTH_SHORT).show()

                }
                R.id.stations_screen -> {
                    Toast.makeText(applicationContext, "You Clicked Stations", Toast.LENGTH_SHORT).show()

                }
                R.id.profile_screen -> {
                    Toast.makeText(applicationContext, "You Clicked Profile", Toast.LENGTH_SHORT).show()

                }
            }
            true
        }
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
        removeWait()
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
