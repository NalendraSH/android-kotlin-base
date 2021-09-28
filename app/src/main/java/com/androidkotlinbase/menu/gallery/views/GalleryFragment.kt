package com.androidkotlinbase.menu.gallery.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.androidkotlinbase.R
import com.androidkotlinbase.databinding.FragmentGalleryBinding
import com.androidkotlinbase.menu.gallery.adapters.GalleryAdapter
import com.androidkotlinbase.menu.gallery.viewmodels.FragmentGalleryViewModel
import com.androidkotlinbase.menu.list.models.Models
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment() {

    private lateinit var binding: FragmentGalleryBinding
    private val viewModel by inject<FragmentGalleryViewModel>()

    private lateinit var adapter: GalleryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bleach = viewModel

        setupSwipeRefresh()
        setupRecyclerView()
        observeLiveData()

        viewModel.getGalleryBleach()
        viewModel.getLocalBleach()
    }

    private fun observeLiveData() {
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.swipeRefreshBleach.isRefreshing = it
        })
        viewModel.liveDataList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it.results)
            for (result in it.results) {
                val res = Models.Content(result.mal_id, result.url, result.image_url, result.title, result.synopsis, result.score)
                viewModel.submitLocalBleach(res)
            }
        })
        viewModel.bleachList.observe(viewLifecycleOwner, Observer {
            Log.d("bleach_list", it.toString())
        })
    }

    private fun setupRecyclerView() {
        val spanCount = context?.resources?.getInteger(R.integer.gallery_span_count)
        binding.recyclerViewBleach.layoutManager = spanCount?.let { GridLayoutManager(context, it) }
        adapter = GalleryAdapter()
        binding.recyclerViewBleach.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshBleach.setOnRefreshListener {
            viewModel.getGalleryBleach()
        }
    }

}
