package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import db.ApartmentsClass;

public class ActivityAddNewApartment extends AppCompatActivity implements View.OnClickListener {

    Button but_home;
    public EditText edit_address, edit_info,edit_rooms,edit_floor,edit_dateown,edit_name;


   private DatabaseReference myDataBase ;
   private FirebaseDatabase rootNode;
   private String New_Apartment = "New_Apartment";
   ApartmentsClass apartmentsClass;



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
        edit_dateown.addTextChangedListener(new TextWatcher() {

            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {

                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }

                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{

                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>9999)?9999:year;
                        cal.set(Calendar.YEAR, year);


                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    edit_dateown.setText(current);
                    edit_dateown.setSelection(sel < current.length() ? sel : current.length());



                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edit_name = (EditText) findViewById(R.id.edit_name);



        myDataBase = FirebaseDatabase.getInstance().getReference(New_Apartment);




    }




    @Override
    public void onClick(View v) {

            Intent intent = getIntent();
            String action = intent.getAction();

            rootNode = FirebaseDatabase.getInstance();
            myDataBase = rootNode.getReference("New_Apartment");

            if (action.equals("1")) {



                String id = "newApartment1";
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();
                String name = edit_name.getText().toString();



                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown,name);


                myDataBase.child(id).setValue(newApartment);



                if(edit_address.getText().toString().length()!=0) {

                    intent.putExtra("rooms1", edit_rooms.getText().toString());
                    intent.putExtra("floor1", edit_floor.getText().toString());
                    intent.putExtra("dateown1", edit_dateown.getText().toString());
                    intent.putExtra("name1", edit_name.getText().toString());

                    intent.putExtra("1", 1);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    edit_address.setError("Введіть дані!");
                    edit_address.requestFocus();
                }


            }


        else if (action.equals("2")) {

                String id = "newApartment2";
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();
                String name = edit_name.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown,name);

                myDataBase.child(id).setValue(newApartment);

                if(edit_address.getText().toString().length()!=0) {

                    intent.putExtra("rooms2", edit_rooms.getText().toString());
                    intent.putExtra("floor2", edit_floor.getText().toString());
                    intent.putExtra("dateown2", edit_dateown.getText().toString());
                    intent.putExtra("name2", edit_name.getText().toString());

                    intent.putExtra("2", 2);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    edit_address.setError("Введіть дані!");
                    edit_address.requestFocus();
                }

           }


         else if (action.equals("3")) {

                String id = "newApartment3";
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();
                String name = edit_name.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown,name);

                myDataBase.child(id).setValue(newApartment);


                if(edit_address.getText().toString().length()!=0) {

                    intent.putExtra("rooms3", edit_rooms.getText().toString());
                    intent.putExtra("floor3", edit_floor.getText().toString());
                    intent.putExtra("dateown3", edit_dateown.getText().toString());
                    intent.putExtra("name3", edit_name.getText().toString());

                    intent.putExtra("3", 3);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    edit_address.setError("Введіть дані!");
                    edit_address.requestFocus();
                }

                }

         else if (action.equals("4")) {

                String id = "newApartment4";
                String address = edit_address.getText().toString();
                String rooms = edit_rooms.getText().toString();
                String floor = edit_floor.getText().toString();
                String dateown = edit_dateown.getText().toString();
                String name = edit_name.getText().toString();


                ApartmentsClass newApartment = new ApartmentsClass(id,address,rooms,floor,dateown,name);

                myDataBase.child(id).setValue(newApartment);


                if(edit_address.getText().toString().length()!=0) {

                    intent.putExtra("rooms4", edit_rooms.getText().toString());
                    intent.putExtra("floor4", edit_floor.getText().toString());
                    intent.putExtra("dateown4", edit_dateown.getText().toString());
                    intent.putExtra("name4", edit_name.getText().toString());

                    intent.putExtra("4", 4);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    edit_address.setError("Введіть дані!");
                    edit_address.requestFocus();
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