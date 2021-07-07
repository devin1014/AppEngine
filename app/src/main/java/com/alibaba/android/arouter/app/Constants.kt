package com.alibaba.android.arouter.app

object Constants {
    const val TAG_LOG = "NLRouter"

    const val EXTRA_KEY_PATH = "_path"
    const val EXTRA_KEY_URI = "_uri"
    const val EXTRA_KEY_ROUTER_URI = "_routerUri"
    const val EXTRA_KEY_FRAGMENT_PATH = "_fragmentPath"

    private const val GROUP_ACTIVITY = "app"

    const val ROUTER_ACTIVITY_SPLASH = "/$GROUP_ACTIVITY/splash"
    const val ROUTER_ACTIVITY_MAIN = "/$GROUP_ACTIVITY/main"
    const val ROUTER_ACTIVITY_ACCOUNT = "/$GROUP_ACTIVITY/account"
    const val ROUTER_ACTIVITY_DETAIL = "/$GROUP_ACTIVITY/detail"

    const val ROUTER_FRAGMENT_HOME = "/main/home"
    const val ROUTER_FRAGMENT_SCHEDULE = "/main/schedule"
    const val ROUTER_FRAGMENT_SETTINGS = "/main/settings"

    const val ROUTER_FRAGMENT_AUTH_SIGNIN = "/account/signin"
    const val ROUTER_FRAGMENT_AUTH_REGISTER = "/account/register"
}