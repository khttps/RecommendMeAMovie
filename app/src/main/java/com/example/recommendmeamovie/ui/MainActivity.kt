package com.example.recommendmeamovie.ui

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.bumptech.glide.Glide
import com.example.recommendmeamovie.R
import com.example.recommendmeamovie.databinding.ActivityMainBinding
import com.example.recommendmeamovie.databinding.NavHeaderBinding
import com.example.recommendmeamovie.util.Constants.IMAGE_URL
import com.example.recommendmeamovie.util.Resource
import com.example.recommendmeamovie.util.Utils
import com.example.recommendmeamovie.util.createRecommendChannel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment).navController
    }

    private val navHeaderBinding: NavHeaderBinding by lazy {
        NavHeaderBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)

        binding.navigationView.addHeaderView(navHeaderBinding.root)

        navController.addOnDestinationChangedListener { _, _, _ ->
            Utils.hideKeyboard(this)
        }

        subscribeObservers()
        createNotificationChannel()
    }

    private fun subscribeObservers() {
        viewModel.session.observe(this) {
            if (it is Resource.Success)
                changeNavMenu(
                    when (it.data) {
                        "" -> R.menu.guest_menu
                        else -> R.menu.user_menu
                    }
                )
        }

        viewModel.account.observe(this) {
            if (it is Resource.Success) {
                Glide.with(this)
                    .load(IMAGE_URL + it.data!!.avatar)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_placeholder)
                    .into(navHeaderBinding.avatar)

                val name = it.data.name.substringBefore(" ")
                navHeaderBinding.hello.text = getString(R.string.hello, name)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun createNotificationChannel() {
        val manager =
            this@MainActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createRecommendChannel(this@MainActivity)
    }

    private fun changeNavMenu(menuRes: Int) {
        binding.navigationView.apply {
            menu.clear()
            inflateMenu(menuRes)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


