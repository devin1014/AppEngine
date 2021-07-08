package com.alibaba.android.arouter.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.alibaba.android.arouter.app.core.NLRouter
import com.alibaba.android.arouter.app.core.NLRouter.OnRouter
import com.alibaba.android.arouter.app.core.NLRouterInfo
import com.alibaba.android.arouter.app.core.NLRouterParseService
import com.alibaba.android.arouter.app.core.getAppService
import com.alibaba.android.arouter.app.service.AuthService
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter

@Suppress("PropertyName")
abstract class BaseActivity : AppCompatActivity(), OnRouter {

    @JvmField
    @Autowired
    var _path: String? = null

    @JvmField
    @Autowired
    var _routerInfo: NLRouterInfo? = null

    private val authChangedReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            setActionBarTitle()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalBroadcastManager.getInstance(this).registerReceiver(authChangedReceiver, IntentFilter(Constants.ACTION_AUTH_CHANGED))
        Utils.printIntentInfo(this, "onCreate")
        ARouter.getInstance().inject(this)
        setContentView(getContentId())
        setActionBarTitle()
    }

    protected fun setActionBarTitle() {
        val title = "${javaClass.simpleName} Auth:${getAppService(AuthService::class).authenticated}"
        if (supportActionBar != null) supportActionBar?.title = title
        else actionBar?.title = title
    }

    protected open fun getContentId() = R.layout.activity_content

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val pendingData: Uri? = intent.getParcelableExtra(NLRouter.EXTRA_KEY_PENDING_DATA)
        val routerInfo: NLRouterInfo? = if (pendingData != null) {
            getAppService(NLRouterParseService::class).parse(pendingData)
        } else {
            intent.getSerializableExtra(NLRouter.EXTRA_KEY_ROUTER_INFO) as? NLRouterInfo
        }
        if (routerInfo != null) {
            handleRouter(routerInfo)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Utils.printIntentInfo(this, "onNewIntent")
        ARouter.getInstance().inject(this)
        (intent.getSerializableExtra(NLRouter.EXTRA_KEY_ROUTER_INFO) as? NLRouterInfo)?.run {
            handleRouter(this)
        }
    }

    private fun handleRouter(routerInfo: NLRouterInfo) {
        if (!onRouter(routerInfo)) {
            supportFragmentManager.fragments.forEach {
                if (it is OnRouter && it.onRouter(routerInfo)) return@forEach
            }
        }
    }

    override fun onRouter(routerUri: NLRouterInfo): Boolean = false

    override fun onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(authChangedReceiver)
        super.onDestroy()
    }

    // ----------------------------------------------------------------------
    // ---- Tools
    // ----------------------------------------------------------------------
    protected open fun addFragment(fragment: Fragment?) {
        fragment ?: return
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_content, fragment)
            .commit()
    }

    protected open fun replaceFragment(fragment: Fragment?) {
        fragment ?: return
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}