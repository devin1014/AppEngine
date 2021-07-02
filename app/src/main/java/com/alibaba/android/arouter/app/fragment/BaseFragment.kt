package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.facade.annotation.Autowired

abstract class BaseFragment : Fragment() {

    @JvmField
    @Autowired
    var key: String? = null

    @JvmField
    @Autowired
    var value: String? = null

    @JvmField
    @Autowired
    var path: String? = null

    @JvmField
    @Autowired
    var uri: String? = null

    private val name: String = javaClass.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getContentId(), container, false)
    }

    protected open fun getContentId() = R.layout.fragment_content

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
                "\npath: $path" +
                "\nuri: $uri"

    }

}