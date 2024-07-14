package com.androidkotlinbase.networks.repositories

import com.androidkotlinbase.data.endpoint.List
import com.androidkotlinbase.menu.list.models.Models
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResponseResult
import kotlinx.coroutines.runBlocking

class ListRepository {

    fun getList(onSuccess: (Models.Response) -> Unit, onError: (FuelError) -> Unit){
        runBlocking {
            val (request, response, result) = Fuel.get(List.LIST_ENDPOINT).awaitObjectResponseResult(Models.Response.Deserializer())
            result.fold({
                onSuccess(it)
            }, {
                onError(it)
            })
        }
    }
}