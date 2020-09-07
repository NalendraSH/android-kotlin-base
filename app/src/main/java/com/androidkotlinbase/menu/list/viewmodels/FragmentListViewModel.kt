package com.androidkotlinbase.menu.list.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.menu.list.paging.ListDataSource
import com.androidkotlinbase.menu.list.paging.ListDataSourceFactory
import com.androidkotlinbase.utils.LoadingState

class FragmentListViewModel : ViewModel(){

    private var listDataSourceFactory: ListDataSourceFactory = ListDataSourceFactory(viewModelScope)
    var narutoList: LiveData<PagedList<Models.Results>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(5)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()
        narutoList = LivePagedListBuilder(listDataSourceFactory, config).build()
    }

    fun getLoadingState(): LiveData<LoadingState>{
        return Transformations.switchMap(listDataSourceFactory.listDataSourceLiveData, ListDataSource::state)
    }

    fun refreshListNaruto(){
        listDataSourceFactory.listDataSourceLiveData.value?.invalidate()
    }
}