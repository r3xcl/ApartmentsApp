package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class Activity_edit_apartment extends AppCompatActivity implements View.OnClickListener{

    TextView apartment_room,apartment_floor,apartment_purchase_date,apartment_address,apartment_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_apartment);

        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        apartment_room = (TextView)findViewById(R.id.apartment_room);
        apartment_floor = (TextView)findViewById(R.id.apartment_floor);
        apartment_purchase_date = (TextView)findViewById(R.id.apartment_purchase_date);
        apartment_address = (TextView)findViewById(R.id.apartment_address);


    }

    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("apartment_edit1")) {

            intent.putExtra("apartment_room1", apartment_room.getText().toString());
            intent.putExtra("apartment_floor1", apartment_floor.getText().toString());
            intent.putExtra("apartment_purchase_date1", apartment_purchase_date.getText().toString());
            intent.putExtra("apartment_address1", apartment_address.getText().toString());



            intent.putExtra("1111", 1111);
            setResult(RESULT_OK, intent);

            finish();

        }


        if (action.equals("apartment_edit2")) {


            intent.putExtra("apartment_room2", apartment_room.getText().toString());
            intent.putExtra("apartment_floor2", apartment_floor.getText().toString());
            intent.putExtra("apartment_purchase_date2", apartment_purchase_date.getText().toString());
            intent.putExtra("apartment_pay2", apartment_pay.getText().toString());

            intent.putExtra("2222", 2222);
            setResult(RESULT_OK, intent);

            finish();



        }

        if (action.equals("apartment_edit3")) {

            intent.putExtra("apartment_room3", apartment_room.getText().toString());
            intent.putExtra("apartment_floor3", apartment_floor.getText().toString());
            intent.putExtra("apartment_purchase_date3", apartment_purchase_date.getText().toString());
            intent.putExtra("apartment_address3", apartment_address.getText().toString());


            intent.putExtra("3333", 3333);
            setResult(RESULT_OK, intent);



            finish();

        }

        if (action.equals("apartment_edit4")) {

            intent.putExtra("apartment_room4", apartment_room.getText().toString());
            intent.putExtra("apartment_floor4", apartment_floor.getText().toString());
            intent.putExtra("apartment_purchase_date4", apartment_purchase_date.getText().toString());
            intent.putExtra("apartment_address4", apartment_address.getText().toString());


            intent.putExtra("4444", 4444);
            setResult(RESULT_OK, intent);

            finish();



        }

        if (action.equals("apartment_edit5")) {

            intent.putExtra("apartment_room5", apartment_room.getText().toString());
            intent.putExtra("apartment_floor5", apartment_floor.getText().toString());
            intent.putExtra("apartment_purchase_date5", apartment_purchase_date.getText().toString());
            intent.putExtra("apartment_address5", apartment_address.getText().toString());


            intent.putExtra("5555", 5555);
            setResult(RESULT_OK, intent);

            finish();



        }
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