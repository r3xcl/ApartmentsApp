package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import db.ClientClass;

public class Activity_NewClient extends AppCompatActivity implements View.OnClickListener{

    TextView client_name,client_surname,client_patronymic,client_number,client_datestart,client_dateend,client_info,client_pay;

    Button add_new_client;

    private DatabaseReference myDataBase ;
    private String New_Client = "New_Client";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__new_client);
        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ



        client_name = (TextView) findViewById(R.id.client_name);
        client_surname = (TextView) findViewById(R.id.client_surname);
        client_patronymic = (TextView) findViewById(R.id.client_patronymic);
        client_number = (TextView) findViewById(R.id.client_number);
        client_datestart = (TextView) findViewById(R.id.client_datestart);
        client_dateend = (TextView) findViewById(R.id.client_dateend);
        client_info = (TextView) findViewById(R.id.client_info);
        client_pay = (TextView) findViewById(R.id.client_pay);

        add_new_client = (Button) findViewById(R.id.add_new_client);
        add_new_client.setOnClickListener(this);


        myDataBase = FirebaseDatabase.getInstance().getReference(New_Client);

    }

    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String action = intent.getAction();

        if (action.equals("new_client1")) {

            if(client_name.getText().toString().length()!=0 && client_number.getText().toString().length()!=0
            && client_datestart.getText().toString().length()!=0 && client_dateend.getText().toString().length()!=0) {
                intent.putExtra("client_name1", client_name.getText().toString());
                intent.putExtra("client_surname1", client_surname.getText().toString());
                intent.putExtra("client_patronymic1", client_patronymic.getText().toString());
                intent.putExtra("client_number1", client_number.getText().toString());
                intent.putExtra("client_datestart1", client_datestart.getText().toString());
                intent.putExtra("client_dateend1", client_dateend.getText().toString());
                intent.putExtra("client_info1", client_info.getText().toString());
                intent.putExtra("client_pay1", client_pay.getText().toString());




            String id = myDataBase.getKey();
            String surname = client_surname.getText().toString();
            String name = client_name.getText().toString();
            String patronymic = client_patronymic.getText().toString();
            String number = client_number.getText().toString();
            String datestart = client_datestart.getText().toString();
            String dateend = client_dateend.getText().toString();
            String pay = client_pay.getText().toString();
            String zastava = client_info.getText().toString();

            ClientClass newClient = new ClientClass(id,surname,name,patronymic,number,datestart,dateend,pay,zastava);
            myDataBase.push().setValue(newClient);

            intent.putExtra("11", 11);
            setResult(RESULT_OK, intent);



            finish();

            }else {
                Toast.makeText(Activity_NewClient.this,"Введіть ім'я,номер телефона та дати!",Toast.LENGTH_LONG).show();
            }

        }

        if (action.equals("new_client2")) {

            if(client_name.getText().toString().length()!=0 && client_number.getText().toString().length()!=0
                    && client_datestart.getText().toString().length()!=0 && client_dateend.getText().toString().length()!=0) {
            intent.putExtra("client_name2", client_name.getText().toString());
            intent.putExtra("client_surname2", client_surname.getText().toString());
            intent.putExtra("client_patronymic2", client_patronymic.getText().toString());
            intent.putExtra("client_number2", client_number.getText().toString());
            intent.putExtra("client_datestart2", client_datestart.getText().toString());
            intent.putExtra("client_dateend2", client_dateend.getText().toString());
            intent.putExtra("client_info2", client_info.getText().toString());
            intent.putExtra("client_pay2", client_pay.getText().toString());



            String id = myDataBase.getKey();
            String surname = client_surname.getText().toString();
            String name = client_name.getText().toString();
            String patronymic = client_patronymic.getText().toString();
            String number = client_number.getText().toString();
            String datestart = client_datestart.getText().toString();
            String dateend = client_dateend.getText().toString();
            String pay = client_pay.getText().toString();
                String zastava = client_info.getText().toString();

            ClientClass newClient = new ClientClass(id,surname,name,patronymic,number,datestart,dateend,pay,zastava);
            myDataBase.push().setValue(newClient);

            intent.putExtra("22", 22);
            setResult(RESULT_OK, intent);



            finish();

            }else {
                Toast.makeText(Activity_NewClient.this,"Введіть ім'я,номер телефона та дати!",Toast.LENGTH_LONG).show();
            }

        }

        if (action.equals("new_client3")) {

            if(client_name.getText().toString().length()!=0 && client_number.getText().toString().length()!=0
                    && client_datestart.getText().toString().length()!=0 && client_dateend.getText().toString().length()!=0) {
            intent.putExtra("client_name3", client_name.getText().toString());
            intent.putExtra("client_surname3", client_surname.getText().toString());
            intent.putExtra("client_patronymic3", client_patronymic.getText().toString());
            intent.putExtra("client_number3", client_number.getText().toString());
            intent.putExtra("client_datestart3", client_datestart.getText().toString());
            intent.putExtra("client_dateend3", client_dateend.getText().toString());
            intent.putExtra("client_info3", client_info.getText().toString());
            intent.putExtra("client_pay3", client_pay.getText().toString());

            String id = myDataBase.getKey();
            String surname = client_surname.getText().toString();
            String name = client_name.getText().toString();
            String patronymic = client_patronymic.getText().toString();
            String number = client_number.getText().toString();
            String datestart = client_datestart.getText().toString();
            String dateend = client_dateend.getText().toString();
            String pay = client_pay.getText().toString();
                String zastava = client_info.getText().toString();

            ClientClass newClient = new ClientClass(id,surname,name,patronymic,number,datestart,dateend,pay,zastava);
            myDataBase.push().setValue(newClient);

            intent.putExtra("33", 33);
            setResult(RESULT_OK, intent);



            finish();
            }else {
                Toast.makeText(Activity_NewClient.this,"Введіть ім'я,номер телефона та дати!",Toast.LENGTH_LONG).show();
            }



        }

        if (action.equals("new_client4")) {

            if(client_name.getText().toString().length()!=0 && client_number.getText().toString().length()!=0
                    && client_datestart.getText().toString().length()!=0 && client_dateend.getText().toString().length()!=0) {
            intent.putExtra("client_name4", client_name.getText().toString());
            intent.putExtra("client_surname4", client_surname.getText().toString());
            intent.putExtra("client_patronymic4", client_patronymic.getText().toString());
            intent.putExtra("client_number4", client_number.getText().toString());
            intent.putExtra("client_datestart4", client_datestart.getText().toString());
            intent.putExtra("client_dateend4", client_dateend.getText().toString());
            intent.putExtra("client_pay4", client_pay.getText().toString());
            intent.putExtra("client_info4", client_info.getText().toString());

            String id = myDataBase.getKey();
            String surname = client_surname.getText().toString();
            String name = client_name.getText().toString();
            String patronymic = client_patronymic.getText().toString();
            String number = client_number.getText().toString();
            String datestart = client_datestart.getText().toString();
            String dateend = client_dateend.getText().toString();
            String pay = client_pay.getText().toString();
                String zastava = client_info.getText().toString();

            ClientClass newClient = new ClientClass(id,surname,name,patronymic,number,datestart,dateend,pay,zastava);
            myDataBase.push().setValue(newClient);

            intent.putExtra("44", 44);
            setResult(RESULT_OK, intent);



            finish();

            }else {
                Toast.makeText(Activity_NewClient.this,"Введіть ім'я,номер телефона та дати!",Toast.LENGTH_LONG).show();
            }


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

    @Override
    protected void onResume() {

        super.onResume();



    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}