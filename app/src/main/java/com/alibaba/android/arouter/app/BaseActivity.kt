package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired

abstract class BaseActivity : AppCompatActivity() {

    @JvmField
    @Autowired(name = Constants.EXTRA_KEY_DATA_URI)
    var dataUri: Uri? = null

//    private val intentUriLiveData: MutableLiveData<Uri> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.printIntentInfo(this)
        setContentView(getContentId())
        if (supportActionBar != null) supportActionBar?.title = javaClass.simpleName
        else actionBar?.title = javaClass.simpleName
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