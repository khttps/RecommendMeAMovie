package com.example.recommendmeamovie.ui

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.ActivityMainBinding
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.Utils
import com.example.recommendmeamovie.util.createRecommendChannel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
    }

    private val drawerLayout: DrawerLayout by lazy {
        binding.drawerLayout
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, _, _ ->
            Utils.hideKeyboard(this)
        }

        viewModel.session.observe(this) {
            changeNavMenu(
                when(it) {
                    is Resource.Success -> R.menu.user_menu
                    else -> R.menu.guest_menu
                }
            )
        }

        createNotificationChannel()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout) || super.onSupportNavigateUp()
    }

    private fun createNotificationChannel() {
        val manager = this@MainActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createRecommendChannel(this@MainActivity)
    }

    private fun changeNavMenu(menuRes: Int) {
        binding.navigationView.apply {
            menu.clear()
            inflateMenu(menuRes)
        }
    }
}


