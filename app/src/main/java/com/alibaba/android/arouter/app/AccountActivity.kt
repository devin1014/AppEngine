package com.alibaba.android.arouter.app

import com.alibaba.android.arouter.app.core.NLRouterInfo
import com.alibaba.android.arouter.app.core.buildFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_ACCOUNT)
class AccountActivity : BaseActivity() {

    override fun onRouter(routerUri: NLRouterInfo): Boolean {
        replaceFragment(buildFragment(routerUri.fragment))
        return true
    }
}