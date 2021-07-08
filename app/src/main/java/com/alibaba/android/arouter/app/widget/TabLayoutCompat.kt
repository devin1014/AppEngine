package com.alibaba.android.arouter.app.widget

import android.view.View
import android.view.View.OnAttachStateChangeListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy

class TabLayoutCompat private constructor(private val tabLayout: TabLayout) {
    companion object {
        fun wrap(tabLayout: TabLayout): TabLayoutCompat = TabLayoutCompat(tabLayout)
    }

    interface TabLayoutCallback : TabConfigurationStrategy, OnTabSelectedListener {
        override fun onTabSelected(tab: Tab) {}
        override fun onTabUnselected(tab: Tab) {}
        override fun onTabReselected(tab: Tab) {}
        override fun onConfigureTab(tab: Tab, position: Int) {}
    }

    fun setupViewPager2(viewPager2: ViewPager2, callback: TabLayoutCallback? = null) {
        tabLayout.addOnTabSelectedListener(callback)
        val tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            callback?.onConfigureTab(tab, position)
        }
        tabLayoutMediator.attach()
        tabLayout.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {}
            override fun onViewDetachedFromWindow(v: View) {
                tabLayoutMediator.detach()
            }
        })
    }
}

abstract class FragmentPagerAdapter2(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    final override fun getItemCount(): Int = getCount()
    final override fun createFragment(position: Int): Fragment = getItem(position)
    abstract fun getCount(): Int
    abstract fun getItem(position: Int): Fragment
    abstract fun getPageTitle(position: Int): CharSequence
}


