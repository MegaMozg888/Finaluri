package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottonNavView)
        val controller = findNavController(R.id.nav_host_fragment)

        val fragmentSet = setOf<Int>(
            R.id.firstFragment,
            R.id.secondFragment,

        )


        setupActionBarWithNavController(controller, AppBarConfiguration(fragmentSet))
        bottomNavView.setupWithNavController(controller)
    }
}