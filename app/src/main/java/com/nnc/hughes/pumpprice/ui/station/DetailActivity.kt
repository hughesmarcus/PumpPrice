package com.nnc.hughes.pumpprice.ui.station

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.model.Station
import android.widget.TextView
import butterknife.BindView
import com.nnc.hughes.pumpprice.network.GasAPI
import kotlinx.android.synthetic.main.activity_detail.*



class DetailActivity : AppCompatActivity() {

    companion object {

        private val STATION = "station"


        fun launch(context: Context, station: Station) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(STATION, station)
            context.startActivity(intent)
        }
    }
    lateinit var station: Station

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        station = intent.getParcelableExtra(STATION)
        station_text.text = station.address


    }
}
