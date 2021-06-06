package com.example.recommendmeamovie.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.datastore.dataStore

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.ActivityMainBinding
import com.example.recommendmeamovie.util.Utils
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var notificationChannel: NotificationChannel

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupNavigationDrawer()
        createNotificationChannel()
    }

    private fun setupNavigationDrawer() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.apply {
            navView.setupWithNavController(navController)
            navView.setNavigationItemSelectedListener(this@MainActivity)
            appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
            toolbar.setupWithNavController(navController, appBarConfiguration)
        }
        
        navController.addOnDestinationChangedListener { _, _, _ ->
            Utils.hideKeyboard(this)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationManager.createNotificationChannel(notificationChannel)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sign_in) {

            viewModel.url.observe(this@MainActivity) {
                it?.let {
                    println("XXX$it")
                    val intent = Intent(Intent.ACTION_VIEW, it)
                    startActivity(intent)
                }

            }
        }
        return true
    }
}


