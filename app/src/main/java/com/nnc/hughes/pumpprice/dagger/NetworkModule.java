package com.nnc.hughes.pumpprice.dagger;

/**
 * Created by marcus on 5/15/17.
 */

import com.nnc.hughes.pumpprice.app.Constants;
import com.nnc.hughes.pumpprice.network.GasAPI;
import com.nnc.hughes.pumpprice.network.Service;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcus on 5/2/17.
 */
@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides
    @Named(NAME_BASE_URL)
    String provideBaseUrlString() {
        return Constants.BASE_URL;
    }

    @Provides
    @Singleton
    Converter.Factory provideGsonConverter() {
        return GsonConverterFactory.create();
    }
    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {

            return new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(converter)
                    .build();

    }

    @Provides
    @Singleton
    GasAPI providesNetworkService(Retrofit retrofit) {
        return retrofit.create(GasAPI.class);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public Service providesService(
            GasAPI gasAPI) {
        return new Service(gasAPI);
    }
}