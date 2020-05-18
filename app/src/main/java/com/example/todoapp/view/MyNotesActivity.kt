package com.example.todoapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.Constraints
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.todoapp.NotesApp
import com.example.todoapp.utils.AppConstants
import com.example.todoapp.utils.AppConstants.DESC
import com.example.todoapp.utils.AppConstants.TITLE
import com.example.todoapp.utils.PrefConstants
import com.example.todoapp.utils.PrefConstants.SHARED_PREFERENCE_NAME
import com.example.todoapp.R
import com.example.todoapp.adapter.NotesAdapter
import com.example.todoapp.clicklisteners.ItemClickListener
import com.example.todoapp.db.Notes
import com.example.todoapp.workmanager.MyWorker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import java.util.concurrent.TimeUnit

class MyNotesActivity : AppCompatActivity() {
    val ADD_NOTES_CODE=100
    var fullname: String? = null

    lateinit var btn_float: FloatingActionButton
    lateinit var viewtitle: TextView
    lateinit var viewdesc: TextView
    lateinit var sharedPreferences: SharedPreferences
    lateinit var recyclerViewnotes: RecyclerView
    var notesArrayList = ArrayList<Notes>()
    private  lateinit var notesAdapter: NotesAdapter
    lateinit var itemClickLstener: ItemClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_notes)
        //viewtitle=findViewById(R.id.tv_vtitle);
        //viewdesc=findViewById(R.id.tv_cdesc);

        setupsharepref()
        bindview()
        getintentdata()
        setRecylervw()
        getDatafromDb()
        setupWorkerManager()
        //acton bar
        supportActionBar?.title = fullname //can take null value tooo now 
        //floatng button
        btn_float.setOnClickListener(object:View.OnClickListener {
            override fun onClick(v: View?) {
                //setupdialogbox()
                val intent = Intent(this@MyNotesActivity,AddNotesActivity::class.java)
                startActivityForResult(intent,ADD_NOTES_CODE)

            }

        })
        // setRecylervw();
    }

    private fun setupWorkerManager() {
        val constraint=Constraints.Builder()
                .build()
        val request=PeriodicWorkRequest.Builder(MyWorker::class.java,15,TimeUnit.MINUTES)
                                        .setConstraints(constraint)
                                         .build()
        WorkManager.getInstance().enqueue(request)
        //also we can queue trequest liiiiiikkke

                //.setRequiresCharging(true)
                //.setRequiresBatteryNotLow(true)
       // WorkManager.getInstance().beginWith(request).then(djjdd).enqueue
    }

    private fun getDatafromDb() {
        val notesApp=applicationContext as NotesApp
        val notesDao=notesApp.getNotesDb().notesDao()
        val  listofNotes=notesDao.getAll()
        Log.d("TAGNOTES","h"+listofNotes.size)
        notesArrayList.addAll(listofNotes)
    }

    private fun getintentdata() { //errorhereray

        val intent = intent
        // fullname = intent.getStringExtra(AppConstants.FULLNAME)
        if (intent.hasExtra(AppConstants.FULLNAME)){
            fullname = intent.getStringExtra(AppConstants.FULLNAME)
        }

        //error wthout toString
        if (fullname.isNullOrEmpty()) {
            fullname = sharedPreferences.getString(PrefConstants.FULLNAME, "")
        }

    }

    private fun bindview() {
        btn_float = findViewById(R.id.float_btn)
        recyclerViewnotes = findViewById(R.id.recyclervw)
    }

    private fun setupsharepref() {
        sharedPreferences = getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun setupdialogbox() {
        val view = LayoutInflater.from(this@MyNotesActivity).inflate(R.layout.addnoteslayout, null)
        val editTextTtle = view.findViewById<EditText>(R.id.et_title)
        val editTextdesc = view.findViewById<EditText>(R.id.et_desc)
        val btn_submt = view.findViewById<Button>(R.id.btn_submtnote)
        val dialog = AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create()
        dialog.show()
        btn_submt.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                //bahar when we r gettng values usng getText, iit is gettng null valye because befor btn.submt t s settng null values in java
                val title = editTextTtle.text.toString()
                val desc = editTextdesc.text.toString()
                if (title.isNotEmpty() && desc.isNotEmpty()) {
                    val notes = Notes(title=title, desc=desc) //ths alspoo sets values  both var
                    notesArrayList.add(notes)
                    addNotesToDb(notes)
                } else Toast.makeText(applicationContext, "Fields ant be empty", Toast.LENGTH_SHORT).show()
                Log.d("TAG", "" + notesArrayList.size)
                Log.d("TAG", "valuesfromdalog $title$desc")
                dialog.hide()
            }
        })
    }

    private fun addNotesToDb(notes: Notes) {
        val notesApp= applicationContext as NotesApp
        val notesDao=notesApp.getNotesDb().notesDao()
        notesDao.insert(notes)
        Log.d("DB", notesDao.getAll().size.toString())
    }

    private fun setRecylervw() { //nterface
        val itemClickLstener: ItemClickListener = object : ItemClickListener {
            override fun OnClick(notes: Notes) {
                Log.d("TAGCLC", "clc workkkkkkkkkked")
                Log.d("TAGDATA", "ttlte" + notes.title)
                val intent = Intent(this@MyNotesActivity, DetailActivity::class.java)
                intent.putExtra(TITLE, notes.title)
                intent.putExtra(DESC, notes.desc)
                startActivity(intent)
            }

            override fun onUpdate(notes: Notes) {

                Log.d("TAGonUPDATE",""+notes.isTaskCompleted) //not wrng gettng opposte value
                val notesApp= applicationContext as NotesApp
                val notesDao=notesApp.getNotesDb().notesDao()
                notesDao.updateNotes(notes)
            }

        }
        notesAdapter = NotesAdapter(notesArrayList,itemClickLstener)
        val linearLayoutManager = LinearLayoutManager(this@MyNotesActivity)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerViewnotes.layoutManager = linearLayoutManager
        recyclerViewnotes.adapter = notesAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==ADD_NOTES_CODE && requestCode!= Activity.RESULT_CANCELED && data!=null){
                val title =data?.getStringExtra(AppConstants.TITLE)
                val desc =data?.getStringExtra(AppConstants.DESC)
                val imagpath=data?.getStringExtra(AppConstants.IMGPATH)
                val notes=Notes(title=title!!,desc=desc!!,imagePath = imagpath!!)
                addNotesToDb(notes)
                notesArrayList.add(notes)
                recyclerViewnotes.adapter?.notifyItemChanged(notesArrayList.size-1)

                //Log.d("DB","")
            }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       val inflater=menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId==R.id.Blogs){
            Log.d("MENU","click successful")
            val intent =Intent(this@MyNotesActivity,BlogActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}