package com.agusmagne.shoplisttestapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.agusmagne.shoplisttestapp.R

private val TAB_TITLES = arrayOf(
        R.string.tab_list,
        R.string.tab_cart
)

class ShopListPageAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return ShopListFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}