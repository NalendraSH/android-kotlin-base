package com.androidkotlinbase

import android.app.Application
import com.androidkotlinbase.di.modules.appModule
import com.androidkotlinbase.di.modules.galleryModule
import com.androidkotlinbase.di.modules.listModule
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogRequestInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by nalen on 07/09/20.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setupFuel()
        setupKoin()
    }

    private fun setupFuel() {
        val fuelManager = FuelManager.instance
        fuelManager.basePath = BuildConfig.BASE_URL
        if (BuildConfig.DEBUG) {
            fuelManager.addRequestInterceptor { LogRequestInterceptor(it) }
            fuelManager.addResponseInterceptor { LogResponseInterceptor(it) }
        }
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, listModule, galleryModule))
        }
    }
}