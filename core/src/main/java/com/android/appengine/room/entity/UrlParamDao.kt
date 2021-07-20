package com.android.appengine.room.entity

import androidx.room.*
import com.android.appengine.room.Constants

@Dao
interface UrlParamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg urlParams: UrlParam)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(vararg urlParams: UrlParam)

    @Delete
    suspend fun delete(vararg urlParams: UrlParam)

    @Query("SELECT * FROM ${Constants.TABLET_URL_PARAMS}")
    suspend fun getUrlParams(): List<UrlParam>

    @Query("SELECT * FROM ${Constants.TABLET_URL_PARAMS} WHERE nlid LIKE :nlid")
    suspend fun getUrlParam(nlid: String): UrlParam?
}