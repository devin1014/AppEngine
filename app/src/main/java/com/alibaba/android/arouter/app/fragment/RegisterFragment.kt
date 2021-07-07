package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.app.core.getAppService
import com.alibaba.android.arouter.app.service.AuthService
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_FRAGMENT_AUTH_REGISTER)
class RegisterFragment : BaseFragment() {

    override fun getContentId(): Int = R.layout.fragment_register

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.auth_btn_register).setOnClickListener {
            showToast("Registered, Auto signIn.")
            getAppService(AuthService::class).authenticated = true
            requireActivity().finish()
        }
    }

}