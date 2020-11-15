package com.agusmagne.shoplisttestapp.network

import com.agusmagne.shoplisttestapp.model.entities.Item
import retrofit2.Call
import retrofit2.http.GET

interface PopularItemsEndpoints {

    companion object {
        const val BASE_URL = "https://popular-items.azurewebsites.net/"
        const val POPULAR_ITEMS = "popular_items.json"
        const val CALLBACK_ERROR = "Error retreiving popular items"
    }

    @GET(POPULAR_ITEMS)
    fun getPopularItems(): Call<Array<Item>>

}