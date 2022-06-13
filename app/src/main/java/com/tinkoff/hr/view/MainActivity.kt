package com.tinkoff.hr.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tinkoff.hr.R
import com.tinkoff.hr.utils.MyUserSharedPref

class MainActivity : AppCompatActivity(){

    private lateinit var navController: NavController
    private var isPressedBack = false
    private val userSharedPref by lazy { MyUserSharedPref(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.navController

        updateUiIfLogin()

        handleLink()
    }

    private fun updateUiIfLogin(){
        if (userSharedPref.isLogin) {
            navController.navigate(R.id.fragmentContentContainer)
        } else {
            navController.navigate(R.id.fragmentRegisterContainer)
        }
    }

    override fun onBackPressed() {
        if (userSharedPref.isLogin) {
            val contentNavController = findNavController(R.id.nav_host_fragment_content)
            val isUp = contentNavController.navigateUp()
            if (!isUp){
                if (isPressedBack) {
                    super.onBackPressed()
                }
                isPressedBack = true
                Toast.makeText(this, "Нажмите ещё раз для выхода", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed({ isPressedBack = false }, 2000)
            }
        } else {
            navController.navigateUp()
        }
    }

    private fun handleLink() {
        val auth = Firebase.auth
        val intent = intent
        val emailLink = intent.data.toString()

        if (auth.isSignInWithEmailLink(emailLink)) {
            val email = userSharedPref.email ?: return

            auth.signInWithEmailLink(email, emailLink)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Successfully signed in with email link!", Toast.LENGTH_SHORT).show()
                        userSharedPref.isLogin = true
                        updateUiIfLogin()
                    } else {
                        Toast.makeText(this, "Error signing in with email link", Toast.LENGTH_SHORT).show()
                        Log.d("exception", "onCreate: ${task.exception}")
                    }
                }

        }
    }
}