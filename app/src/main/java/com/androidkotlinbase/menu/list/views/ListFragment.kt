package com.androidkotlinbase.menu.list.views

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidkotlinbase.R
import com.androidkotlinbase.databinding.FragmentListBinding
import com.androidkotlinbase.menu.list.adapters.ListAdapter
import com.androidkotlinbase.menu.list.viewmodels.FragmentListViewModel
import org.koin.android.ext.android.inject

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel by inject<FragmentListViewModel> ()

    private lateinit var adapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.naruto = viewModel

        setupSwipeRefresh()
        setupRecyclerView()
        observeLiveData()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        activity?.recreate()
    }

    private fun observeLiveData() {
        viewModel.narutoList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        viewModel.getLoadingState().observe(viewLifecycleOwner, Observer {
        })
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerViewNaruto.layoutManager = layoutManager
        adapter = ListAdapter()
        binding.recyclerViewNaruto.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshNaruto.setOnRefreshListener {
            viewModel.refreshListNaruto()
        }
    }

}
