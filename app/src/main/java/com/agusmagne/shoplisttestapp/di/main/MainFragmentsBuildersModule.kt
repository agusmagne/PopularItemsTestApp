package com.agusmagne.shoplisttestapp.di.main

import com.agusmagne.shoplisttestapp.ui.main.popularitems.PopularItemDialogFragment
import com.agusmagne.shoplisttestapp.ui.main.ShopListFragment
import com.agusmagne.shoplisttestapp.ui.main.shoppingcart.ShoppingCartDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeShopListFragment(): ShopListFragment

    @ContributesAndroidInjector
    abstract fun contributePopularItemDialogFragment(): PopularItemDialogFragment

    @ContributesAndroidInjector
    abstract fun contributeShoppingCartDialogFragment(): ShoppingCartDialogFragment

}