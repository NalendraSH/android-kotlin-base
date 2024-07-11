package com.androidkotlinbase.menu.list.paging

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.androidkotlinbase.data.Constant
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.networks.repositories.ListRepository
import com.androidkotlinbase.utils.LoadingState
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class ListDataSource(coroutineScope: CoroutineScope): PagingSource<Int, Models.Results>(),
    KoinComponent {

    private val repository: ListRepository by inject{ parametersOf(coroutineScope) }

    var state: MutableLiveData<LoadingState> = MutableLiveData()

    private fun updateState(loadingState: LoadingState) {
        state.postValue(loadingState)
    }

    override fun getRefreshKey(state: PagingState<Int, Models.Results>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Models.Results> {
        val result: MutableList<Models.Results> = mutableListOf()
        val nextPageNumber = params.key ?: 1

        updateState(LoadingState.LOADING)
        repository.getList({
            updateState(LoadingState.DONE)
            result.clear()
            result.addAll(it.data)
        }, {
            SystemClock.sleep(Constant.DUMMY_LOAD_MORE_TIME)
            updateState(LoadingState.DONE)
            it.printStackTrace()
        })

        return LoadResult.Page(result, null, nextPageNumber+20)
    }
}