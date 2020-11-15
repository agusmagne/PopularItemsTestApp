package com.agusmagne.shoplisttestapp.ui.main

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.agusmagne.shoplisttestapp.R
import com.agusmagne.shoplisttestapp.vm.ShopListViewModel
import com.google.android.material.tabs.TabLayout
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    lateinit var viewmodel: ShopListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buildViewPager()
        fab.hide()
        fab.setOnClickListener { getCurrentFragment()?.openNewItemDialogFragment() }
    }

    private fun buildViewPager() {
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = ShopListPageAdapter(this, supportFragmentManager)
        viewPager.addOnPageChangeListener(onPageChangeListener)
        findViewById<TabLayout>(R.id.tabs).setupWithViewPager(viewPager)
    }

    private val onPageChangeListener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position == 1) {
                fab.show()
            } else {
                fab.hide()
            }
        }
    }

    private fun getCurrentFragment(): ShopListFragment? {
        val fragments = supportFragmentManager.fragments
        if (fragments.isNotEmpty()) {
            return fragments.filter { f -> (f as ShopListFragment).index == 2 }[0] as ShopListFragment

        }
        return null
    }
}