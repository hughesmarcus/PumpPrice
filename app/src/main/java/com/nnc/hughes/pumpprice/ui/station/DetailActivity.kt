package com.nnc.hughes.pumpprice.ui.station

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.model.Station
import android.widget.TextView
import butterknife.BindView
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {

    companion object {

        private val STATION_ID = "station_id"

        fun launch(context: Context, station: Station){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(STATION_ID, station.id)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        station_text.text = intent.getStringExtra(STATION_ID)


    }
}
