package com.example.myapart.files;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapart.files.fragments.fragmentFiles1;
import com.example.myapart.files.fragments.fragmentFiles2;
import com.example.myapart.files.fragments.fragmentFiles3;
import com.example.myapart.files.fragments.fragmentFiles4;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Files extends AppCompatActivity implements View.OnClickListener{

    Fragment fragment = null;

    FloatingActionButton fb;

    ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        Intent intent = getIntent();
        String action = intent.getAction();

        getSupportActionBar().hide();

      back = findViewById(R.id.back);

      back.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });

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