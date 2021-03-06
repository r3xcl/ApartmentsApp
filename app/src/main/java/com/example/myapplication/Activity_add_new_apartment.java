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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import db.ApartmentsClass;

public class Activity_add_new_apartment extends AppCompatActivity implements View.OnClickListener {

    Button but_home;
    public EditText edit_address, edit_info,edit_rooms,edit_floor,edit_dateown;


   private DatabaseReference myDataBase ;
   private String New_Apartment = "New_Apartment";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ
        setContentView(R.layout.activity_add_new_apartment);




        but_home = (Button) findViewById(R.id.but_home);
        but_home.setOnClickListener(this);


        edit_address = (EditText) findViewById(R.id.edit_address);

        edit_info = (EditText) findViewById(R.id.edit_info);

        edit_rooms = (EditText) findViewById(R.id.edit_rooms);

        edit_floor = (EditText) findViewById(R.id.edit_floor);

        edit_dateown = (EditText) findViewById(R.id.edit_dateown);



        myDataBase = FirebaseDatabase.getInstance().getReference(New_Apartment);




    }




    @Override
    public void onClick(View v) {

            Intent intent = getIntent();
            String action = intent.getAction();

            if (action.equals("1")) {



                String id = myDataBase.getKey();
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();



                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown);

                myDataBase.push().setValue(newApartment);



                if(edit_address.getText().toString().length()!=0) {
                    intent.putExtra("address1", edit_address.getText().toString());
                    intent.putExtra("rooms1", edit_rooms.getText().toString());
                    intent.putExtra("floor1", edit_floor.getText().toString());
                    intent.putExtra("dateown1", edit_dateown.getText().toString());

                    intent.putExtra("1", 1);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(Activity_add_new_apartment.this,"Введіть адресу!",Toast.LENGTH_LONG).show();
                }


            }


        else if (action.equals("2")) {

                String id = myDataBase.getKey();
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown);

                myDataBase.push().setValue(newApartment);

                if(edit_address.getText().toString().length()!=0) {
                    intent.putExtra("address2", edit_address.getText().toString());
                    intent.putExtra("rooms2", edit_rooms.getText().toString());
                    intent.putExtra("floor2", edit_floor.getText().toString());
                    intent.putExtra("dateown2", edit_dateown.getText().toString());

                    intent.putExtra("2", 2);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(Activity_add_new_apartment.this,"Введіть адресу!",Toast.LENGTH_LONG).show();
                }

           }


         else if (action.equals("3")) {

                String id = myDataBase.getKey();
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown);

                myDataBase.push().setValue(newApartment);


                if(edit_address.getText().toString().length()!=0) {
                    intent.putExtra("address3", edit_address.getText().toString());
                    intent.putExtra("rooms3", edit_rooms.getText().toString());
                    intent.putExtra("floor3", edit_floor.getText().toString());
                    intent.putExtra("dateown3", edit_dateown.getText().toString());

                    intent.putExtra("3", 3);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(Activity_add_new_apartment.this,"Введіть адресу!",Toast.LENGTH_LONG).show();
                }

                }

         else if (action.equals("4")) {

                String id = myDataBase.getKey();
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown);

                myDataBase.push().setValue(newApartment);


                if(edit_address.getText().toString().length()!=0) {
                    intent.putExtra("address4", edit_address.getText().toString());
                    intent.putExtra("rooms4", edit_rooms.getText().toString());
                    intent.putExtra("floor4", edit_floor.getText().toString());
                    intent.putExtra("dateown4", edit_dateown.getText().toString());

                    intent.putExtra("4", 4);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(Activity_add_new_apartment.this,"Введіть адресу!",Toast.LENGTH_LONG).show();
                }
                    }



         else if (action.equals("5")) {

                String id = myDataBase.getKey();
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown);

                myDataBase.push().setValue(newApartment);


                if(edit_address.getText().toString().length()!=0) {
                    intent.putExtra("address5", edit_address.getText().toString());
                    intent.putExtra("rooms5", edit_rooms.getText().toString());
                    intent.putExtra("floor5", edit_floor.getText().toString());
                    intent.putExtra("dateown5", edit_dateown.getText().toString());

                    intent.putExtra("5", 5);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(Activity_add_new_apartment.this,"Введіть адресу!",Toast.LENGTH_LONG).show();
                }
                    }



        }


    public void  onClickSave(View view){

    }


    @Override
    protected void onResume() {

        super.onResume();



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

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