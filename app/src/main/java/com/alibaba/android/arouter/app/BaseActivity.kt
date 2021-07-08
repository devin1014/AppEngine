package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.core.NLRouter
import com.alibaba.android.arouter.app.core.NLRouterInfo
import com.alibaba.android.arouter.app.core.NLRouterParseService
import com.alibaba.android.arouter.app.core.getAppService
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter

@Suppress("PropertyName")
abstract class BaseActivity : AppCompatActivity(), NLRouter.OnRouter {

    @JvmField
    @Autowired
    var _path: String? = null

    @JvmField
    @Autowired
    var _routerInfo: NLRouterInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.printIntentInfo(this, "onCreate")
        ARouter.getInstance().inject(this)
        setContentView(getContentId())
        if (supportActionBar != null) supportActionBar?.title = javaClass.simpleName
        else actionBar?.title = javaClass.simpleName
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
            onRouter(routerInfo)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Utils.printIntentInfo(this, "onNewIntent")
        ARouter.getInstance().inject(this)
        (intent.getSerializableExtra(NLRouter.EXTRA_KEY_ROUTER_INFO) as? NLRouterInfo)?.run {
            onRouter(this)
        }
    }

    override fun onRouter(routerUri: NLRouterInfo): Boolean = false

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