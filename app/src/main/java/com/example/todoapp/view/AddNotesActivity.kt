package com.example.todoapp.view

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.example.todoapp.R

class AddNotesActivity : AppCompatActivity() {
    lateinit var  et_title : EditText
    lateinit var et_desc : EditText
    lateinit var imagevw_addnotes : ImageView
    lateinit var btn_submitnote : Button
    val REQUEST_CODE_GALLERY=2
    val REQUEST_CODE_CAMERA=1
    var picture_path=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindview()
        clicklisteners()

    }

    private fun clicklisteners() {
        imagevw_addnotes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setupdialogbox()

            }

        })
    }

    private fun setupdialogbox() {
        val  view =LayoutInflater.from(this).inflate(R.layout.dailogselector,null)
        val tv_camera:TextView=view.findViewById(R.id.tv_camera)
        val tv_gallery : TextView=view.findViewById(R.id.tv_gallery)
        val dialog =AlertDialog
                .Builder(this)
                .setView(view)
                .setCancelable(true)
                .create()
        tv_camera.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //val intent
            }

        })
        tv_gallery.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,REQUEST_CODE_GALLERY)
            }

        })
        dialog.show()
    }

    private fun bindview() {
        et_title=findViewById(R.id.et_title)
        et_desc=findViewById(R.id.et_desc)
        imagevw_addnotes=findViewById(R.id.imagevw_addnotes)
        btn_submitnote=findViewById(R.id.btn_submtnote)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK){
            when (requestCode) {
                REQUEST_CODE_GALLERY -> {
                    val selectedimg= data?.data

                    val filepath= arrayOf(MediaStore.Images.Media.DATA)
                    val c= selectedimg?.let { contentResolver.query(it,filepath,null,null,null) }
                    if (c != null) {
                        c.moveToFirst()
                    }
                    val columnIndex= c?.getColumnIndex(filepath[0])
                    if (c != null) {
                        picture_path= columnIndex?.let { c.getString(it) }.toString()
                    }
                    if (c != null) {
                        c.close()
                    }
                    Log.d("PATH",""+picture_path)
                    Glide.with(this).load(picture_path).into(imagevw_addnotes)

                }
                REQUEST_CODE_CAMERA -> {

                }
            }
        }
    }
}
