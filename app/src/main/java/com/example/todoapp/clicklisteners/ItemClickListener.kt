package com.example.todoapp.clicklisteners

import android.service.autofill.OnClickAction
import com.example.todoapp.model.Notes

interface ItemClickListener {

    fun OnClick(notes : Notes)
}