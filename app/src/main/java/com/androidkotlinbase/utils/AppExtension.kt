package com.androidkotlinbase.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.androidkotlinbase.BuildConfig
import com.androidkotlinbase.data.Constant

fun logDebug(message: String) {
    if (BuildConfig.DEBUG) Log.d(Constant.TAG_DEBUG, message)
}

fun logError(message: String) {
    if (BuildConfig.DEBUG) Log.e(Constant.TAG_ERROR, message)
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}