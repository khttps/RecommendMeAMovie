package com.example.recommendmeamovie.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.ActivityMainBinding
import com.example.recommendmeamovie.util.Utils
import com.example.recommendmeamovie.util.createRecommendChannel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel.loggedIn.observe(this) {
            it?.let {
                changeNavMenu(
                    when(it) {
                        true -> R.menu.user_menu.also { viewModel.startSession() }
                        else -> R.menu.guest_menu
                    }
                )
            }
        }

        setupNavigation()
        createNotificationChannel()

        navController.addOnDestinationChangedListener { _, _, _ ->
            Utils.hideKeyboard(this)
        }
    }

    override fun onResume() {
        super.onResume()
        intent.data?.let { viewModel.startSession() }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.apply {
            navView.setupWithNavController(navController)
            appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
            toolbar.setupWithNavController(navController, appBarConfiguration)
        }

        val signOutMenuItem = binding.navView.menu.findItem(R.id.log_out)
        signOutMenuItem?.apply {
            setOnMenuItemClickListener {
                showLogoutDialog()
                true
            }
        }
    }

    private fun createNotificationChannel() {
        val manager = this@MainActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createRecommendChannel(this@MainActivity)
    }

    private fun changeNavMenu(menuRes: Int) {
        binding.navView.apply {
            menu.clear()
            inflateMenu(R.menu.user_menu)
        }
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle(resources.getString(R.string.dialog_title))
            .setMessage(resources.getString(R.string.supporting_text))
            .setNegativeButton(resources.getString(R.string.dialog_decline)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.dialog_accept)) { dialog, _ ->
                viewModel.clearSession()
                dialog.dismiss()
            }.show()
    }

}


