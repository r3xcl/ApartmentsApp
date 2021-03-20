package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.example.myapplication.fragments.fragments1.Fragment_Pay1;
import com.example.myapplication.fragments.fragments1.Fragment_Repair1;
import com.example.myapplication.fragments.fragments2.Fragment_Pay2;
import com.example.myapplication.fragments.fragments2.Fragment_Repair2;
import com.example.myapplication.fragments.fragments3.Fragment_Pay3;
import com.example.myapplication.fragments.fragments3.Fragment_Repair3;
import com.example.myapplication.fragments.fragments4.Fragment_Pay4;
import com.example.myapplication.fragments.fragments4.Fragment_Repair4;

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
                      getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair1()).commit();

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
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair2()).commit();
                    fragment2.setVisibility(View.INVISIBLE);
                    fragment1.setVisibility(View.VISIBLE);
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay2()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });
        }

        if (action.equals("archive3")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay3()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair3()).commit();
                    fragment2.setVisibility(View.INVISIBLE);
                    fragment1.setVisibility(View.VISIBLE);
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay3()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });
        }

        if (action.equals("archive4")) {

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay4()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair4()).commit();
                    fragment2.setVisibility(View.INVISIBLE);
                    fragment1.setVisibility(View.VISIBLE);
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay4()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });
        }
    }


    @Override
    public void onClick(View v) {


    }

    //ПРИ НАЖАТИИ НА ЭКРАН СКРЫВАЕМ КЛАВИАТУРУ --->

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN)
            hideKeyboard();
        return super.dispatchTouchEvent(ev);
    }



    // <---


}