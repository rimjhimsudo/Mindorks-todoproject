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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.todoapp.R
import com.example.todoapp.utils.PrefConstants


class SplashActivity : AppCompatActivity() {
    //private val PERMISSION_REQUEST_CODE = 200

    lateinit  var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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
    }

   /* private fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
    }

    private fun requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
                alertBuilder.setCancelable(true)
                alertBuilder.setTitle("Permission required")
                alertBuilder.setMessage("Permission required")
                alertBuilder.setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
                    ActivityCompat.requestPermissions(this@SplashActivity, arrayOf(WRITE_EXTERNAL_STORAGE
                            , READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
                })
                val alert: AlertDialog = alertBuilder.create()
                alert.show()
                Log.e("", "permission denied, show dialog")
            } else {
                ActivityCompat.requestPermissions(this@SplashActivity, arrayOf(WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            }
        } else {
            openActivity()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.size > 0 && grantResults.size > 0) {
                var flag = true
                for (i in grantResults.indices) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false
                    }
                }
                if (flag) {
                    openActivity()
                } else {
                    finish()
                }
            } else {
                finish()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun openActivity() { //add your further process after giving permission or to download images from remote server.
    }*/

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