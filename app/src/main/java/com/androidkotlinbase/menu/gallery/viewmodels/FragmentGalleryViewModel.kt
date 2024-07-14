package com.androidkotlinbase.menu.gallery.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.networks.repositories.GalleryRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FragmentGalleryViewModel : ViewModel(), KoinComponent {

    private val networkRepository: GalleryRepository by inject()
    private val localRepository: com.androidkotlinbase.local.repositories.GalleryRepository by inject()

    var liveDataList: MutableLiveData<Models.Response> = MutableLiveData()
    var bleachList: MutableLiveData<List<Models.Content>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getGalleryBleach() {
        isLoading.postValue(true)
        networkRepository.getGallery({
            isLoading.postValue(false)
            liveDataList.postValue(it)
        }, {
            isLoading.postValue(false)
            it.printStackTrace()
        })
    }

    fun getLocalBleach() {
        localRepository.getLocalBleach {
            bleachList.postValue(it)
        }
    }

    fun submitLocalBleach(result: Models.Content) {
        localRepository.submitLocalBleach(result)
    }
}