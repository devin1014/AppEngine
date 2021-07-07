package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.core.NLRouter
import com.alibaba.android.arouter.app.core.NLRouterUri
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired

@Suppress("PropertyName")
abstract class BaseActivity : AppCompatActivity(), NLRouter.OnRouter {

    @JvmField
    @Autowired
    var _path: String? = null

    @JvmField
    @Autowired
    var _uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.printIntentInfo(this, "onCreate")
        setContentView(getContentId())
        if (supportActionBar != null) supportActionBar?.title = javaClass.simpleName
        else actionBar?.title = javaClass.simpleName
    }

    protected open fun getContentId() = R.layout.activity_content

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Utils.printIntentInfo(this, "onNewIntent")
        (intent.getSerializableExtra(Constants.EXTRA_KEY_ROUTER_URI) as? NLRouterUri)?.run {
            onRouter(this)
        }
    }

    override fun onRouter(routerUri: NLRouterUri): Boolean = false

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