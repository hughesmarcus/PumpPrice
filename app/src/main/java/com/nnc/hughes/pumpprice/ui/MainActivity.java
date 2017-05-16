package com.nnc.hughes.pumpprice.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nnc.hughes.pumpprice.R;
import com.nnc.hughes.pumpprice.app.PumpPriceApplication;
import com.nnc.hughes.pumpprice.model.Station;
import com.nnc.hughes.pumpprice.model.StationsListResponse;
import com.nnc.hughes.pumpprice.network.Service;

import java.util.List;

import javax.inject.Inject;
import com.nnc.hughes.pumpprice.network.GasAPI;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GasListView {
    private RecyclerView list;
    @Inject
    public Service service;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PumpPriceApplication)getApplication()).getAppComponent().inject(this);

        renderView();
        init();

        GasListPresenter presenter = new GasListPresenter(service, this);
        presenter.getStationList();
    }

    public  void renderView(){
        setContentView(R.layout.activity_main);
        list = (RecyclerView) findViewById(R.id.activity_stations_recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.activity_stations_progressBar);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getStationListSuccess(StationsListResponse stationsListResponse) {
        if (stationsListResponse != null && stationsListResponse.getData() != null && stationsListResponse.getData().size() > 0) {
            StationListAdapater adapter = new StationListAdapater(getApplicationContext(), stationsListResponse.getData(),
                    new StationListAdapater.OnItemClickListener() {
                        @Override
                        public void onClick(Station Item) {
                            Toast.makeText(getApplicationContext(), Item.getRegPrice(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

            list.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getApplicationContext(), "NOOOOOOOO",
                    Toast.LENGTH_LONG).show();
        }
    }

}
