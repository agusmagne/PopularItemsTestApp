package com.agusmagne.shoplisttestapp.di.main

import com.agusmagne.shoplisttestapp.TLSSocketFactory
import com.agusmagne.shoplisttestapp.network.PopularItemsEndpoints
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class MainModule {

    @Provides
    fun provideRetrofitInstance(): Retrofit {
        val client = OkHttpClient.Builder()
            .sslSocketFactory(TLSSocketFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(PopularItemsEndpoints.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providePopularItemsEndpoints(retrofit: Retrofit): PopularItemsEndpoints =
        retrofit.create(PopularItemsEndpoints::class.java)


}