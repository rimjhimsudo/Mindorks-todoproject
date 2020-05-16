package com.example.todoapp

import android.app.Application
import com.example.todoapp.db.NotesDatabase

class  NotesApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }

    fun getNotesDb():NotesDatabase{
        return NotesDatabase.getInstance(this)
    }
}