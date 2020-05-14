package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText et_fullname, et_username;
    Button btn_login;
    String fullname, username;
    SharedPreferences sharedPreferences; //stores in key value pair
    //key =mindorks value =work1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindview();
        setupsharedpref();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 fullname=et_fullname.getText().toString();
                 username= et_username.getText().toString();
                if (!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(username)){
                    Intent intent=new Intent(LoginActivity.this,MyNotesActivity.class);
                    intent.putExtra(AppConstants.FULLNAME,fullname);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "fullname and username cant be empty",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    public  void bindview(){
        et_fullname=findViewById(R.id.et_fullname);
        et_username=findViewById(R.id.et_username);
        btn_login=findViewById(R.id.btn_login);
    }
    public  void setupsharedpref(){
        sharedPreferences =getSharedPreferences()
    }
}
