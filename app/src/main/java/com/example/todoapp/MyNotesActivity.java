package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;
    FloatingActionButton btn_float;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        btn_float=findViewById(R.id.float_btn);
        Intent intent=getIntent();
        fullname= intent.getStringExtra("fullname");
        //acton bar
        getSupportActionBar().setTitle(fullname);
        //floatng button
        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
