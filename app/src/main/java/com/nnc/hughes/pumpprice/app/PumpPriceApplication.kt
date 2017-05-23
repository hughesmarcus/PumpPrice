/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.nnc.hughes.pumpprice.app

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import timber.log.Timber
import com.nnc.hughes.pumpprice.dagger.AppComponent
import com.nnc.hughes.pumpprice.dagger.module.AppModule
import com.nnc.hughes.pumpprice.dagger.DaggerAppComponent
import com.nnc.hughes.pumpprice.dagger.module.NetworkModule
import com.nnc.hughes.pumpprice.utils.CrashReportTree
import io.fabric.sdk.android.Fabric
import timber.log.BuildConfig

import java.io.File


class PumpPriceApplication : Application() {
    var appComponent: AppComponent? = null
        private set

    protected fun initDagger(application: PumpPriceApplication): AppComponent {
        val cacheFile = File(application.cacheDir, "responses")
        return DaggerAppComponent.builder()
                .appModule(AppModule(application))
                .networkModule(NetworkModule(cacheFile))
                .build()

    }

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        //Crashlytics
        val core = CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build()

        Fabric.with(this, Crashlytics.Builder().core(core).build())


        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportTree())
        }
    }
}
