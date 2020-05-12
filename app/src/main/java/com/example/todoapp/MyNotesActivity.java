package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;
    FloatingActionButton btn_float;
    TextView viewtitle , viewdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);
        viewtitle=findViewById(R.id.tv_vtitle);
        viewdesc=findViewById(R.id.tv_cdesc);
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
        final String ttle=editTextTtle.getText().toString();
        EditText editTextdesc=view.findViewById(R.id.et_desc);
        final String desc=editTextdesc.getText().toString();
        Button btn_submt=view.findViewById(R.id.btn_submtnote);
        final AlertDialog dialog=new AlertDialog.Builder(this).setView(view).setCancelable(false).create();
        dialog.show();
        btn_submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewtitle.setText(ttle);
                viewdesc.setText(desc);
                dialog.hide();
            }
        });



    }
}
