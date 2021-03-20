package com.example.myapplication.files;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.files.fragments.fragmentFiles1;
import com.example.myapplication.files.fragments.fragmentFiles2;
import com.example.myapplication.files.fragments.fragmentFiles3;
import com.example.myapplication.files.fragments.fragmentFiles4;
import com.example.myapplication.fragments.fragments1.Fragment_Pay1;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class Files extends AppCompatActivity implements View.OnClickListener{

    Fragment fragment = null;

    FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        Intent intent = getIntent();
        String action = intent.getAction();

        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ



        if (action.equals("files1")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.files,new fragmentFiles1()).commit();

            fb=(FloatingActionButton)findViewById(R.id.floatingActionButton);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent("filesUP1");
                    startActivity(intent1);
                }
            });

        }

        if (action.equals("files2")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.files,new fragmentFiles2()).commit();

            fb=(FloatingActionButton)findViewById(R.id.floatingActionButton);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent("filesUP2");
                    startActivity(intent1);
                }
            });

        }

        if (action.equals("files3")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.files,new fragmentFiles3()).commit();

            fb=(FloatingActionButton)findViewById(R.id.floatingActionButton);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent("filesUP3");
                    startActivity(intent1);
                }
            });

        }

        if (action.equals("files4")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.files,new fragmentFiles4()).commit();

            fb=(FloatingActionButton)findViewById(R.id.floatingActionButton);
            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent("filesUP4");
                    startActivity(intent1);
                }
            });

        }





    }



    @Override
    public void onClick(View v) {


    }


}