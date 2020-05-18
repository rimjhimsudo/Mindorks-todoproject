package com.example.todoapp.view

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.todoapp.R
import com.example.todoapp.onboarding.OnBoardingActivity
import com.example.todoapp.utils.PrefConstants
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId


class SplashActivity : AppCompatActivity() {
    //private val PERMISSION_REQUEST_CODE = 200

    lateinit  var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

       /* if (!checkPermission()) {
            openActivity();
        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {
                openActivity();
            }
        }*/
        setupsharedpref()
        checkloginstatus()
        getfcmToken()
    }

    private fun getfcmToken() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("splash", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result?.token

                    // Log and toast
                    //val msg = getString(R.string.msg_token_fmt, token)
                    Log.d("splash", token)
                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
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
        val isBoardgSuccess= sharedPreferences.getBoolean(PrefConstants.ON_BOARDED_SUCCESS,false)

        if (isLoggedin) { //note activiity
            val intent1 = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent1)
        } else { //logn actvity
            if (isBoardgSuccess){
                val intent = Intent(this@SplashActivity , LoginActivity::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                startActivity(intent)
            }

        }
        finish()
    }
}