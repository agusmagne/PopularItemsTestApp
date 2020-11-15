package com.agusmagne.shoplisttestapp.di.app

import android.app.Application
import com.agusmagne.shoplisttestapp.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
        AppModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}