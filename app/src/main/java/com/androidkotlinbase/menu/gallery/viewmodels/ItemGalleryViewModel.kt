package com.androidkotlinbase.menu.gallery.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.androidkotlinbase.menu.list.models.Models

class ItemGalleryViewModel(list: Models.Results): ViewModel(){

    var image_url: ObservableField<String> = ObservableField(list.image_url)

}