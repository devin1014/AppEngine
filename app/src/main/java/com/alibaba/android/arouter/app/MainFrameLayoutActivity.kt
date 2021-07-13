package com.alibaba.android.arouter.app

import android.os.Bundle
import com.alibaba.android.arouter.app.core.RouterInfo
import com.alibaba.android.arouter.app.core.buildFragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab

@Route(path = Constants.ROUTER_ACTIVITY_MAIN, group = Constants.GROUP_FRAME_LAYOUT)
class MainFrameLayoutActivity : BaseActivity(), OnTabSelectedListener {

    private val pagePaths = listOf(Constants.ROUTER_FRAGMENT_HOME, Constants.ROUTER_FRAGMENT_SCHEDULE, Constants.ROUTER_FRAGMENT_SETTINGS)
    private val tabLayout: TabLayout by lazy { findViewById(R.id.main_tab_layout) }
    private val tabMap = mutableMapOf<String, Tab>()

    override fun getContentId(): Int = R.layout.activity_main_framelayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
        tabLayout.addOnTabSelectedListener(this)
        val pageTitles = resources.getStringArray(R.array.MAIN_TABS)
        pageTitles.forEachIndexed { index, value ->
            tabLayout.addTab(tabLayout.newTab().apply {
                text = value
                tag = pagePaths[index]
                tabMap[pagePaths[index]] = this
            }, index == 0)
        }
    }

    override fun onRouter(routerUri: RouterInfo): Boolean {
        tabMap[routerUri.fragment]?.run {
            if (tabLayout.selectedTabPosition != this.position) {
                tabLayout.selectTab(this)
                return true
            }
        }
        return false
    }

    override fun onTabSelected(tab: Tab) {
        replaceFragment(buildFragment(tab.tag as String))
    }

    override fun onTabUnselected(tab: Tab) {
    }

    override fun onTabReselected(tab: Tab) {
    }

}