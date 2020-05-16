package com.example.todoapp.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.utils.AppConstants
import com.example.todoapp.utils.AppConstants.DESC
import com.example.todoapp.R

class DetailActivity : AppCompatActivity() {
    lateinit  var tv_dttle: TextView
    lateinit var tv_ddesc: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        bindview()
        setupintent()
    }

    private fun setupintent() {
        val intent = intent //equals to getntent
        val ttle = intent.getStringExtra(AppConstants.TITLE)
        val desc = intent.getStringExtra(DESC)
        tv_dttle.text = ttle
        tv_ddesc.text = desc
    }

    private fun bindview() {
        tv_dttle = findViewById(R.id.tv_dttle)
        tv_ddesc = findViewById(R.id.tv_ddesc)
    }
}