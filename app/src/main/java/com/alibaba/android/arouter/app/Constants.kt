package com.alibaba.android.arouter.app

object Constants {
    const val TAG_LOG = "AppDemo"

    const val GROUP_VIEWPAGER2 = "viewpager2"
    const val GROUP_FRAME_LAYOUT = "frame_layout"
    private const val GROUP_MAIN = GROUP_VIEWPAGER2

    const val ACTION_AUTH_CHANGED = "action.auth.changed"

    //    activity
    const val ROUTER_ACTIVITY_SPLASH = "/app/splash"
    const val ROUTER_ACTIVITY_MAIN = "/$GROUP_MAIN/main"
    const val ROUTER_ACTIVITY_ACCOUNT = "/app/account"
    const val ROUTER_ACTIVITY_DETAIL = "/app/detail"

    //    fragment
    const val ROUTER_FRAGMENT_HOME = "/main/home"
    const val ROUTER_FRAGMENT_SCHEDULE = "/main/schedule"
    const val ROUTER_FRAGMENT_SETTINGS = "/main/settings"
    const val ROUTER_FRAGMENT_TAB = "/main/tab"

    const val ROUTER_FRAGMENT_AUTH_SIGNIN = "/account/signin"
    const val ROUTER_FRAGMENT_AUTH_REGISTER = "/account/register"
    const val ROUTER_FRAGMENT_AUTH_PURCHASE = "/account/purchase"
    const val ROUTER_FRAGMENT_AUTH_INFO = "/account/info"
}