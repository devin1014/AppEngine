package com.alibaba.android.arouter.app

import android.os.Bundle
import com.alibaba.android.arouter.app.core.NLRouter
import com.alibaba.android.arouter.app.core.NLRouterUri
import com.alibaba.android.arouter.app.core.linkToFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_ACCOUNT)
class AccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NLRouter.route(this)
    }

    override fun onRouter(routerUri: NLRouterUri): Boolean {
        replaceFragment(linkToFragment(routerUri.path))
        return true
    }
}