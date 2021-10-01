package com.androidkotlinbase.di.modules

import com.androidkotlinbase.main.MainViewModel
import com.androidkotlinbase.networks.repositories.GalleryRepository
import com.androidkotlinbase.networks.repositories.ListRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by nalen on 07/09/20.
 */

val appModule = module {

    factory { ListRepository(get()) }
    factory { GalleryRepository() }
    factory { com.androidkotlinbase.local.repositories.GalleryRepository(androidApplication()) }

    viewModel { MainViewModel(androidApplication()) }

}