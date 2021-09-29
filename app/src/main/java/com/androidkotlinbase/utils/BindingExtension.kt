package com.androidkotlinbase.utils

import androidx.appcompat.widget.AppCompatImageView
import coil.api.load
import com.androidkotlinbase.R

fun AppCompatImageView.setImageUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.placeholder)
        error(R.drawable.placeholder)
    }
}