package com.agusmagne.shoplisttestapp.di.main

import androidx.lifecycle.ViewModel
import com.agusmagne.shoplisttestapp.di.app.ViewModelKey
import com.agusmagne.shoplisttestapp.vm.ShopListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ShopListViewModel::class)
    abstract fun bindShopListViewModel(viewModel: ShopListViewModel): ViewModel
}