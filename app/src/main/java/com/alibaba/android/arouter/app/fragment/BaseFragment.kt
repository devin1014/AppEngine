package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.app.core.NLRouterUri
import com.alibaba.android.arouter.app.util.Utils
import com.alibaba.android.arouter.facade.annotation.Autowired

abstract class BaseFragment : Fragment() {

    @JvmField
    @Autowired
    var key: String? = null

    @JvmField
    @Autowired
    var value: String? = null

    @JvmField
    @Autowired(name = Constants.EXTRA_KEY_ROUTER_URI)
    var routerUri: NLRouterUri? = null

    private val name: String = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentId(), container, false)
    }

    protected open fun getContentId() = R.layout.fragment_content

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Utils.printBundleInfo(this)
    }

    protected open fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

    protected fun setContentText() {
        (view as? TextView)?.text = buildContent()
    }

    protected fun buildContent(): String {
        return "Fragment: $name" +
                "\nkey: $key" +
                "\nvalue: $value" +
                "\nrouterUri: $routerUri"

    }

}