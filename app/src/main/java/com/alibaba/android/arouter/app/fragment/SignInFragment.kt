package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.android.appengine.router.buildActivity
import com.android.appengine.router.getAppService
import com.alibaba.android.arouter.app.service.AuthService
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_FRAGMENT_AUTH_SIGNIN)
class SignInFragment : BaseFragment() {

    override fun getContentId(): Int = R.layout.fragment_signin

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.auth_btn_signin).setOnClickListener {
            getAppService(AuthService::class).authenticated = true
            showToast("SignIn success.")
            requireActivity().finish()
        }

        view.findViewById<View>(R.id.auth_btn_register).setOnClickListener {
            requireActivity().buildActivity {
                activity = Constants.ROUTER_ACTIVITY_ACCOUNT
                fragment = Constants.ROUTER_FRAGMENT_AUTH_REGISTER
            }
        }
    }

}