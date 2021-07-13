package com.alibaba.android.arouter.app

import com.android.appengine.router.RouterInfo
import com.android.appengine.router.buildFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_ACCOUNT)
class AccountActivity : BaseActivity() {

    override fun onRouter(routerUri: RouterInfo): Boolean {
        replaceFragment(buildFragment(routerUri.fragment))
        return true
    }
}