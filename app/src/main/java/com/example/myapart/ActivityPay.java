package com.example.myapart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import db.Pay.PayClass;

public class ActivityPay extends AppCompatActivity implements View.OnClickListener{

    EditText sum,date_pay,name;

    Button add_oplaty;

    private DatabaseReference myDataBase;
    private String PayClass = "Pay_History";
    SharedPreferences sharedPreferences;

    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");


        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sum = (EditText) findViewById(R.id.sum);
        date_pay = (EditText) findViewById(R.id.date_pay);
        date_pay.addTextChangedListener(new TextWatcher() {

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
                    date_pay.setText(current);
                    date_pay.setSelection(sel < current.length() ? sel : current.length());



                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        name = (EditText) findViewById(R.id.namee);

        add_oplaty = (Button) findViewById(R.id.add_oplaty);
        add_oplaty.setOnClickListener(this);

        myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(PayClass);
    }

    public void onClick(View v) {
        Intent intent1 = getIntent();
        String action = intent1.getAction();

        if (action.equals("pay1")) {
            if(sum.getText().toString().length()!=0 && date_pay.getText().toString().length()!=0) {



                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(PayClass).child("apartment1");

                String id = "apartment1";
                String pay = date_pay.getText().toString();
                String summ = sum.getText().toString();
                String namee = name.getText().toString();
                String user = auth;

                PayClass newPay = new PayClass(id,summ,pay,namee,user);
                myDataBase.push().setValue(newPay);



                intent1.putExtra("11111", 11111);
                setResult(RESULT_OK, intent1);
                Toast.makeText(this,"Оплату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }

        if (action.equals("pay2")) {
            if(sum.getText().toString().length()!=0 && date_pay.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(PayClass).child("apartment2");

                String pay = date_pay.getText().toString();
                String id = "apartment2";
                String summ = sum.getText().toString();
                String namee = name.getText().toString();
                String user = auth;

                intent1.putExtra("pay2",summ);


                PayClass newPay = new PayClass(id,summ,pay,namee,user);
                myDataBase.push().setValue(newPay);

                intent1.putExtra("22222", 22222);
                setResult(RESULT_OK, intent1);
                Toast.makeText(this,"Оплату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }

        if (action.equals("pay3")) {
            if(sum.getText().toString().length()!=0 && date_pay.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(PayClass).child("apartment3");

                String pay = date_pay.getText().toString();
                String summ = sum.getText().toString();
                String namee = name.getText().toString();
                String user = auth;
                String id = "apartment3";

                intent1.putExtra("pay3",summ);

                PayClass newPay = new PayClass(id,summ,pay,namee,user);
                myDataBase.push().setValue(newPay);

                intent1.putExtra("33333", 33333);
                setResult(RESULT_OK, intent1);
                Toast.makeText(this,"Оплату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }

        if (action.equals("pay4")) {
            if(sum.getText().toString().length()!=0 && date_pay.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(PayClass).child("apartment4");

                String pay = date_pay.getText().toString();
                String summ = sum.getText().toString();
                String namee = name.getText().toString();
                String user = auth;
                String id = "apartment4";

                intent1.putExtra("pay4",summ);

                PayClass newPay = new PayClass(id,summ,pay,namee,user);
                myDataBase.push().setValue(newPay);

                intent1.putExtra("44444", 44444);
                setResult(RESULT_OK, intent1);
                Toast.makeText(this,"Оплату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }
    }
}