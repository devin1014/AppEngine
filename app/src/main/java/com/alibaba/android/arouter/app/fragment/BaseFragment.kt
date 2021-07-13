package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.R
import com.android.appengine.router.Router.OnRouter
import com.android.appengine.router.RouterInfo
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import java.util.*

@Suppress("PropertyName")
abstract class BaseFragment : Fragment(), OnRouter {

    @JvmField
    @Autowired
    var _path: String? = null

    private val name: String = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentId(), container, false)
    }

    protected open fun getContentId() = R.layout.fragment_content

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Utils.printBundleInfo(this)
        ARouter.getInstance().inject(this)
    }

    @CallSuper
    override fun onRouter(routerUri: RouterInfo): Boolean {
        if (routerUri.fragment == _path) {
            ARouter.getInstance().inject(this)
            return true
        }
        return false
    }

    protected open fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    protected fun setContentText(text: String = buildContent()) {
        (view as? TextView)?.text = text
    }

    protected fun buildContent(): String {
        return with(StringBuilder(name)) {
            arguments?.keySet()?.run {
                for (key in TreeSet(this)) {
                    append("\n$key=${arguments?.get(key)}")
                }
            }
            toString()
        }
    }
}