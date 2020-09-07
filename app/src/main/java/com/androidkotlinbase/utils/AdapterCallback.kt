package com.androidkotlinbase.utils

import androidx.recyclerview.widget.DiffUtil
import com.androidkotlinbase.menu.list.models.Models

class AdapterCallback {

    companion object {

        /**
         * diff callback adapter naruto & bleach
         */
        val DiffListCallback = object : DiffUtil.ItemCallback<Models.Results>() {
            override fun areItemsTheSame(
                oldItem: Models.Results,
                newItem: Models.Results
            ): Boolean {
                return oldItem.mal_id == newItem.mal_id
            }

            override fun areContentsTheSame(
                oldItem: Models.Results,
                newItem: Models.Results
            ): Boolean {
                return oldItem == newItem
            }
        }

    }

}