package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter

abstract class BaseActivity : AppCompatActivity() {

    @JvmField
    @Autowired(name = "_intentDataUri")
    var intentUri: Uri? = null

//    private val intentUriLiveData: MutableLiveData<Uri> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.printIntentInfo(this)
        setContentView(getContentId())
//        intentUriLiveData.observe(this, {
//            onRouter(it)
//        })
    }

    protected open fun getContentId() = R.layout.activity_content

    protected open fun onRouter(uri: Uri) {}

//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//        intentUriLiveData.value = intentUri
//    }

//    override fun onDestroy() {
//        intentUriLiveData.removeObservers(this)
//        super.onDestroy()
//    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Utils.printIntentInfo(this)
    }

    // ----------------------------------------------------------------------
    // ---- Tools
    // ----------------------------------------------------------------------
    protected open fun showFragment(fragment: Fragment?) {
        fragment ?: return
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_content, fragment)
            .commit()
    }

    protected open fun showFragment(path: String) {
        showFragment(ARouter.getInstance().build(path).navigation() as? Fragment)
    }

    protected fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}