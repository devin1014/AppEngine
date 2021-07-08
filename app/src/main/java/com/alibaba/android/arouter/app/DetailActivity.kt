package com.alibaba.android.arouter.app

import android.os.Bundle
import android.widget.TextView
import com.alibaba.android.arouter.app.bean.Game
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constants.ROUTER_ACTIVITY_DETAIL)
class DetailActivity : BaseActivity() {

    @JvmField
    @Autowired
    var game: Game? = null

    override fun getContentId(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<TextView>(R.id.detail_text).text = game?.toString()
    }
}