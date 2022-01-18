package com.example.myquizapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myquizapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNav()
    }

    private fun initNav() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            run {
                if (destination.id != R.id.navigation_home && destination.id != R.id.navigation_settings && destination.id!=R.id.navigation_history)
                    navView.visibility = View.GONE
                else navView.visibility = View.VISIBLE
            }
        }
        navController.navigate(R.id.navigation_launch)
    }

}