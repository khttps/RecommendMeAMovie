package com.example.recommendmeamovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu

import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.recommendmeamovie.NavigationDirections
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.ActivityMainBinding
import com.example.recommendmeamovie.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setupNavigationDrawer()
    }

    private fun setupNavigationDrawer() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Utils.hideKeyboard(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}


