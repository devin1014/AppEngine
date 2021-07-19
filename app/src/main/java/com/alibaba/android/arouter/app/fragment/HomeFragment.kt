package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.bean.Home
import com.alibaba.android.arouter.facade.annotation.Route
import com.android.app.http.ResponseResult
import com.android.app.http.RetrofitService
import com.android.app.http.module.HomeHttpService
import com.android.app.http.module.safetyCall
import com.android.appengine.router.RouterInfo

@Route(path = Constants.ROUTER_FRAGMENT_HOME)
class HomeFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContentText()

        lifecycleScope.launchWhenResumed {
//            val result: ResponseResult<Home> = HomeHttpService::class.call { getHomeResult() }
            val result: ResponseResult<Home> = safetyCall { RetrofitService.get(HomeHttpService::class).getHomeResult() }
//            val result: ResponseResult<Home> = HttpService.get(HomeHttpService::class) { it.getHomeResult() }
            if (result.isSuccess) {
                Log.i("test", result.toString())
            } else if (result.isFailure) {
                Log.w("test", result.toString())
            }
        }
    }

    override fun onRouter(routerUri: RouterInfo): Boolean {
        val handled = super.onRouter(routerUri)
        if (handled) setContentText()
        return handled
    }
}