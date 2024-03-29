package com.alibaba.android.arouter.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.app.widget.FragmentPagerAdapter2
import com.alibaba.android.arouter.app.widget.TabLayoutCompat
import com.alibaba.android.arouter.app.widget.TabLayoutCompat.TabLayoutCallback
import com.alibaba.android.arouter.facade.annotation.Route
import com.android.appengine.router.RouterInfo
import com.android.appengine.router.buildFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab
import kotlin.math.max

@Route(path = Constants.ROUTER_ACTIVITY_MAIN, group = Constants.GROUP_VIEWPAGER2)
class MainViewPager2Activity : BaseActivity() {

    private val pagePaths = listOf(
        Constants.ROUTER_FRAGMENT_HOME,
        Constants.ROUTER_FRAGMENT_SCHEDULE,
        Constants.ROUTER_FRAGMENT_SETTINGS
    )
    private val pageTitles = arrayOf("Home", "Schedule", "Setting")

    private val viewPager: ViewPager2 by lazy { findViewById(R.id.main_view_pager) }

    override fun getContentId(): Int = R.layout.activity_main_viewpager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
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

    override fun onRouter(routerUri: RouterInfo): Boolean {
        viewPager.currentItem = max(pagePaths.indexOf(routerUri.fragment), 0)
        return true
    }

}