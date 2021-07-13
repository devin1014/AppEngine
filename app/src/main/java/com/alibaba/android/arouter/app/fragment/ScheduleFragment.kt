package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.android.appengine.router.RouterInfo
import com.alibaba.android.arouter.app.widget.ListAdapter
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_FRAGMENT_SCHEDULE)
class ScheduleFragment : BaseFragment() {

    @JvmField
    @Autowired
    var position: Int = RecyclerView.NO_POSITION

    private val recyclerView: RecyclerView by lazy { requireView().findViewById(R.id.fragment_list) }

    override fun getContentId(): Int = R.layout.fragment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ListAdapter(requireActivity())
            scrollToPosition(position)
        }
    }

    override fun onRouter(routerUri: RouterInfo): Boolean {
        val handled = super.onRouter(routerUri)
        if (handled) recyclerView.scrollToPosition(position)
        return handled
    }

}