package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupsharedpref();
        checkloginstatus();
    }

    private void setupsharedpref() {
        sharedPreferences=getSharedPreferences(PrefConstants.SHARED_PREFERENCE_NAME,MODE_PRIVATE);

    }

    public void checkloginstatus(){
        //if user not logged then take to login screen
        // //else take to notes activity screen
        //check some data locally
        ///term - Share preferences

        boolean isLoggedin=sharedPreferences.getBoolean(PrefConstants.IS_LOGGED_IN,false);

        if (isLoggedin){
            //note activiity
            Intent intent=new Intent(SplashActivity.this, MyNotesActivity.class);
            startActivity(intent);
        }
        else{
            //logn actvity
            Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
            startActivity(intent);

        }
        finish();


    }

}
