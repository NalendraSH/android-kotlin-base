package com.androidkotlinbase.di.modules

import com.androidkotlinbase.menu.list.viewmodels.FragmentListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val listModule: Module = module {
    viewModel { FragmentListViewModel() }
}