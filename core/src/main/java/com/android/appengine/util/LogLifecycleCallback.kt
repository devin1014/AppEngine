package com.android.appengine.util

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks

internal class LogLifecycleCallback private constructor() {

    @Suppress("ObjectPropertyName", "unused")
    companion object {
        private var _logActivity: ActivityLifecycleCallbacks? = null
        val activityCallbacks: ActivityLifecycleCallbacks
            get() {
                if (_logActivity == null) {
                    _logActivity = LogActivityLifecycleCallbacks(registerChildFragment = false, hierarchyChild = false)
                }
                return _logActivity!!
            }

        private var _logActivityFragment: ActivityLifecycleCallbacks? = null
        val activityFragmentCallbacks: ActivityLifecycleCallbacks
            get() {
                if (_logActivityFragment == null) {
                    _logActivityFragment = LogActivityLifecycleCallbacks(registerChildFragment = true, hierarchyChild = false)
                }
                return _logActivityFragment!!
            }

        private var _logActivityFragmentWithAllChildren: ActivityLifecycleCallbacks? = null
        val activityAllFragmentCallbacks: ActivityLifecycleCallbacks
            get() {
                if (_logActivityFragmentWithAllChildren == null) {
                    _logActivityFragmentWithAllChildren = LogActivityLifecycleCallbacks(registerChildFragment = true, hierarchyChild = true)
                }
                return _logActivityFragmentWithAllChildren!!
            }
    }

    // ------------------------------------------------------------------------------------
    // ---- Activity
    // ---- NOTE: all 'pre' function only be called after Android 10(29).
    // ---- NOTE: all 'pre' function only be called after Android 10(29).
    // ---- NOTE: all 'pre' function only be called after Android 10(29).
    // ------------------------------------------------------------------------------------
    private class LogActivityLifecycleCallbacks(
        val registerChildFragment: Boolean = true,
        val hierarchyChild: Boolean = false
    ) : ActivityLifecycleCallbacks {

        // ------------------------------------------------------------------------------------
        // ---- create
        // ------------------------------------------------------------------------------------
        override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPreCreated")
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onCreated")
            if (registerChildFragment && activity is FragmentActivity) {
                activity.supportFragmentManager.registerFragmentLifecycleCallbacks(LogFragmentLifecycleCallbacks, hierarchyChild)
            }
        }

        override fun onActivityPostCreated(activity: Activity, savedInstanceState: Bundle?) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostCreated")
        }

        // ------------------------------------------------------------------------------------
        // ---- start
        // ------------------------------------------------------------------------------------
        override fun onActivityPreStarted(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPreStarted")
        }

        override fun onActivityStarted(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onStarted")
        }

        override fun onActivityPostStarted(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostStarted")
        }

        // ------------------------------------------------------------------------------------
        // ---- resume
        // ------------------------------------------------------------------------------------
        override fun onActivityPreResumed(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPreResumed")
        }

        override fun onActivityResumed(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onResumed")
        }

        override fun onActivityPostResumed(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostResumed")
        }

        // ------------------------------------------------------------------------------------
        // ---- pause
        // ------------------------------------------------------------------------------------
        override fun onActivityPrePaused(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPrePaused")
        }

        override fun onActivityPaused(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPaused")
        }

        override fun onActivityPostPaused(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostPaused")
        }

        // ------------------------------------------------------------------------------------
        // ---- saveInstanceState
        // ------------------------------------------------------------------------------------
        override fun onActivityPreSaveInstanceState(activity: Activity, outState: Bundle) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPreSaveInstanceState")
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onSaveInstanceState")
        }

        override fun onActivityPostSaveInstanceState(activity: Activity, outState: Bundle) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostSaveInstanceState")
        }

        // ------------------------------------------------------------------------------------
        // ---- stop
        // ------------------------------------------------------------------------------------
        override fun onActivityPreStopped(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPreStopped")
        }

        override fun onActivityStopped(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onStopped")
        }

        override fun onActivityPostStopped(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostStopped")
        }

        // ------------------------------------------------------------------------------------
        // ---- destroy
        // ------------------------------------------------------------------------------------
        override fun onActivityPreDestroyed(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPreDestroyed")
        }

        override fun onActivityDestroyed(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onDestroyed")
            if (registerChildFragment && activity is FragmentActivity) {
                activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(LogFragmentLifecycleCallbacks)
            }
        }

        override fun onActivityPostDestroyed(activity: Activity) {
            EngineLog.debug("${activity.javaClass.simpleName} -> onPostDestroyed")
        }

    }

    // ------------------------------------------------------------------------------------
    // ---- Fragment
    // ------------------------------------------------------------------------------------
    private object LogFragmentLifecycleCallbacks : FragmentLifecycleCallbacks() {
        override fun onFragmentPreAttached(fm: FragmentManager, f: Fragment, context: Context) {
            EngineLog.debug("${f.javaClass.simpleName} -> onPreAttached")
        }

        override fun onFragmentAttached(fm: FragmentManager, f: Fragment, context: Context) {
            EngineLog.debug("${f.javaClass.simpleName} -> onAttached")
        }

        override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            EngineLog.debug("${f.javaClass.simpleName} -> onActivityCreated")
        }

        override fun onFragmentPreCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            EngineLog.debug("${f.javaClass.simpleName} -> onPreCreated")
        }

        override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
            EngineLog.debug("${f.javaClass.simpleName} -> onCreated")
        }

        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            EngineLog.debug("${f.javaClass.simpleName} -> onViewCreated")
        }

        override fun onFragmentStarted(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onStarted")
        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onResumed")
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onPaused")
        }

        override fun onFragmentSaveInstanceState(fm: FragmentManager, f: Fragment, outState: Bundle) {
            EngineLog.debug("${f.javaClass.simpleName} -> onSaveInstanceState")
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onStopped")
        }

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onViewDestroyed")
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onDestroyed")
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            EngineLog.debug("${f.javaClass.simpleName} -> onDetached")
        }
    }
}