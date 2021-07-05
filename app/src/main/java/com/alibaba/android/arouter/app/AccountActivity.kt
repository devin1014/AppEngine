package com.alibaba.android.arouter.app

import android.os.Bundle
import com.alibaba.android.arouter.app.core.linkToFragment
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_ACCOUNT)
class AccountActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(linkToFragment(Constants.ROUTER_FRAGMENT_AUTH_SIGNIN))
    }

}