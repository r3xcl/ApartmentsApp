package com.example.myapplication.message;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.airbnb.lottie.LottieAnimationView;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class MessageIntegration extends AppCompatActivity  implements View.OnClickListener{

    EditText whatsapp_text;
    Button send_message,send_email,send_all;
    TextView num_whatsapp,email,textView45,textView44;

    String user= "apartmentapp01@gmail.com";
    String password = "appapartment";
    String sub = "";

    String recipient,textmes;

    GmailSender sender;


    boolean isCheckedWhatsApp = false;
    boolean isCheckedEmail = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_integration);
        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ


        whatsapp_text  = (EditText) findViewById(R.id.whatsapp_text);
        num_whatsapp  = (TextView) findViewById(R.id.num_whatsapp);
        email  = (TextView) findViewById(R.id.email);
        textView45  = (TextView) findViewById(R.id.textView45);
        textView44  = (TextView) findViewById(R.id.textView44);

        send_message = (Button) findViewById(R.id.send_whatsapp);
        send_message.setOnClickListener(this);

        send_email = (Button) findViewById(R.id.send_email);
        send_email.setOnClickListener(this);

        send_all = (Button) findViewById(R.id.send_all);
        send_all.setOnClickListener(this);

        LottieAnimationView lottieCheckWhats = findViewById(R.id.check_whatsapp);
        LottieAnimationView lottieCheckEmail = findViewById(R.id.check_email);


        sender = new GmailSender(user, password);

        String number = getIntent().getStringExtra("number");
        num_whatsapp.setText(number);

        String email1 = getIntent().getStringExtra("email");
        email.setText(email1);





        lottieCheckWhats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheckedWhatsApp){


                    lottieCheckWhats.setSpeed(-2);
                    lottieCheckWhats.playAnimation();
                    isCheckedWhatsApp = false;

                    send_message.setVisibility(View.INVISIBLE);


                }else {

                    lottieCheckWhats.setSpeed(2);
                    lottieCheckWhats.playAnimation();
                    isCheckedWhatsApp=true;

                    send_message.setVisibility(View.VISIBLE);


                }

                if(isCheckedWhatsApp && isCheckedEmail){

                    send_all.setVisibility(View.VISIBLE);

                }else {




                    send_all.setVisibility(View.INVISIBLE);

                }
            }
        });

        lottieCheckEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isCheckedEmail){

                    lottieCheckEmail.setSpeed(-2);
                    lottieCheckEmail.playAnimation();
                    isCheckedEmail = false;

                    send_email.setVisibility(View.INVISIBLE);


                }else {

                    lottieCheckEmail.setSpeed(2);
                    lottieCheckEmail.playAnimation();
                    isCheckedEmail=true;

                    send_email.setVisibility(View.VISIBLE);


                }

                if(isCheckedWhatsApp && isCheckedEmail){
                    send_all.setVisibility(View.VISIBLE);


                    send_all.setVisibility(View.VISIBLE);

                }else {


                    send_all.setVisibility(View.INVISIBLE);

                }
            }
        });






            send_message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(num_whatsapp.getText().toString().isEmpty()){

                        Toast.makeText(MessageIntegration.this,"Номер телефону не знайдено! Додайте у списку орендарів!",Toast.LENGTH_LONG).show();

                    }else {

                        String number = num_whatsapp.getText().toString();
                        String message = whatsapp_text.getText().toString();

                        boolean installed = appIntalledOrNot("com.whatsapp");

                        if (installed) {

                            Intent intent = new Intent(Intent.ACTION_VIEW);

                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+38" + number + "&text=" + message));
                            startActivity(intent);

                        } else {

                            Toast.makeText(MessageIntegration.this, "Помилка відправлення повідомлення." +
                                    " Застосунок WhatsApp не знайдено на Вашому пристрої", Toast.LENGTH_LONG).show();

                        }

                        finish();
                    }

                }
            });


            send_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(num_whatsapp.getText().toString().isEmpty()){

                        Toast.makeText(MessageIntegration.this,"Номер телефону не знайдено! Додайте у списку орендарів!",Toast.LENGTH_LONG).show();

                    }if(email.getText().toString().isEmpty()){


                        Toast.makeText(MessageIntegration.this,"Пошта не знайдена! Додайте у списку орендарів!",Toast.LENGTH_LONG).show();

                    }

                    else {

                        String number = num_whatsapp.getText().toString();
                        String message = whatsapp_text.getText().toString();

                        boolean installed = appIntalledOrNot("com.whatsapp");

                        if (installed) {

                            Intent intent = new Intent(Intent.ACTION_VIEW);

                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + "+38" + number + "&text=" + message));
                            startActivity(intent);

                            textmes = whatsapp_text.getText().toString();
                            recipient = email.getText().toString();

                            new MyAsyncClass().execute();

                        } else {

                            Toast.makeText(MessageIntegration.this, "Помилка відправлення повідомлення." +
                                    " Застосунок WhatsApp не знайдено на Вашому пристрої", Toast.LENGTH_LONG).show();

                        }

                    }
                }
            });



            send_email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(email.getText().toString().isEmpty()) {

                        Toast.makeText(MessageIntegration.this, "Пошта не знайдена! Додайте у списку орендарів!", Toast.LENGTH_LONG).show();

                    }else {

                        textmes = whatsapp_text.getText().toString();
                        recipient = email.getText().toString();

                        new MyAsyncClass().execute();
                    }


                }

            });




    }



    private boolean appIntalledOrNot(String s) {

        PackageManager packageManager = getPackageManager();
        boolean app_installed ;
        try {

            packageManager.getPackageInfo(s,PackageManager.GET_ACTIVITIES);
            app_installed = true;

        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;

    }



    class MyAsyncClass extends AsyncTask<Void, Void, Void> {

        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(MessageIntegration.this);
            pDialog.setMessage("Відправляється!");
            pDialog.show();

        }

        @Override

        protected Void doInBackground(Void... mApi) {
            try {

                sender.sendMail( sub,textmes, user, recipient);
                Log.d("send", "done");
            }
            catch (Exception ex) {
                Log.d("exceptionsending", ex.toString());
            }
            return null;
        }

        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            Toast.makeText(MessageIntegration.this, "Відправлено!", Toast.LENGTH_SHORT).show();

            finish();

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

    @Override
    public void onClick(View v) {

    }


    // <---
}

