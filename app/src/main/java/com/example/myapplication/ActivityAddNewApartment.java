package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import db.ApartmentsClass;

public class ActivityAddNewApartment extends AppCompatActivity implements View.OnClickListener {

    Button but_home;
    public EditText edit_address, edit_info,edit_rooms,edit_floor,edit_dateown,edit_name,edit_size;
    public Spinner edit_districts,edit_cities;

    String[] districts = {"Оберіть район...","Голосіївський район","Дарницький район","Деснянський район","Дніпровський район"
            ,"Оболонський район","Печерський район","Подільський район","Святошинський район"
            , "Солом'янський район","Шевченківський район"};

    String[] cities = {"Оберіть місто...","Київ"};


   private DatabaseReference myDataBase ;
   private FirebaseDatabase rootNode;
   SharedPreferences sharedPreferences;
   private String New_Apartment = "New_Apartment";
   ApartmentsClass apartmentsClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_new_apartment);

        edit_districts = (Spinner) findViewById(R.id.edit_districts);
        edit_districts.setEnabled(false);

        edit_cities = (Spinner) findViewById(R.id.edit_cities);


        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        ArrayAdapter<String> adapter_districts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,districts) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }

            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {


                } else {

                }
                return view;
            }
        };

        adapter_districts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            edit_districts.setAdapter(adapter_districts);

            edit_districts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItemText = (String) parent.getItemAtPosition(position);

                    if(position > 0){

                    }else {



                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });




        ArrayAdapter<String> adapter_cities = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,cities) {

            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }

            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {


                } else {

                }
                return view;
            }
        };

        adapter_cities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edit_cities.setAdapter(adapter_cities);

        edit_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

                if(position > 0){

                    edit_districts.setEnabled(true);

                }else {

                    edit_districts.setEnabled(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        but_home = (Button) findViewById(R.id.but_home);
        but_home.setOnClickListener(this);


        edit_address = (EditText) findViewById(R.id.edit_address);

        edit_info = (EditText) findViewById(R.id.edit_info);

        edit_size = (EditText) findViewById(R.id.edit_size);

        edit_rooms = (EditText) findViewById(R.id.edit_rooms);

        edit_floor = (EditText) findViewById(R.id.edit_floor);

        edit_dateown = (EditText) findViewById(R.id.edit_dateown);



        edit_name = (EditText) findViewById(R.id.edit_name);

        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        myDataBase = FirebaseDatabase.getInstance().getReference(auth);




    }




    @Override
    public void onClick(View v) {

            Intent intent = getIntent();
            String action = intent.getAction();

            String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

            rootNode = FirebaseDatabase.getInstance();
            myDataBase = rootNode.getReference(auth).child("New_Apartment");


            if (action.equals("1")) {


                 String year = edit_dateown.getText().toString();


                         if (edit_cities.getSelectedItem().toString().equals("Оберіть місто...") || edit_districts.getSelectedItem().toString().equals("Оберіть район...")) {

                             Toast.makeText(ActivityAddNewApartment.this, "Оберіть місто та район!", Toast.LENGTH_SHORT).show();
                         } else {
                             if (edit_address.getText().toString().length() != 0) {

                                 if (!year.equals("") && Integer.parseInt(edit_dateown.getText().toString()) < 1900
                                         && Integer.parseInt(edit_dateown.getText().toString()) < 2100) {

                                         Toast.makeText(ActivityAddNewApartment.this, "Рік введений некоректно!", Toast.LENGTH_SHORT).show();
                                     } else {

                                     intent.putExtra("1", 1);
                                     setResult(RESULT_OK, intent);

                                     String id = "newApartment1";
                                     String address = edit_address.getText().toString();
                                     String rooms = edit_rooms.getText().toString();
                                     String floor = edit_floor.getText().toString();
                                     String dateown = edit_dateown.getText().toString();
                                     String name = edit_name.getText().toString();
                                     String size = edit_size.getText().toString();


                                     String city = edit_cities.getSelectedItem().toString();
                                     String district = edit_districts.getSelectedItem().toString();


                                     ApartmentsClass newApartment = new ApartmentsClass(id, address, city, district, rooms, floor, dateown, name,size);


                                     myDataBase.child(id).setValue(newApartment);

                                     finish();
                                 }

                             } else {


                                 edit_address.setError("Введіть дані!");
                                 edit_address.requestFocus();
                             }


                         }



            }


        else if (action.equals("2")) {

                String year = edit_dateown.getText().toString();


                if (edit_cities.getSelectedItem().toString().equals("Оберіть місто...") || edit_districts.getSelectedItem().toString().equals("Оберіть район...")) {

                    Toast.makeText(ActivityAddNewApartment.this, "Оберіть місто та район!", Toast.LENGTH_SHORT).show();
                } else {
                    if (edit_address.getText().toString().length() != 0) {

                        if (!year.equals("") && Integer.parseInt(edit_dateown.getText().toString()) < 1900
                                && Integer.parseInt(edit_dateown.getText().toString()) < 2100) {

                            Toast.makeText(ActivityAddNewApartment.this, "Рік введений некоректно!", Toast.LENGTH_SHORT).show();
                        } else {

                            intent.putExtra("2", 2);
                            setResult(RESULT_OK, intent);

                            String id = "newApartment2";
                            String address = edit_address.getText().toString();
                            String rooms = edit_rooms.getText().toString();
                            String floor = edit_floor.getText().toString();
                            String dateown = edit_dateown.getText().toString();
                            String name = edit_name.getText().toString();
                            String size = edit_size.getText().toString();


                            String city = edit_cities.getSelectedItem().toString();
                            String district = edit_districts.getSelectedItem().toString();


                            ApartmentsClass newApartment = new ApartmentsClass(id, address, city, district, rooms, floor, dateown, name,size);


                            myDataBase.child(id).setValue(newApartment);

                            finish();
                        }

                    } else {


                        edit_address.setError("Введіть дані!");
                        edit_address.requestFocus();
                    }


                }

           }


         else if (action.equals("3")) {

                String year = edit_dateown.getText().toString();


                if (edit_cities.getSelectedItem().toString().equals("Оберіть місто...") || edit_districts.getSelectedItem().toString().equals("Оберіть район...")) {

                    Toast.makeText(ActivityAddNewApartment.this, "Оберіть місто та район!", Toast.LENGTH_SHORT).show();
                } else {
                    if (edit_address.getText().toString().length() != 0) {

                        if (!year.equals("") && Integer.parseInt(edit_dateown.getText().toString()) < 1900
                                && Integer.parseInt(edit_dateown.getText().toString()) < 2100) {

                            Toast.makeText(ActivityAddNewApartment.this, "Рік введений некоректно!", Toast.LENGTH_SHORT).show();
                        } else {

                            intent.putExtra("3", 3);
                            setResult(RESULT_OK, intent);

                            String id = "newApartment3";
                            String address = edit_address.getText().toString();
                            String rooms = edit_rooms.getText().toString();
                            String floor = edit_floor.getText().toString();
                            String dateown = edit_dateown.getText().toString();
                            String name = edit_name.getText().toString();
                            String size = edit_size.getText().toString();


                            String city = edit_cities.getSelectedItem().toString();
                            String district = edit_districts.getSelectedItem().toString();


                            ApartmentsClass newApartment = new ApartmentsClass(id, address, city, district, rooms, floor, dateown, name,size);


                            myDataBase.child(id).setValue(newApartment);

                            finish();
                        }

                    } else {


                        edit_address.setError("Введіть дані!");
                        edit_address.requestFocus();
                    }


                }

         }

         else if (action.equals("4")) {

                String year = edit_dateown.getText().toString();


                if (edit_cities.getSelectedItem().toString().equals("Оберіть місто...") || edit_districts.getSelectedItem().toString().equals("Оберіть район...")) {

                    Toast.makeText(ActivityAddNewApartment.this, "Оберіть місто та район!", Toast.LENGTH_SHORT).show();
                } else {
                    if (edit_address.getText().toString().length() != 0) {

                        if (!year.equals("") && Integer.parseInt(edit_dateown.getText().toString()) < 1900
                                && Integer.parseInt(edit_dateown.getText().toString()) < 2100) {

                            Toast.makeText(ActivityAddNewApartment.this, "Рік введений некоректно!", Toast.LENGTH_SHORT).show();
                        } else {

                            intent.putExtra("4", 4);
                            setResult(RESULT_OK, intent);

                            String id = "newApartment4";
                            String address = edit_address.getText().toString();
                            String rooms = edit_rooms.getText().toString();
                            String floor = edit_floor.getText().toString();
                            String dateown = edit_dateown.getText().toString();
                            String name = edit_name.getText().toString();
                            String size = edit_size.getText().toString();


                            String city = edit_cities.getSelectedItem().toString();
                            String district = edit_districts.getSelectedItem().toString();


                            ApartmentsClass newApartment = new ApartmentsClass(id, address, city, district, rooms, floor, dateown, name, size);


                            myDataBase.child(id).setValue(newApartment);

                            finish();
                        }

                    } else {


                        edit_address.setError("Введіть дані!");
                        edit_address.requestFocus();
                    }


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





}