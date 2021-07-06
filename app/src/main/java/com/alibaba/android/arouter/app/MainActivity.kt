package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.app.core.linkToFragment
import com.alibaba.android.arouter.app.widget.FragmentPagerAdapter2
import com.alibaba.android.arouter.app.widget.TabLayoutCompat
import com.alibaba.android.arouter.app.widget.TabLayoutCompat.TabLayoutCallback
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

@Route(path = Constants.ROUTER_ACTIVITY_MAIN)
class MainActivity : BaseActivity(), OnCheckedChangeListener {

    private val viewPager: ViewPager2 by lazy { findViewById(R.id.main_view_pager) }

    override fun getContentId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initComponent()
        dataUri?.run { onRouter(this) }
    }

    private fun initComponent() {
        val pageTitles = resources.getStringArray(R.array.MAIN_TABS)
        val pagePaths = arrayOf(Constants.ROUTER_FRAGMENT_HOME, Constants.ROUTER_FRAGMENT_SCHEDULE, Constants.ROUTER_FRAGMENT_SETTINGS)
        viewPager.adapter = object : FragmentPagerAdapter2(this) {
            override fun getCount(): Int = pageTitles.size

            override fun getItem(position: Int): Fragment {
                return ARouter.getInstance().build(pagePaths[position]).navigation() as Fragment
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

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        ARouter.getInstance().inject(this)
        dataUri?.run { onRouter(this) }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        val path = group.findViewById<RadioButton>(checkedId).tag as String
        replaceFragment(linkToFragment(path))
    }

    override fun onRouter(uri: Uri) {
        Log.i(Constants.TAG_LOG, "onRouter: $uri")
        replaceFragment(linkToFragment(uri))
    }

}