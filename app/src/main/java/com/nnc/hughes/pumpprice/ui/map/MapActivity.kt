package com.nnc.hughes.pumpprice.ui.map

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment
import com.google.android.gms.location.places.ui.PlaceSelectionListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.app.PumpPriceApplication
import com.nnc.hughes.pumpprice.data.GasPalPreferences
import com.nnc.hughes.pumpprice.model.Station
import com.nnc.hughes.pumpprice.model.StationsListResponse
import com.nnc.hughes.pumpprice.network.GasAPI
import com.nnc.hughes.pumpprice.ui.stationlist.GasListPresenter
import com.nnc.hughes.pumpprice.ui.stationlist.MainActivity
import com.nnc.hughes.pumpprice.ui.stationlist.StationListAdapater
import javax.inject.Inject


class MapActivity : FragmentActivity(), OnMapReadyCallback,MapView  {
    private var mMap: GoogleMap? = null
    @Inject
    lateinit var gasApi: GasAPI
    lateinit var presenter: MapPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as PumpPriceApplication).appComponent!!.inject(this)
        setContentView(R.layout.activity_map)
        initSearch()
        val coords = GasPalPreferences.getLocationCoordinates(this)
        presenter = MapPresenter(this, gasApi)
        presenter.getStationList(coords[0], coords[1])
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        initNavigation()

    }
    fun initSearch() {
        val autocompleteFragment = getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment) as PlaceAutocompleteFragment
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                GasPalPreferences.resetLocationCoordinates(this@MapActivity)
                GasPalPreferences.setLocationDetails(this@MapActivity, java.lang.Double.toString(place.latLng.latitude), java.lang.Double.toString(place.latLng.longitude))
                presenter.getStationList(java.lang.Double.toString(place.latLng.latitude), java.lang.Double.toString(place.latLng.longitude))
            }

            override fun onError(status: Status) {
                // TODO: Handle the error.

            }
        })
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney, Australia, and move the camera.
        val sydney = LatLng(-34.0, 151.0)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
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
                    val intent = Intent(this, MainActivity::class.java)

                    this.startActivity(intent)
                    Toast.makeText(applicationContext, "You Clicked Stations", Toast.LENGTH_SHORT).show()

                }
                R.id.profile_screen -> {
                    Toast.makeText(applicationContext, "You Clicked Profile", Toast.LENGTH_SHORT).show()

                }
                R.id.map_screen ->{
                    val intent = Intent(this, MapActivity::class.java)

                    this.startActivity(intent)
                }
            }
            true
        }
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


        } else {
            Toast.makeText(applicationContext, "NOOOOOOOO",
                    Toast.LENGTH_LONG).show()
        }
    }
    override fun onFailure(appErrorMessage: String) {

    }
}
