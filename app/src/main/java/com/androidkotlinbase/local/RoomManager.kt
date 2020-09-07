package com.androidkotlinbase.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.androidkotlinbase.local.dao.GalleryDao
import com.androidkotlinbase.menu.list.models.Models

@Database(entities = [Models.Content::class], version = 1)
abstract class RoomManager : RoomDatabase() {

    abstract fun bleachDao(): GalleryDao

    companion object{

        private var instance: RoomManager? = null

        fun getInstance(context: Context): RoomManager? {
            if (instance == null){
                synchronized(RoomManager::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RoomManager::class.java,
                        "database.db").build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

    }

}