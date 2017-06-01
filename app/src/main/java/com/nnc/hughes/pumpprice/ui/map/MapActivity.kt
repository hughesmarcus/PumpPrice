package com.nnc.hughes.pumpprice.ui.map

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.ui.stationlist.MainActivity


class MapActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        initNavigation()
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

}
