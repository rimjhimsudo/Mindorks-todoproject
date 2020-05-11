package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                setupdialogbox();
            }
        });

    }

    private void setupdialogbox(){
        View view= LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.addnoteslayout,null);
        EditText editTextTtle=view.findViewById(R.id.et_title);
        EditText editTextdesc=view.findViewById(R.id.et_desc);
        Button btn_submt=view.findViewById(R.id.btn_submtnote);
        AlertDialog dialog=new AlertDialog.Builder(this).setView(view).setCancelable(false).create();
        dialog.show();

    }
}
