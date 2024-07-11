package com.androidkotlinbase.menu.gallery.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSwipeRefresh()
        setupRecyclerView()
        observeLiveData()

        viewModel.getGalleryBleach()
        viewModel.getLocalBleach()
    }

    private fun observeLiveData() {
        viewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeBleach.isRefreshing = it
        })
        viewModel.liveDataList.observe(viewLifecycleOwner, {
            adapter.submitList(it.data)
            for (result in it.data) {
                val res = Models.Content(result.mal_id, result.url, result.images.webp.image_url, result.title, result.synopsis, result.score)
                viewModel.submitLocalBleach(res)
            }
        })
        viewModel.bleachList.observe(viewLifecycleOwner, {
            Log.d("bleach_list", it.toString())
        })
    }

    private fun setupRecyclerView() {
        val spanCount = context?.resources?.getInteger(R.integer.gallery_span_count)
        binding.rvBleach.layoutManager = spanCount?.let { GridLayoutManager(context, it) }
        adapter = GalleryAdapter()
        binding.rvBleach.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeBleach.setOnRefreshListener {
            viewModel.getGalleryBleach()
        }
    }

}
