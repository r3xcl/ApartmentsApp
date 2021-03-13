package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityShowAllinfoClient extends AppCompatActivity {

    private TextView surname,name,patronymic,number,datestart,dateend,sumpay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_allinfo_client);
        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        surname  = (TextView) findViewById(R.id.surname);
        name  = (TextView) findViewById(R.id.name);
        patronymic  = (TextView) findViewById(R.id.patronymic);
        number  = (TextView) findViewById(R.id.number);
        datestart  = (TextView) findViewById(R.id.datestart);
        dateend  = (TextView) findViewById(R.id.dateend);
        sumpay  = (TextView) findViewById(R.id.sumpay);

        getIntentMain4();

    }

    private void getIntentMain4(){
        Intent i = getIntent();
        if (i!=null){
            surname.setText(i.getStringExtra("surname"));
            name.setText(i.getStringExtra("name"));
            patronymic.setText(i.getStringExtra("patronymic"));
            number.setText(i.getStringExtra("number"));
            datestart.setText(i.getStringExtra("datestart"));
            dateend.setText(i.getStringExtra("dateend"));
            sumpay.setText(i.getStringExtra("sumpay") + " Грн.");
        }
    }
}