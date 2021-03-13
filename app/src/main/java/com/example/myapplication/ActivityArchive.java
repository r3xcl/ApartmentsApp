package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.fragments.fragments1.Fragment_Pay1;
import com.example.myapplication.fragments.fragments1.Fragment_Remont1;
import com.example.myapplication.fragments.fragments2.Fragment_Pay2;
import com.example.myapplication.fragments.fragments2.Fragment_Remont2;
import com.example.myapplication.fragments.fragments3.Fragment_Pay3;
import com.example.myapplication.fragments.fragments3.Fragment_Remont3;
import com.example.myapplication.fragments.fragments4.Fragment_Pay4;
import com.example.myapplication.fragments.fragments4.Fragment_Remont4;

public class ActivityArchive extends AppCompatActivity implements View.OnClickListener{

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

        fragment1.setVisibility(View.INVISIBLE);


        if (action.equals("archive1")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay1()).commit();

              fragment2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Remont1()).commit();

                      fragment2.setVisibility(View.INVISIBLE);
                      fragment1.setVisibility(View.VISIBLE);
                  }
              });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay1()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });
        }

        if (action.equals("archive2")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay2()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Remont2()).commit();
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay2()).commit();
                }
            });
        }

        if (action.equals("archive3")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay3()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Remont3()).commit();
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay3()).commit();
                }
            });
        }

        if (action.equals("archive4")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay4()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Remont4()).commit();
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay4()).commit();
                }
            });
        }
    }


    @Override
    public void onClick(View v) {


    }


}