package com.alibaba.android.arouter.app.widget

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.app.bean.Game
import com.alibaba.android.arouter.app.util.AppLog
import com.alibaba.android.arouter.app.util.getEntryName
import com.alibaba.android.arouter.app.util.getRadix16Code
import com.android.appengine.router.buildActivity
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter(private val activity: Activity) : Adapter<ListHolder>(), OnClickListener {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)
    private val layoutInflater = LayoutInflater.from(activity)
    private val list = mutableListOf<String>()

    init {
        for (index in 1..100) {
            list.add(index.toString())
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        AppLog.info("onAttachedToRecyclerView: ${recyclerView.getEntryName()}")
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        AppLog.warning("onDetachedFromRecyclerView: ${recyclerView.getEntryName()}")
    }

    override fun onViewAttachedToWindow(holder: ListHolder) {
        AppLog.debug("[${holder.adapterPosition + 1}] onViewAttachedToWindow: ${holder.getRadix16Code()}")
    }

    override fun onViewDetachedFromWindow(holder: ListHolder) {
        AppLog.warning("[${holder.adapterPosition + 1}] onViewDetachedFromWindow: ${holder.getRadix16Code()}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        AppLog.info("onCreateViewHolder: viewType=$viewType")
        return ListHolder(layoutInflater.inflate(R.layout.item_list, parent, false), this)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        AppLog.debug("[${position + 1}] onBindViewHolder: ${holder.getRadix16Code()}")
        holder.setData(list[position])
    }

    override fun onViewRecycled(holder: ListHolder) {
        AppLog.info("[${holder.adapterPosition + 1}] onViewRecycled: ${holder.getRadix16Code()}")
    }

    override fun onFailedToRecycleView(holder: ListHolder): Boolean {
        AppLog.warning("[${holder.adapterPosition + 1}] onFailedToRecycleView: ${holder.getRadix16Code()}")
        return false
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = position / 10

    override fun onClick(v: View) {
        val game = Game().apply {
            id = "ext_${v.tag as String}"
            name = "this is test game"
            date = dateFormat.format(Date())
        }
        activity.buildActivity {
            activity = Constants.ROUTER_ACTIVITY_DETAIL
            params["game"] = game
        }
    }
}

class ListHolder(itemView: View, clickListener: OnClickListener) : ViewHolder(itemView) {

    init {
        itemView.setOnClickListener(clickListener)
    }

    fun setData(data: String) {
        itemView.tag = data
        (itemView as? TextView)?.text = data
    }
}