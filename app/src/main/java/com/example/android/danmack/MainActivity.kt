package com.example.android.danmack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.android.danmack.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var ui : ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)

        ui = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViews()

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            ui.bottomNavigationViewMain.isVisible = true

            when (destination.id) {
                R.id.exploreDetailFragment -> {
                    ui.bottomNavigationViewMain.isVisible = false
                }
                R.id.loginFragment -> {
                    ui.bottomNavigationViewMain.isVisible = false
                }
                R.id.signUpFragment -> {
                    ui.bottomNavigationViewMain.isVisible = false
                }
            }
        }


    }



    private fun setupViews() {

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main)

        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }

//        ui.bottomNavigationViewMain.setOnNavigationItemSelectedListener {
//
//        }



        ui.bottomNavigationViewMain.setupWithNavController(navController)
//
//        appBarConfiguration = AppBarConfiguration(
//            topLevelDestinationIds = setOf(
//                R.id.exploreFragment,
//                R.id.playlistFragment,
//                R.id.searchFragment,
//                R.id.libraryFragment
//            )
//        )

    }



    fun showBottomNavigation () {
        ui.bottomNavigationViewMain.visibility = View.VISIBLE
    }

    fun hideBottomNavigation () {
        ui.bottomNavigationViewMain.visibility = View.GONE
    }



//    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//
//        // Hide the status bar.
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//
//        // Remember that you should never show the action bar if the
//        // status bar is hidden, so hide that too if necessary.
//        actionBar?.hide()
//    }
}