package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Activity_Archive extends AppCompatActivity implements View.OnClickListener{

    ImageButton fragment2,fragment1;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__archive);

        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        fragment2 = (ImageButton)findViewById(R.id.fragment2);
        fragment2.setOnClickListener(this);

        fragment1 = (ImageButton)findViewById(R.id.fragment1);
        fragment1.setOnClickListener(this);

        Intent intent = getIntent();
        String action = intent.getAction();


        if (action.equals("archive1")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay()).commit();

              fragment2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Remont()).commit();
                  }
              });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay()).commit();
                }
            });
        }
    }


    @Override
    public void onClick(View v) {


    }


}