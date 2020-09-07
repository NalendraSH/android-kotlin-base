package com.androidkotlinbase.menu.gallery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidkotlinbase.R
import com.androidkotlinbase.databinding.ItemGalleryBinding
import com.androidkotlinbase.menu.gallery.viewmodels.ItemGalleryViewModel
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.utils.AdapterCallback

/**
 * Created by nalen on 07/09/20.
 */
class GalleryAdapter: ListAdapter<Models.Results, GalleryAdapter.GalleryViewHolder>(AdapterCallback.DiffListCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemGalleryBinding = DataBindingUtil.inflate(inflater, R.layout.item_gallery, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val bleachModel = getItem(holder.adapterPosition)
        holder.bindData(bleachModel)
    }

    class GalleryViewHolder(private val binding: ItemGalleryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(bleachModel: Models.Results) {
            binding.bleachList = ItemGalleryViewModel(bleachModel)
            binding.executePendingBindings()
        }
    }
}