package com.tinkoff.hr.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tinkoff.hr.R
import com.tinkoff.hr.viewmodels.RegisterViewModel

class MainActivity : AppCompatActivity(){

    private val registerViewModel: RegisterViewModel by viewModels()

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNav: BottomNavigationView

    private var isLogin = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setUpNav()

        updateUiIfLogin()

        registerViewModel.code.observe(this){
            updateUiIfLogin()
        }
    }

    private fun setUpNav() {
        navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)

        bottomNav.setOnItemReselectedListener {
             navController.navigateUp()
        }
    }

    private fun updateUiIfLogin(){
        if (isLogin){
            navController.graph = navController.navInflater.inflate(R.navigation.nav_graph_main)
            bottomNav.visibility = View.VISIBLE
        } else {
            navController.graph = navController.navInflater.inflate(R.navigation.nav_graph_register)
            bottomNav.visibility = View.GONE
        }
    }

    private fun initViews(){
        bottomNav = findViewById(R.id.bottom_nav)
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }
}