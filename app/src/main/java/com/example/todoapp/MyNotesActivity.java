package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todoapp.adapter.NotesAdapter;
import com.example.todoapp.clicklisteners.ItemClickLstener;
import com.example.todoapp.model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.todoapp.AppConstants.DESC;
import static com.example.todoapp.AppConstants.TITLE;

public class MyNotesActivity extends AppCompatActivity {
    String fullname;
    FloatingActionButton btn_float;
    TextView viewtitle , viewdesc;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerViewnotes;
    ArrayList<Notes> notesArrayList=new ArrayList<>();
    private NotesAdapter notesAdapter;
    ItemClickLstener itemClickLstener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notes);

        //viewtitle=findViewById(R.id.tv_vtitle);
        //viewdesc=findViewById(R.id.tv_cdesc);
        btn_float=findViewById(R.id.float_btn);
        recyclerViewnotes=findViewById(R.id.recyclervw);
        setupsharepref();
        getIntentdata();
        //acton bar
        getSupportActionBar().setTitle(fullname);
        //floatng button
        btn_float.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupdialogbox();
            }
        });

       // setRecylervw();

    }

    private void setupsharepref() {
        sharedPreferences=getSharedPreferences(PrefConstants.SHARED_PREFERENCE_NAME,MODE_PRIVATE);
    }

    private void setupdialogbox(){
        View view= LayoutInflater.from(MyNotesActivity.this).inflate(R.layout.addnoteslayout,null);
        final EditText editTextTtle=view.findViewById(R.id.et_title);

        final EditText editTextdesc=view.findViewById(R.id.et_desc);

        Button btn_submt=view.findViewById(R.id.btn_submtnote);
        final AlertDialog dialog=new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
        dialog.show();
        btn_submt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bahar when we r gettng values usng getText, iit is gettng null valye because befor btn.submt t s settng null values

                String title=editTextTtle.getText().toString();
                String descptn=editTextdesc.getText().toString();
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(descptn)){
                    Notes notes=new Notes();
                    notes.setTitle(title);
                    notes.setDesc(descptn);
                    notesArrayList.add(notes);
                }
                else
                    Toast.makeText(getApplicationContext(), "Fields ant be empty",Toast.LENGTH_SHORT).show();

                setRecylervw();
                Log.d("TAG",""+notesArrayList.size());
                //viewtitle.setText(ttle);
                //viewdesc.setText(desc);
                //notesAdapter.setListnotes(notesArrayList);
                //notesAdapter.notifyDataSetChanged();
                Log.d("TAG", "valuesfromdalog "+title+descptn);
                dialog.hide();

            }
        });



    }

    private void setRecylervw() {
        //nterface
        ItemClickLstener itemClickLstener = new ItemClickLstener() {
            @Override
            public void onClick(Notes notes) {
                Log.d("TAGCLC","clc workkkkkkkkkked");
                Log.d("TAGDATA","ttlte"+notes.getTitle());
                Intent intent=new Intent(MyNotesActivity.this, DetailActivity.class);
                intent.putExtra(TITLE,notes.getTitle());
                intent.putExtra(DESC,notes.getDesc());
                startActivity(intent);

            }
        };
        notesAdapter= new NotesAdapter(notesArrayList,itemClickLstener);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MyNotesActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewnotes.setLayoutManager(linearLayoutManager);
        recyclerViewnotes.setAdapter(notesAdapter);
    }

    public  void getIntentdata(){
        Intent intent=getIntent();
        fullname= intent.getStringExtra(AppConstants.FULLNAME);
        if (TextUtils.isEmpty(fullname)){
            fullname=sharedPreferences.getString(PrefConstants.FULLNAME,"");

        }
    }



}
