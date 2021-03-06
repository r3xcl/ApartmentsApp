package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import db.ClientClass;

public class Activity_Add_Client extends AppCompatActivity implements View.OnClickListener {

    TextView add_name, add_surname, add_patronymic, add_number, add_datestart, add_dateend, add_pay,client_info;

    Button add_client;

    private DatabaseReference myDataBase;
    private String New_Client = "New_Client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__client);

        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ


        add_name = (TextView) findViewById(R.id.add_name);
        add_surname = (TextView) findViewById(R.id.add_surname);
        add_patronymic = (TextView) findViewById(R.id.add_patronymic);
        add_number = (TextView) findViewById(R.id.add_number);
        add_datestart = (TextView) findViewById(R.id.add_datestart);
        add_dateend = (TextView) findViewById(R.id.add_dateend);
        client_info = (TextView) findViewById(R.id.client_info);

        add_pay = (TextView) findViewById(R.id.add_pay);

        add_client = (Button) findViewById(R.id.add_client);
        add_client.setOnClickListener(this);


        myDataBase = FirebaseDatabase.getInstance().getReference(New_Client);
    }

    public void onClick(View v) {
        Intent intent = getIntent();
        String action = intent.getAction();



        if (action.equals("add_client")) {

                    if (add_name.getText().toString().length() != 0 && add_number.getText().toString().length() != 0
                            && add_datestart.getText().toString().length() != 0 && add_dateend.getText().toString().length() != 0
                            && client_info.getText().toString().length() != 0 ) {

                        String id = myDataBase.getKey();
                        String surname = add_surname.getText().toString();
                        String name = add_name.getText().toString();
                        String patronymic = add_patronymic.getText().toString();
                        String number = add_number.getText().toString();
                        String datestart = add_datestart.getText().toString();
                        String dateend = add_dateend.getText().toString();
                        String pay = add_pay.getText().toString();
                        String zastava = client_info.getText().toString();

                        ClientClass newClient = new ClientClass(id, surname, name, patronymic, number, datestart, dateend, pay, zastava);
                        myDataBase.push().setValue(newClient);

                        intent.putExtra("1", 1);
                        setResult(RESULT_OK, intent);

                        finish();

                    } else {
                        Toast.makeText(Activity_Add_Client.this, "Невірні дати! Ведіть ім'я,номер телефона,суму застави та дати!", Toast.LENGTH_LONG).show();
                    }


        }
    }
}