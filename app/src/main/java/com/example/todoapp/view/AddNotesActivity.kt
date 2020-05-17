package com.example.todoapp.view
/*https://stackoverflow.com/questions/56598480/couldnt-find-meta-data-for-provider-with-authority*/
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.todoapp.BuildConfig
import com.example.todoapp.R
import com.example.todoapp.utils.AppConstants
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddNotesActivity : AppCompatActivity() {
    lateinit var  et_title : EditText
    lateinit var et_desc : EditText
    lateinit var imagevw_addnotes : ImageView
    lateinit var btn_submitnote : Button
    val REQUEST_CODE_GALLERY=2
    val REQUEST_CODE_CAMERA=1
    val MY_PERMSSN_CODE=101
    var picture_path=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
        bindview()
        clicklisteners()

    }

    private fun clicklisteners() {
        btn_submitnote.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val intent=Intent()
                intent.putExtra(AppConstants.TITLE,et_title.text.toString())
                intent.putExtra(AppConstants.DESC,et_desc.text.toString())
                intent.putExtra(AppConstants.IMGPATH,picture_path)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }

        })
        imagevw_addnotes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (checAndReqPermssn()){
                    setupdialogbox()
                }


            }

        })
    }

    private fun checAndReqPermssn(): Boolean {
        val cameraPermssn = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        val storagePermssn = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
        val arrayLstPerNeeded = ArrayList<String>()
        if (cameraPermssn != PackageManager.PERMISSION_GRANTED) {
            arrayLstPerNeeded.add(android.Manifest.permission.CAMERA)
        }
        if (storagePermssn != PackageManager.PERMISSION_GRANTED) {
            arrayLstPerNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (arrayLstPerNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, arrayLstPerNeeded.toTypedArray<String>(), MY_PERMSSN_CODE)
            return false

        }
        return true
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            MY_PERMSSN_CODE->{
                if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    setupdialogbox()
                }
            }
        }

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
                val takepictureintent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                var photofile: File?=null
                photofile=createImg()
                if(photofile!=null){
                    val photoURI=FileProvider.getUriForFile(this@AddNotesActivity,BuildConfig.APPLICATION_ID+".provider",photofile)
                    picture_path=photofile.absolutePath
                    Log.d("TAGPATHPIC",""+picture_path)
                    takepictureintent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
                    startActivityForResult(takepictureintent,REQUEST_CODE_CAMERA)
                }
                dialog.hide()
            }

        })
        tv_gallery.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val intent=Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,REQUEST_CODE_GALLERY)
                dialog.hide()
            }

        })
        dialog.show()
    }

    private fun createImg(): File? {
        val timeStamp=SimpleDateFormat("yyyyMMDDHHss").format(Date())
        val filename="JPEG_"+timeStamp+"_"
        val storeDIR=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(filename,".jpg",storeDIR)
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
                    //val file = File(picture_path)
                    Glide.with(this).load(picture_path).centerCrop().override(150,150).error(R.drawable.ic_add_black_24dp).into(imagevw_addnotes)

                }
                REQUEST_CODE_CAMERA -> {
                        //wwerite
                    Glide.with(this).load(picture_path).into(imagevw_addnotes)
                }
            }
        }
    }
}
