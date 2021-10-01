package com.androidkotlinbase.menu.list.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.menu.list.paging.ListDataSource

class FragmentListViewModel : ViewModel(){

    var narutoList: LiveData<PagingData<Models.Results>>

    init {
        val pager = Pager(
            pagingSourceFactory = { ListDataSource(viewModelScope) },
            config = PagingConfig(20)
        )
        narutoList = pager.liveData
    }
}