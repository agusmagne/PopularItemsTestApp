package com.agusmagne.shoplisttestapp.di.app

import com.agusmagne.shoplisttestapp.ui.main.MainActivity
import com.agusmagne.shoplisttestapp.di.main.MainFragmentsBuildersModule
import com.agusmagne.shoplisttestapp.di.main.MainModule
import com.agusmagne.shoplisttestapp.di.main.MainViewModelsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            MainFragmentsBuildersModule::class,
            MainViewModelsModule::class,
            MainModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity

}

