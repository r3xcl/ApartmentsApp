package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import db.Client.ClientClass;

public class ActivityNewClient extends AppCompatActivity implements View.OnClickListener{



    EditText client_name,client_surname,client_patronymic,client_number,client_datestart,client_dateend,client_info,client_pay,client_email;

    Button add_new_client;

    ImageButton back;

    private DatabaseReference myDataBase ;
    private String New_Client = "New_Client";

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        client_name = (EditText) findViewById(R.id.client_name);
        client_surname = (EditText) findViewById(R.id.client_surname);
        client_patronymic = (EditText) findViewById(R.id.client_patronymic);
        client_number = (EditText) findViewById(R.id.client_number);
        client_datestart = (EditText) findViewById(R.id.client_datestart);
        client_dateend = (EditText) findViewById(R.id.client_dateend);
        client_info = (EditText) findViewById(R.id.client_info);
        client_pay = (EditText) findViewById(R.id.client_pay);
        client_email = (EditText) findViewById(R.id.client_email);

        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        add_new_client = (Button) findViewById(R.id.add_new_client);
        add_new_client.setOnClickListener(this);

             client_datestart.addTextChangedListener(new TextWatcher() {

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
                         client_datestart.setText(current);
                         client_datestart.setSelection(sel < current.length() ? sel : current.length());



                     }
                 }

                 @Override
                 public void afterTextChanged(Editable s) {

                 }
             });

             client_dateend.addTextChangedListener(new TextWatcher() {

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
                         client_dateend.setText(current);
                         client_dateend.setSelection(sel < current.length() ? sel : current.length());



                     }


                 }

                 @Override
                 public void afterTextChanged(Editable s) {

                 }
             });
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        myDataBase = FirebaseDatabase.getInstance().getReference(auth).child(New_Client);

    }



    @Override
    public void onClick(View v) {

        Intent intent = getIntent();
        String action = intent.getAction();


        if(client_name.getText().toString().length()==0) {

            client_name.setError("Введіть дані!");
            client_name.requestFocus();

        }

        if(client_info.getText().toString().length()==0){

            client_info.setError("Введіть дані!");
            client_info.requestFocus();
        }
        
        if(client_datestart.getText().toString().length()==0){


            client_datestart.setError("Введіть дані!");
            client_datestart.requestFocus();
        }

        if(client_dateend.getText().toString().length()==0){
            client_dateend.setError("Введіть дані!");
            client_dateend.requestFocus();
        }


        String email1 = client_email.getText().toString();

        if (email1.length()!=0&&!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){

            client_email.setError("Формат пошти невірний!");
            client_email.requestFocus();

        }


        if (action.equals("new_client1")) {


            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                String datestart1 = client_datestart.getText().toString();
                Date dateS = format.parse(datestart1);

                String dateend1 = client_dateend.getText().toString();
                Date dateE = format.parse(dateend1);


                if (email1.length()!=0&&!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){

                    client_email.setError("Формат пошти невірний!");
                    client_email.requestFocus();

                } else {
                if(dateE.compareTo(dateS)<0) {

                    Toast.makeText(ActivityNewClient.this, "Початок оренди не може бути раніше завершення!", Toast.LENGTH_LONG).show();
                }
                else {

                    if (client_name.getText().toString().length() != 0
                            && client_datestart.getText().toString().length() != 0 && client_dateend.getText().toString().length() != 0
                            && client_info.getText().toString().length() != 0) {



                        intent.putExtra("client_name1", client_name.getText().toString());
                        intent.putExtra("client_surname1", client_surname.getText().toString());
                        intent.putExtra("client_patronymic1", client_patronymic.getText().toString());
                        intent.putExtra("client_number1", client_number.getText().toString());
                        intent.putExtra("client_datestart1", client_datestart.getText().toString());
                        intent.putExtra("client_dateend1", client_dateend.getText().toString());
                        intent.putExtra("client_info1", client_info.getText().toString());
                        intent.putExtra("client_pay1", client_pay.getText().toString());
                        intent.putExtra("client_email1", client_email.getText().toString());


                        String id = client_name.getText().toString();
                        String surname = client_surname.getText().toString();
                        String name = client_name.getText().toString();
                        String patronymic = client_patronymic.getText().toString();
                        String number = client_number.getText().toString();
                        String datestart = client_datestart.getText().toString();
                        String dateend = client_dateend.getText().toString();
                        String pay = client_pay.getText().toString();
                        String zastava = client_info.getText().toString();
                        String email = client_email.getText().toString();
                        String user = sharedPreferences.getString("auth","");
                        String busyness = "0";


                        ClientClass newClient = new ClientClass(id, surname, name, patronymic, number, email, datestart, dateend, pay, zastava,busyness,user);
                        myDataBase.push().setValue(newClient);

                        intent.putExtra("11", 11);
                        setResult(RESULT_OK, intent);


                        finish();

                        Toast.makeText(ActivityNewClient.this, "Орендаря додано!", Toast.LENGTH_LONG).show();

                    } else {

                    }
                  }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        if (action.equals("new_client2")) {

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                String datestart1 = client_datestart.getText().toString();
                Date dateS = format.parse(datestart1);

                String dateend1 = client_dateend.getText().toString();
                Date dateE = format.parse(dateend1);


                if (email1.length()!=0&&!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){

                    client_email.setError("Формат пошти невірний!");
                    client_email.requestFocus();

                } else {
                    if(dateE.compareTo(dateS)<0) {

                        Toast.makeText(ActivityNewClient.this, "Початок оренди не може бути раніше завершення!", Toast.LENGTH_LONG).show();
                    }
                    else {

                        if (client_name.getText().toString().length() != 0
                                && client_datestart.getText().toString().length() != 0 && client_dateend.getText().toString().length() != 0
                                && client_info.getText().toString().length() != 0) {

                            intent.putExtra("client_name2", client_name.getText().toString());
                            intent.putExtra("client_surname2", client_surname.getText().toString());
                            intent.putExtra("client_patronymic2", client_patronymic.getText().toString());
                            intent.putExtra("client_number2", client_number.getText().toString());
                            intent.putExtra("client_datestart2", client_datestart.getText().toString());
                            intent.putExtra("client_dateend2", client_dateend.getText().toString());
                            intent.putExtra("client_info2", client_info.getText().toString());
                            intent.putExtra("client_pay2", client_pay.getText().toString());
                            intent.putExtra("client_email2", client_email.getText().toString());


                            String id = client_name.getText().toString();
                            String surname = client_surname.getText().toString();
                            String name = client_name.getText().toString();
                            String patronymic = client_patronymic.getText().toString();
                            String number = client_number.getText().toString();
                            String datestart = client_datestart.getText().toString();
                            String dateend = client_dateend.getText().toString();
                            String pay = client_pay.getText().toString();
                            String zastava = client_info.getText().toString();
                            String email = client_email.getText().toString();
                            String busyness = "0";
                            String user = sharedPreferences.getString("auth","");


                            ClientClass newClient = new ClientClass(id, surname, name, patronymic, number, email, datestart, dateend, pay, zastava,busyness,user);
                            myDataBase.push().setValue(newClient);

                            intent.putExtra("22", 22);
                            setResult(RESULT_OK, intent);


                            finish();

                            Toast.makeText(ActivityNewClient.this, "Орендаря додано!", Toast.LENGTH_LONG).show();

                        } else {

                        }
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        if (action.equals("new_client3")) {

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                String datestart1 = client_datestart.getText().toString();
                Date dateS = format.parse(datestart1);

                String dateend1 = client_dateend.getText().toString();
                Date dateE = format.parse(dateend1);


                if (email1.length()!=0&&!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){

                    client_email.setError("Формат пошти невірний!");
                    client_email.requestFocus();

                } else {
                    if(dateE.compareTo(dateS)<0) {

                        Toast.makeText(ActivityNewClient.this, "Початок оренди не може бути раніше завершення!", Toast.LENGTH_LONG).show();
                    }
                    else {

                        if (client_name.getText().toString().length() != 0
                                && client_datestart.getText().toString().length() != 0 && client_dateend.getText().toString().length() != 0
                                && client_info.getText().toString().length() != 0) {

                            intent.putExtra("client_name3", client_name.getText().toString());
                            intent.putExtra("client_surname3", client_surname.getText().toString());
                            intent.putExtra("client_patronymic3", client_patronymic.getText().toString());
                            intent.putExtra("client_number3", client_number.getText().toString());
                            intent.putExtra("client_datestart3", client_datestart.getText().toString());
                            intent.putExtra("client_dateend3", client_dateend.getText().toString());
                            intent.putExtra("client_info3", client_info.getText().toString());
                            intent.putExtra("client_pay3", client_pay.getText().toString());
                            intent.putExtra("client_email3", client_email.getText().toString());


                            String id = client_name.getText().toString();
                            String surname = client_surname.getText().toString();
                            String name = client_name.getText().toString();
                            String patronymic = client_patronymic.getText().toString();
                            String number = client_number.getText().toString();
                            String datestart = client_datestart.getText().toString();
                            String dateend = client_dateend.getText().toString();
                            String pay = client_pay.getText().toString();
                            String zastava = client_info.getText().toString();
                            String email = client_email.getText().toString();
                            String user = sharedPreferences.getString("auth","");
                            String busyness = "0";


                            ClientClass newClient = new ClientClass(id, surname, name, patronymic, number, email, datestart, dateend, pay, zastava,busyness,user);
                            myDataBase.push().setValue(newClient);

                            intent.putExtra("33", 33);
                            setResult(RESULT_OK, intent);


                            finish();

                            Toast.makeText(ActivityNewClient.this, "Орендаря додано!", Toast.LENGTH_LONG).show();

                        } else {

                        }
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }

        if (action.equals("new_client4")) {

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                String datestart1 = client_datestart.getText().toString();
                Date dateS = format.parse(datestart1);

                String dateend1 = client_dateend.getText().toString();
                Date dateE = format.parse(dateend1);


                if (email1.length()!=0&&!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){

                    client_email.setError("Формат пошти невірний!");
                    client_email.requestFocus();

                } else {
                    if(dateE.compareTo(dateS)<0) {

                        Toast.makeText(ActivityNewClient.this, "Початок оренди не може бути раніше завершення!", Toast.LENGTH_LONG).show();
                    }
                    else {

                        if (client_name.getText().toString().length() != 0
                                && client_datestart.getText().toString().length() != 0 && client_dateend.getText().toString().length() != 0
                                && client_info.getText().toString().length() != 0) {

                            intent.putExtra("client_name4", client_name.getText().toString());
                            intent.putExtra("client_surname4", client_surname.getText().toString());
                            intent.putExtra("client_patronymic4", client_patronymic.getText().toString());
                            intent.putExtra("client_number4", client_number.getText().toString());
                            intent.putExtra("client_datestart4", client_datestart.getText().toString());
                            intent.putExtra("client_dateend4", client_dateend.getText().toString());
                            intent.putExtra("client_info4", client_info.getText().toString());
                            intent.putExtra("client_pay4", client_pay.getText().toString());
                            intent.putExtra("client_email4", client_email.getText().toString());


                            String id = client_name.getText().toString();
                            String surname = client_surname.getText().toString();
                            String name = client_name.getText().toString();
                            String patronymic = client_patronymic.getText().toString();
                            String number = client_number.getText().toString();
                            String datestart = client_datestart.getText().toString();
                            String dateend = client_dateend.getText().toString();
                            String pay = client_pay.getText().toString();
                            String zastava = client_info.getText().toString();
                            String email = client_email.getText().toString();
                            String user = sharedPreferences.getString("auth","");
                            String busyness = "0";


                            ClientClass newClient = new ClientClass(id, surname, name, patronymic, number, email, datestart, dateend, pay, zastava,busyness,user);
                            myDataBase.push().setValue(newClient);

                            intent.putExtra("44", 44);
                            setResult(RESULT_OK, intent);


                            finish();

                            Toast.makeText(ActivityNewClient.this, "Орендаря додано!", Toast.LENGTH_LONG).show();

                        } else {

                        }
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
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