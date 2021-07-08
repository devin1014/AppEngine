package com.alibaba.android.arouter.app.bean

import java.io.Serializable

class Game : Serializable {
    var id: String? = null
    var name: String? = null
    var date: String? = null

    override fun toString(): String {
        return "Game{ " +
                "id:\'$id\', " +
                "name:\'$name\', " +
                "date:\'$date\' " +
                "}"
    }
}