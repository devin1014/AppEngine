package com.android.appengine.room.entity

import androidx.room.TypeConverter
import org.json.JSONObject

class Converters {
    @TypeConverter
    fun json2String(value: JSONObject): String = value.toString()

    @TypeConverter
    fun string2Json(value: String): JSONObject = JSONObject(value)
}