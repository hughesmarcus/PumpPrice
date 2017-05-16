package com.nnc.hughes.pumpprice.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nnc.hughes.pumpprice.R;
import com.nnc.hughes.pumpprice.model.Station;

import java.util.List;

/**
 * Created by marcus on 5/15/17.
 */

public class StationListAdapater extends RecyclerView.Adapter<StationListAdapater.ViewHolder>   {
    private final OnItemClickListener listener;
    private List<Station> data;
    private Context context;

    public StationListAdapater(Context context, List<Station> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public StationListAdapater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.station_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(StationListAdapater.ViewHolder holder, int position) {
        holder.click(data.get(position), listener);
        holder.price.setText(data.get(position).getRegPrice());






    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(Station Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;


        public ViewHolder(View itemView) {
            super(itemView);
            price = (TextView) itemView.findViewById(R.id.price);



        }


        public void click(final Station station, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(station);
                }
            });
        }
    }

}
