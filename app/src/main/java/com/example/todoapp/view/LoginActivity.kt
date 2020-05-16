package com.example.todoapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.utils.AppConstants
import com.example.todoapp.utils.PrefConstants
import com.example.todoapp.utils.PrefConstants.SHARED_PREFERENCE_NAME
import com.example.todoapp.R

class LoginActivity : AppCompatActivity() {
    lateinit var et_fullname: EditText
    lateinit var et_username: EditText
    lateinit var btn_login: Button
    //lateinit var fullname: String
    //lateinit var username: String
    lateinit var sharedPreferences : SharedPreferences
    //stores in key value pair
    //key =mindorks value =work1
    lateinit  var editor : Editor//conssder as regiister where you keep making entre and when u want to rub it u rub iiit.


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindview()
        setupsharedpref()
    }

    private fun saveFullname(fullname: String) {
        editor = sharedPreferences.edit()
        editor.putString(PrefConstants.FULLNAME, fullname)
        editor.apply()
    }

    fun bindview() {
        et_fullname = findViewById(R.id.et_fullname)
        et_username = findViewById(R.id.et_username)
        btn_login = findViewById(R.id.btn_login)
        //object  eyword n case of new eyword whle usong lsteners
        val clickAction= object : View.OnClickListener{
            override fun onClick(v: View?) {
                val fullname = et_fullname.text.toString()
                val username = et_username.text.toString()
                if (fullname.isNotEmpty() && username.isNotEmpty()) {
                    saveLoginstatus()
                    saveFullname(fullname)
                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(AppConstants.FULLNAME, fullname)
                    startActivity(intent)
                    //finish()
                } else {

                    Toast.makeText(this@LoginActivity, "fullname and username cant be empty", Toast.LENGTH_LONG).show()
                }
            }

        }
        btn_login.setOnClickListener(clickAction)
    }

    fun setupsharedpref() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
        //prvatemode specifically tells prefernece that it can be acceseed b our own app not others.
    }

    fun saveLoginstatus() { //open regster
        editor = sharedPreferences.edit()
        //we write
        editor.putBoolean("IS_LOGGED_IN", true) //key ="is logged in"
        //we save
        editor.apply()
    }
}