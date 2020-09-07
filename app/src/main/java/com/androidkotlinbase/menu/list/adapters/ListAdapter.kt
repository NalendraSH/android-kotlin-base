package com.androidkotlinbase.menu.list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidkotlinbase.R
import com.androidkotlinbase.databinding.ItemListBinding
import com.androidkotlinbase.menu.list.models.Models
import com.androidkotlinbase.menu.list.viewmodels.ItemListViewModel
import com.androidkotlinbase.utils.AdapterCallback
import com.androidkotlinbase.utils.LoadingState

class ListAdapter : PagedListAdapter<Models.Results, RecyclerView.ViewHolder>(AdapterCallback.DiffListCallback) {

    companion object {
        const val VIEW_TYPE_ITEM = 1
        const val VIEW_TYPE_LOAD = 2
    }

    private var loadingState = LoadingState.LOADING

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) VIEW_TYPE_ITEM else VIEW_TYPE_LOAD
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == VIEW_TYPE_ITEM) {
            val binding: ItemListBinding = DataBindingUtil.inflate(inflater, R.layout.item_list, parent, false)
            return NarutoViewHolder(binding)
        }else {
            return LoadMoreViewHolder(inflater.inflate(R.layout.item_load_more, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NarutoViewHolder) {
            val narutoModel = getItem(holder.adapterPosition)
            narutoModel?.let { holder.bindData(it) }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean{
        return super.getItemCount() != 0 && loadingState == LoadingState.LOADING
    }

    class NarutoViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(narutoModel: Models.Results) {
            binding.narutoList = ItemListViewModel(narutoModel)
            binding.executePendingBindings()
        }
    }

    class LoadMoreViewHolder(view: View): RecyclerView.ViewHolder(view)
}