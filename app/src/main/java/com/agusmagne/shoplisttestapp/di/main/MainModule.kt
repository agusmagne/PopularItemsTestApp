package com.agusmagne.shoplisttestapp.di.main

import android.app.Application
import androidx.room.Room
import com.agusmagne.shoplisttestapp.model.dao.Database
import com.agusmagne.shoplisttestapp.network.PopularItemsEndpoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PopularItemsEndpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun providePopularItemsEndpoints(retrofit: Retrofit): PopularItemsEndpoints =
        retrofit.create(PopularItemsEndpoints::class.java)


}