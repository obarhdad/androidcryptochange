package com.android.contact.android.crypto.change.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.contact.android.crypto.change.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navController = findNavController(R.id.mainNavFragment)
        //NavigationUI.setupWithNavController(bottomNavView, navController)
    }
}