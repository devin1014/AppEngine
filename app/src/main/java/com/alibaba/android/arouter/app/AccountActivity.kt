package com.alibaba.android.arouter.app

import android.os.Bundle
import com.alibaba.android.arouter.app.core.NLRouterInfo
import com.alibaba.android.arouter.app.core.buildFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_ACCOUNT)
class AccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onRouter(routerUri: NLRouterInfo): Boolean {
        replaceFragment(buildFragment(routerUri.fragment))
        return true
    }
}