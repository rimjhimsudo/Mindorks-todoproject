package com.example.todoapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.utils.PrefConstants
import com.example.todoapp.R

class SplashActivity : AppCompatActivity() {
    lateinit  var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupsharedpref()
        checkloginstatus()
    }

    private fun setupsharedpref() {
        sharedPreferences = getSharedPreferences(PrefConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    fun checkloginstatus() {
        //if user not logged then take to login screen
        // //else take to notes activity screen
        //check some data locally
        ///term - Share preferences
        val isLoggedin = sharedPreferences.getBoolean(PrefConstants.IS_LOGGED_IN, false)
        if (isLoggedin) { //note activiity
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else { //logn actvity
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}