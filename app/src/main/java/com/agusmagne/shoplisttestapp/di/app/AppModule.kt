package com.agusmagne.shoplisttestapp.di.app

import android.app.Application
import androidx.room.Room
import com.agusmagne.shoplisttestapp.model.dao.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideItemDatabase(application: Application): Database =
        Room.databaseBuilder(application, Database::class.java, "database").build()
}