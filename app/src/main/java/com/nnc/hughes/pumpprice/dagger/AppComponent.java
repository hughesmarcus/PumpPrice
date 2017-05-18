package com.nnc.hughes.pumpprice.dagger;



import com.nnc.hughes.pumpprice.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by marcus on 5/2/17.
 */


@Singleton
@Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity target);
    //void inject(GasListPresenter target);
}
