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

import db.Repair.RepairClass;

public class ActivityRepairs extends AppCompatActivity implements View.OnClickListener {

    TextView sum_repair,date_repair,name_repair;

    Button add_repair;

    private DatabaseReference myDataBase;
    private String RepairClass = "Repairs_History";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__repairs);

        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        sum_repair = (TextView) findViewById(R.id.sum_repair);
        name_repair = (TextView) findViewById(R.id.name_repair);
        date_repair = (TextView) findViewById(R.id.date_repair);

        add_repair = (Button) findViewById(R.id.add_repair);
        add_repair.setOnClickListener(this);

        myDataBase = FirebaseDatabase.getInstance().getReference(RepairClass);
    }

    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("remont1")) {
            if(sum_repair.getText().toString().length()!=0 && date_repair.getText().toString().length()!=0) {

                String id = "apartment1";
                String date = date_repair.getText().toString();
                String sum = sum_repair.getText().toString();
                String name = name_repair.getText().toString();


                db.Repair.RepairClass repairClass = new RepairClass(id,sum,date,name);
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



                String id = "apartment2";
                String pay = date_repair.getText().toString();
                String summ = sum_repair.getText().toString();
                String name = name_repair.getText().toString();


                db.Repair.RepairClass repairClass = new RepairClass(id,summ,pay,name);
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



                String id = "apartment3";
                String pay = date_repair.getText().toString();
                String summ = sum_repair.getText().toString();
                String name = name_repair.getText().toString();


                db.Repair.RepairClass repairClass = new RepairClass(id,summ,pay,name);
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



                String id = "apartment4";

                String pay = date_repair.getText().toString();
                String summ = sum_repair.getText().toString();
                String name = name_repair.getText().toString();


                db.Repair.RepairClass repairClass = new RepairClass(id,summ,pay,name);
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