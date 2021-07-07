package com.alibaba.android.arouter.app.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.app.bean.Game
import com.alibaba.android.arouter.app.core.buildActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import java.util.*

@Route(path = Constants.ROUTER_FRAGMENT_SCHEDULE)
class ScheduleFragment : BaseFragment() {

    @JvmField
    @Autowired
    var position: Int = RecyclerView.NO_POSITION

    override fun getContentId(): Int = R.layout.fragment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ARouter.getInstance().inject(this)
        view.findViewById<RecyclerView>(R.id.fragment_list).apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ListAdapter(requireActivity())
            scrollToPosition(position)
        }
        showToast(buildContent())
    }

    private class ListAdapter(private val activity: Activity) : Adapter<ListHolder>(), OnClickListener {

        private val layoutInflater = LayoutInflater.from(activity)
        private val list = mutableListOf<String>()

        init {
            for (index in 1..100) {
                list.add(index.toString())
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
            return ListHolder(layoutInflater.inflate(R.layout.item_list, parent, false), this)
        }

        override fun onBindViewHolder(holder: ListHolder, position: Int) {
            holder.setData(list[position])
        }

        override fun getItemCount(): Int = list.size

        override fun onClick(v: View) {
            val game = Game().apply {
                id = "ext_${v.tag as String}"
                name = "this is test game"
                date = Date().toString()
            }
            activity.buildActivity {
                activity = Constants.ROUTER_ACTIVITY_DETAIL
                params["game"] = game
            }
        }
    }

    private class ListHolder(itemView: View, clickListener: OnClickListener) : ViewHolder(itemView) {

        init {
            itemView.setOnClickListener(clickListener)
        }

        fun setData(data: String) {
            itemView.tag = data
            (itemView as? TextView)?.text = data
        }
    }
}