package com.android.appengine.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.appengine.room.Constants
import org.json.JSONObject

@Entity(tableName = Constants.TABLET_URL_PARAMS)
data class UrlParam(
    @PrimaryKey val nlid: String,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "params") val params: JSONObject? = null
)