package com.androidkotlinbase.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.androidkotlinbase.menu.list.models.Models

/**
 * Created by nalen on 07/09/20.
 */
@Dao
interface GalleryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(results: Models.Content)

    @Query("SELECT * FROM content")
    fun getAll(): List<Models.Content>

}