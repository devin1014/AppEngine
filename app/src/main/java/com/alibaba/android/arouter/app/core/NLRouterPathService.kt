//package com.alibaba.android.arouter.app.core
//
//import android.content.Context
//import android.content.pm.PackageManager
//import android.net.Uri
//import com.alibaba.android.arouter.app.Constants
//import com.alibaba.android.arouter.facade.annotation.Route
//import com.alibaba.android.arouter.facade.service.PathReplaceService
//import com.alibaba.android.arouter.launcher.ARouter
//
//@Route(path = "/app/service/nlrouter_path")
//class NLRouterPathService : PathReplaceService {
//
////    private companion object {
////        const val SCHEME_HTTP = "http"
////        const val SCHEME_HTTPS = "https"
////    }
//
//    private var _scheme: String = "arouter"
//    private var _host: String = "arouter.app.com"
//
//    override fun init(context: Context) {
//        try {
//            val appInfo = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
//            _scheme = appInfo.metaData.getString("arouter_scheme", _scheme)
//            _host = appInfo.metaData.getString("arouter_host", _host)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        ARouter.logger.info(Constants.TAG_LOG, "NLRouterPathService init, scheme=$_scheme ,host=$_host ")
//    }
//
//    override fun forString(path: String): String {
//        return when (path) {
//            "main_home" -> Constants.ROUTER_FRAGMENT_HOME
//            "main_schedule" -> Constants.ROUTER_FRAGMENT_SCHEDULE
//            "main_settings" -> Constants.ROUTER_FRAGMENT_SETTINGS
//            else -> path
//        }
//    }
//
//    override fun forUri(uri: Uri): Uri = uri
//
////    override fun forUri(uri: Uri): Uri {
////        if (validUri(uri)) return uri
////        val builder = Uri.Builder().apply {
////            scheme(_scheme)
////            authority(_host)
////            path(uri.host)
////            if (!uri.path.isNullOrEmpty() && uri.path!!.length > 1) {
////                appendPath(uri.path!!.substring(1))
////            }
////            query(uri.query)
////        }
////        val result = builder.build()
////        ARouter.logger.info(Constants.TAG_LOG, "NLRouterPathService oldUri: $uri")
////        ARouter.logger.info(Constants.TAG_LOG, "NLRouterPathService newUri: $result")
////        return result
////    }
////
////    private fun validUri(uri: Uri): Boolean {
////        if (SCHEME_HTTP != uri.scheme && SCHEME_HTTPS != uri.scheme) return false
////        if (_host != uri.host) return false
////        if (uri.path.isNullOrEmpty() || uri.path!!.length < 3) return false
////        return true
////    }
//}