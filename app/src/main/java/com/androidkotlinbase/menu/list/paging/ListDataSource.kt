package com.androidkotlinbase.menu.list.paging

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.androidkotlinbase.data.Constant
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.networks.repositories.ListRepository
import com.androidkotlinbase.utils.LoadingState
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class ListDataSource(coroutineScope: CoroutineScope): PageKeyedDataSource<Int, Models.Results>(),
    KoinComponent {

    private val repository: ListRepository by inject{ parametersOf(coroutineScope) }

    var state: MutableLiveData<LoadingState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Models.Results>) {
        updateState(LoadingState.LOADING)
        repository.getList({
            updateState(LoadingState.DONE)
            callback.onResult(it.results, 0, 20)
        }, {
            updateState(LoadingState.DONE)
            it.printStackTrace()
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Models.Results>) {
        updateState(LoadingState.LOADING)
        repository.getList({
            updateState(LoadingState.DONE)
            callback.onResult(it.results, params.key+20)
        }, {
            SystemClock.sleep(Constant.DUMMY_LOAD_MORE_TIME)
            updateState(LoadingState.DONE)
            it.printStackTrace()
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Models.Results>) {
    }

    private fun updateState(loadingState: LoadingState) {
        state.postValue(loadingState)
    }
}