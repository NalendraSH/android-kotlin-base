package com.androidkotlinbase.local.repositories

import android.app.Application
import com.androidkotlinbase.local.RoomManager
import com.androidkotlinbase.local.dao.GalleryDao
import com.androidkotlinbase.menu.list.models.Models
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GalleryRepository(application: Application) {

    private var bleachDao: GalleryDao

    init {
        val roomManager: RoomManager = RoomManager.getInstance(application.applicationContext)!!
        bleachDao = roomManager.bleachDao()
    }

    fun getLocalBleach(postValue: (List<Models.Content>) -> Unit) {
        GlobalScope.launch {
            val bleachList = bleachDao.getAll()
            postValue(bleachList)
        }
    }

    fun submitLocalBleach(result: Models.Content) {
        GlobalScope.launch {
            bleachDao.insert(result)
        }
    }
}