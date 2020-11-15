package com.agusmagne.shoplisttestapp.di.app

import androidx.lifecycle.ViewModelProvider
import com.agusmagne.shoplisttestapp.vm.ViewModelFactoryProviders
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactoryProviders): ViewModelProvider.Factory

}