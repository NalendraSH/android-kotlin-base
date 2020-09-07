package com.androidkotlinbase.menu.list.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.androidkotlinbase.menu.list.models.Models

class ItemListViewModel(list: Models.Results): ViewModel(){

    var title: ObservableField<String> = ObservableField(list.title)
    var synopsis: ObservableField<String> = ObservableField(list.synopsis)
    var score: ObservableField<Float> = ObservableField(list.score)
    var image_url: ObservableField<String> = ObservableField(list.image_url)

}