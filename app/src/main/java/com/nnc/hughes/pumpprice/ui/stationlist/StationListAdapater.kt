package com.nnc.hughes.pumpprice.ui.stationlist

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.nnc.hughes.pumpprice.R
import com.nnc.hughes.pumpprice.model.Station

/**
 * Created by marcus on 5/15/17.
 */

class StationListAdapater(private val context: Context, private val data: List<Station>, private val listener: OnItemClickListener) : RecyclerView.Adapter<StationListAdapater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.station_list, null)
        view.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.click(data[position], listener)
        holder.price.text = data[position].regPrice
        holder.station.text = data[position].station
        holder.distance.text = data[position].distance
        holder.address.text = data[position].address
        holder.time.text = data[position].regDate


    }


    override fun getItemCount(): Int {
        return data.size
    }


    interface OnItemClickListener {
        fun onClick(Item: Station)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var price: TextView
        internal var station: TextView
        internal var distance: TextView
        internal var address: TextView
        internal var time: TextView


        init {
            price = itemView.findViewById(R.id.reg_price) as TextView
            distance = itemView.findViewById(R.id.distance) as TextView
            address = itemView.findViewById(R.id.address) as TextView
            time = itemView.findViewById(R.id.time) as TextView
            station = itemView.findViewById(R.id.station) as TextView

        }


        fun click(station: Station, listener: OnItemClickListener) {
            itemView.setOnClickListener { listener.onClick(station) }
        }
    }

}
