package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.bean.AppConfig
import com.alibaba.android.arouter.app.service.AppConfigService
import com.alibaba.android.arouter.app.util.AppLog
import com.alibaba.android.arouter.facade.annotation.Route
import com.android.app.http.ResponseResult
import com.android.app.http.RetrofitService
import com.android.app.http.module.safetyCall
import com.android.appengine.router.RouterInfo

@Route(path = Constants.ROUTER_FRAGMENT_HOME)
class HomeFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContentText()

        lifecycleScope.launchWhenResumed {
//            val homeDeferred: Deferred<ResponseResult<Home>> = async { RetrofitService.get(HomeHttpService::class).getHomeResult() }
//            val result: ResponseResult<Home> = safetyCall { RetrofitService.get(HomeHttpService::class).getHomeResult() }
//            if (result.isSuccess) {
//                AppLog.debug("success")
//            } else if (result.isFailure) {
//                AppLog.warning("failed: ${result.getException()}")
//            }

            val config: ResponseResult<AppConfig> = safetyCall { RetrofitService.get(AppConfigService::class).getAppConfig() }
            if (config.isSuccess) {
//                RoomDatabase.database.urlParamDao().insert()
                AppLog.debug("success")
            } else if (config.isFailure) {
                AppLog.warning("failed: ${config.getException()}")
            }
        }
    }

    override fun onRouter(routerUri: RouterInfo): Boolean {
        val handled = super.onRouter(routerUri)
        if (handled) setContentText()
        return handled
    }
}