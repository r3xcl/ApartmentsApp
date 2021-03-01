package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import db.ClientClass;

public class Activity_FindClient extends AppCompatActivity implements View.OnClickListener{

    private ListView listView1;
    private ArrayAdapter<String> adapter1;
    private List<String> listData1;
    private DatabaseReference myDataBase ;
    private String New_Client = "New_Client";

    private List<ClientClass> listTemp1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__find_client);

        Intent intent = getIntent();
        String action = intent.getAction();
        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ


            listView1 = findViewById(R.id.listView1);
            listData1 = new ArrayList<>();
            listTemp1 = new ArrayList<>();
            adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);

            listView1.setAdapter(adapter1);

            myDataBase = FirebaseDatabase.getInstance().getReference(New_Client);

            getDataFromDB();

            setOnClickItem();


    }

    private void getDataFromDB(){

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(listData1.size() > 0 )listData1.clear();
                if(listTemp1.size() > 0 )listTemp1.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    ClientClass clientClass = ds.getValue(ClientClass.class);
                    assert clientClass != null;
                    listData1.add(clientClass.getSurname() + " " +clientClass.getName() + " " + clientClass.getPatronymic());
                    listTemp1.add(clientClass);

                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myDataBase.addValueEventListener(vListener);
    }

    @Override
    public void onClick(View v) {
        
    }

    private void setOnClickItem(){

        Intent intent = getIntent();
        String action = intent.getAction();
        if (action.equals("find_client1")) {

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClientClass clientClass = listTemp1.get(position);

                    intent.putExtra("surname1", clientClass.getSurname());
                    intent.putExtra("name1", clientClass.getName());
                    intent.putExtra("patronymic1", clientClass.getPatronymic());
                    intent.putExtra("number1", clientClass.getNumber());
                    intent.putExtra("datestart1", clientClass.getDatestart());
                    intent.putExtra("dateend1",clientClass.getDateend());
                    intent.putExtra("sumpay1",clientClass.getPay());

                    intent.putExtra("111", 111);
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });
        }

        if (action.equals("find_client2")) {

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClientClass clientClass = listTemp1.get(position);

                    intent.putExtra("surname2", clientClass.getSurname());
                    intent.putExtra("name2", clientClass.getName());
                    intent.putExtra("patronymic2", clientClass.getPatronymic());
                    intent.putExtra("number2", clientClass.getNumber());
                    intent.putExtra("datestart2", clientClass.getDatestart());
                    intent.putExtra("dateend2",clientClass.getDateend());
                    intent.putExtra("sumpay2",clientClass.getPay());

                    intent.putExtra("222", 222);
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });
        }

        if (action.equals("find_client3")) {

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClientClass clientClass = listTemp1.get(position);

                    intent.putExtra("surname3", clientClass.getSurname());
                    intent.putExtra("name3", clientClass.getName());
                    intent.putExtra("patronymic3", clientClass.getPatronymic());
                    intent.putExtra("number3", clientClass.getNumber());
                    intent.putExtra("datestart3", clientClass.getDatestart());
                    intent.putExtra("dateend3",clientClass.getDateend());
                    intent.putExtra("sumpay3",clientClass.getPay());

                    intent.putExtra("333", 333);
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });
        }

        if (action.equals("find_client4")) {

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClientClass clientClass = listTemp1.get(position);

                    intent.putExtra("surname4", clientClass.getSurname());
                    intent.putExtra("name4", clientClass.getName());
                    intent.putExtra("patronymic4", clientClass.getPatronymic());
                    intent.putExtra("number4", clientClass.getNumber());
                    intent.putExtra("datestart4", clientClass.getDatestart());
                    intent.putExtra("dateend4",clientClass.getDateend());
                    intent.putExtra("sumpay4",clientClass.getPay());

                    intent.putExtra("444", 444);
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });
        }

        if (action.equals("find_client5")) {

            listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ClientClass clientClass = listTemp1.get(position);

                    intent.putExtra("surname5", clientClass.getSurname());
                    intent.putExtra("name5", clientClass.getName());
                    intent.putExtra("patronymic5", clientClass.getPatronymic());
                    intent.putExtra("number5", clientClass.getNumber());
                    intent.putExtra("datestart5", clientClass.getDatestart());
                    intent.putExtra("dateend5",clientClass.getDateend());
                    intent.putExtra("sumpay5",clientClass.getPay());

                    intent.putExtra("555", 555);
                    setResult(RESULT_OK, intent);

                    finish();
                }
            });
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