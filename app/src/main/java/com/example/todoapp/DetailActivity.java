package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tv_dttle, tv_ddesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindview();
        setupintent();
    }

    private void setupintent() {
        Intent intent=getIntent();
        String ttle= intent.getStringExtra(AppConstants.TITLE);
        String desc= intent.getStringExtra(AppConstants.DESC);
        tv_dttle.setText(ttle);
        tv_ddesc.setText(desc);
    }

    private void bindview() {
        tv_dttle=findViewById(R.id.tv_vttle);
        tv_ddesc=findViewById(R.id.tv_vdesc);

    }
}
