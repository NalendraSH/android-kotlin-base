package com.androidkotlinbase.di.modules

import com.androidkotlinbase.menu.gallery.viewmodels.FragmentGalleryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val galleryModule: Module = module {
    viewModel { FragmentGalleryViewModel() }
}