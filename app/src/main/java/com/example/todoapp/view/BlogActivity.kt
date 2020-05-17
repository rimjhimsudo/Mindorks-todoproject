package com.example.todoapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import kotlinx.android.synthetic.main.activity_blog.*

class BlogActivity : AppCompatActivity() {
    lateinit var recyclervwblogs : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog)
        bindview()

    }

    private fun bindview() {
        //recylervwblog=findViewById(R.id.recylervwblog)
        recyclervwblogs=findViewById(R.id.recylervwblog)
    }
}
