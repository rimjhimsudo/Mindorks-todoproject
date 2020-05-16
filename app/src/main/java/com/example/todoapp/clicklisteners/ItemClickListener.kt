package com.example.todoapp.clicklisteners

import android.service.autofill.OnClickAction
import com.example.todoapp.db.Notes

interface ItemClickListener {

    fun OnClick(notes : Notes)
    fun onUpdate(notes: Notes)
}