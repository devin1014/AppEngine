package com.alibaba.android.arouter.app

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.app.core.NLRouterInfo
import com.alibaba.android.arouter.app.core.buildFragment
import com.alibaba.android.arouter.app.widget.FragmentPagerAdapter2
import com.alibaba.android.arouter.app.widget.TabLayoutCompat
import com.alibaba.android.arouter.app.widget.TabLayoutCompat.TabLayoutCallback
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import kotlin.math.max

@Route(path = Constants.ROUTER_ACTIVITY_MAIN)
class MainActivity : BaseActivity(), OnCheckedChangeListener {

    private val pagePaths = listOf(Constants.ROUTER_FRAGMENT_HOME, Constants.ROUTER_FRAGMENT_SCHEDULE, Constants.ROUTER_FRAGMENT_SETTINGS)
    private val viewPager: ViewPager2 by lazy { findViewById(R.id.main_view_pager) }

    override fun getContentId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
        val pageTitles = resources.getStringArray(R.array.MAIN_TABS)
        viewPager.adapter = object : FragmentPagerAdapter2(this) {
            override fun getCount(): Int = pageTitles.size

            override fun getItem(position: Int): Fragment {
                return buildFragment(pagePaths[position])!!
            }

            override fun getPageTitle(position: Int): CharSequence = pageTitles[position]
        }
        val tabLayout = findViewById<TabLayout>(R.id.main_tab_layout)
        TabLayoutCompat.wrap(tabLayout).setupViewPager2(viewPager, object : TabLayoutCallback {
            override fun onConfigureTab(tab: Tab, position: Int) {
                tab.text = pageTitles[position]
            }
        })
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        val path = pagePaths[group.indexOfChild(group.findViewById<RadioButton>(checkedId))]
        replaceFragment(buildFragment(path))
    }

    override fun onRouter(routerUri: NLRouterInfo): Boolean {
        viewPager.currentItem = max(pagePaths.indexOf(routerUri.fragment), 0)
        return true
    }

}