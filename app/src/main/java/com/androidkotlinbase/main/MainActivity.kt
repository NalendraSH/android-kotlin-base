package com.androidkotlinbase.main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidkotlinbase.R
import com.androidkotlinbase.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = viewModel

        setupToolbar()
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragmentContainer).navigateUp()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    private fun setupNavigation(){
        binding.bottomNavigationViewMain.itemIconTintList = null
        val navController = Navigation.findNavController(this, R.id.fragmentContainer)
        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomNavigationViewMain, navController)
    }

    fun hideNavigation(value: Boolean) {
        if (value) {
            binding.bottomNavigationViewMain.visibility = View.GONE
        } else {
            binding.bottomNavigationViewMain.visibility = View.VISIBLE
        }
    }
}