package com.tinkoff.hr.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tinkoff.hr.R
import com.tinkoff.hr.utils.setupWithNavController
import com.tinkoff.hr.viewmodels.RegisterViewModel

class MainActivity : AppCompatActivity(){

    private val registerViewModel: RegisterViewModel by viewModels()

    private lateinit var navController: NavController

    private var isLogin = false //todo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController

        updateUiIfLogin()

        registerViewModel.code.observe(this){
            isLogin = true
            updateUiIfLogin()
        }
    }

    private fun updateUiIfLogin(){
        if (isLogin) {
            navController.navigate(R.id.fragmentContentContainer)
        } else {
            navController.navigate(R.id.fragmentRegisterContainer)
        }
    }

    override fun onBackPressed() {
        if (isLogin) {
            val contentNavController = findNavController(R.id.nav_host_fragment_content)
            val isUp = contentNavController.navigateUp()
            if (!isUp){
                super.onBackPressed()
            }
        } else {
            navController.navigateUp()
        }
    }
}