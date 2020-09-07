package com.androidkotlinbase.menu.list.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.androidkotlinbase.menu.list.models.Models
import kotlinx.coroutines.CoroutineScope

class ListDataSourceFactory(private val coroutineScope: CoroutineScope): DataSource.Factory<Int, Models.Results>() {

    val listDataSourceLiveData: MutableLiveData<ListDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Models.Results> {
        val narutoDataSource = ListDataSource(coroutineScope)
        listDataSourceLiveData.postValue(narutoDataSource)
        return narutoDataSource
    }
}