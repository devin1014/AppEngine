package com.alibaba.android.arouter.app.bean

data class DynamicLead(
    val id: String,
    val type: String,
    val title: String,
    val description: String,
    val subTitle: String,
    val image: String,
    val template: String,
    val platform: String,
    val link: String,
    val style: String
)

data class Team(
    val id: String,
    val name: String,
    val code: String,
    val image: String
)

data class Home(
    val dynamicLead: MutableList<DynamicLead>?,
    val teams: MutableList<Team>?
)