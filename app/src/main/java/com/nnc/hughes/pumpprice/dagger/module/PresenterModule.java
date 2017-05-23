package com.nnc.hughes.pumpprice.dagger.module;

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
    GasListPresenter provideGasListPresenter(GasListView view) {
        return new GasListPresenter(view, null);
    }

}