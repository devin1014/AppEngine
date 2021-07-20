@file:Suppress("ObjectPropertyName")

package com.android.appengine.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.appengine.room.entity.Converters
import com.android.appengine.room.entity.UrlParam
import com.android.appengine.room.entity.UrlParamDao

@TypeConverters(Converters::class)
@Database(entities = [UrlParam::class], version = 1)
abstract class ConfigurationDatabase : RoomDatabase() {

    abstract fun urlParamDao(): UrlParamDao

}

object RoomDatabase {

    private var _database: ConfigurationDatabase? = null
    val database: ConfigurationDatabase
        get() = _database!!

    fun init(context: Context) {
        if (_database == null) {
            _database = Room.databaseBuilder(context.applicationContext, ConfigurationDatabase::class.java, "urlParamConfigs-db").build()
        }
    }

}