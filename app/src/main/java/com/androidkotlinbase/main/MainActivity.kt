package com.androidkotlinbase.main

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.androidkotlinbase.R
import com.androidkotlinbase.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by inject<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupNavigation()
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragment_container).navigateUp()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbarMain)
    }

    private fun setupNavigation(){
        binding.bottomnavigationMain.itemIconTintList = null
        val navController = Navigation.findNavController(this, R.id.fragment_container)
        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomnavigationMain, navController)
    }

    fun hideNavigation(value: Boolean) {
        if (value) {
            binding.bottomnavigationMain.visibility = View.GONE
        } else {
            binding.bottomnavigationMain.visibility = View.VISIBLE
        }
    }
}