package com.example.todoapp.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.todoapp.NotesApp

class  MyWorker(val context : Context, val workerparam: WorkerParameters) : Worker(context,workerparam){
    override fun doWork(): Result {
        //iiiikkkkkk ======
        val notesApp =applicationContext as NotesApp
        val notesDao=notesApp.getNotesDb().notesDao()
        notesDao.deleteNotes(true)
        return Result.success()
    }

}