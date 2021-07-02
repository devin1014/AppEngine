package com.alibaba.android.arouter.app.bean

class Game {
    var id: String? = null
    var name: String? = null
    var date: String? = null

    override fun toString(): String {
        return "Game{" +
                "\n    id: $id" +
                "\n    name: $name" +
                "\n    date: $date" +
                "\n}"
    }
}