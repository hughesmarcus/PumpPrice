package com.nnc.hughes.pumpprice.dagger;

import com.nnc.hughes.pumpprice.network.Service;
import com.nnc.hughes.pumpprice.ui.GasListPresenter;
import com.nnc.hughes.pumpprice.ui.GasListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by marcus on 5/15/17.
 */
@Module
public class PresenterModule {
    @Provides
    @Singleton
    GasListPresenter provideGasListPresenter(Service service, GasListView view) {
        return new GasListPresenter(service, view);
    }

}