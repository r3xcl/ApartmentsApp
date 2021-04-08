package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import db.Repair.RepairClass;

public class ActivityRepairs extends AppCompatActivity implements View.OnClickListener {

    EditText sum_repair,date_repair,name_repair;

    Button add_repair;

    private DatabaseReference myDataBase;
    private String RepairClass = "Repairs_History";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs);

        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        sum_repair = (EditText) findViewById(R.id.sum_repair);
        name_repair = (EditText) findViewById(R.id.name_repair);
        date_repair = (EditText) findViewById(R.id.date_repair);
        date_repair.addTextChangedListener(new TextWatcher() {

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
                    date_repair.setText(current);
                    date_repair.setSelection(sel < current.length() ? sel : current.length());



                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        add_repair = (Button) findViewById(R.id.add_repair);
        add_repair.setOnClickListener(this);

        myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(RepairClass);
    }

    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("remont1")) {
            if(sum_repair.getText().toString().length()!=0 && date_repair.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                String id = "apartment1";
                String date = date_repair.getText().toString();
                String sum = sum_repair.getText().toString();
                String name = name_repair.getText().toString();
                String user =auth;



                db.Repair.RepairClass repairClass = new RepairClass(id,sum,date,name,user);
                myDataBase.push().setValue(repairClass);

                intent.putExtra("111111", 101);
                setResult(RESULT_OK, intent);
                Toast.makeText(this,"Витрату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }

        if (action.equals("remont2")) {
            if(sum_repair.getText().toString().length()!=0 && date_repair.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                String id = "apartment2";
                String pay = date_repair.getText().toString();
                String summ = sum_repair.getText().toString();
                String name = name_repair.getText().toString();
                String user = auth;


                db.Repair.RepairClass repairClass = new RepairClass(id,summ,pay,name,user);
                myDataBase.push().setValue(repairClass);

                intent.putExtra("222222", 202);
                setResult(RESULT_OK, intent);
                Toast.makeText(this,"Витрату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }

        if (action.equals("remont3")) {
            if(sum_repair.getText().toString().length()!=0 && date_repair.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                String id = "apartment3";
                String pay = date_repair.getText().toString();
                String summ = sum_repair.getText().toString();
                String name = name_repair.getText().toString();
                String user = auth;


                db.Repair.RepairClass repairClass = new RepairClass(id,summ,pay,name,user);
                myDataBase.push().setValue(repairClass);



                intent.putExtra("333333", 303);
                setResult(RESULT_OK, intent);
                Toast.makeText(this,"Витрату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }

        if (action.equals("remont4")) {
            if(sum_repair.getText().toString().length()!=0 && date_repair.getText().toString().length()!=0) {

                String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

                String id = "apartment4";

                String pay = date_repair.getText().toString();
                String summ = sum_repair.getText().toString();
                String name = name_repair.getText().toString();
                String user = auth;


                db.Repair.RepairClass repairClass = new RepairClass(id,summ,pay,name,user);
                myDataBase.push().setValue(repairClass);


                intent.putExtra("444444", 404);
                setResult(RESULT_OK, intent);
                Toast.makeText(this,"Витрату додано!",Toast.LENGTH_LONG).show();
                finish();

            }else {
                Toast.makeText(this,"Введіть суму та дату!",Toast.LENGTH_LONG).show();
            }
        }
    }


}