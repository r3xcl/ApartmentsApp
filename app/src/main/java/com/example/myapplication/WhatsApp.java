package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WhatsApp extends AppCompatActivity  implements View.OnClickListener{

    EditText whatsapp_text;
    Button send_whatsapp;
    TextView num_whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);
        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ


        whatsapp_text  = (EditText) findViewById(R.id.whatsapp_text);
        num_whatsapp  = (TextView) findViewById(R.id.num_whatsapp);

        send_whatsapp = (Button) findViewById(R.id.send_whatsapp);
        send_whatsapp.setOnClickListener(this);

            String number = getIntent().getStringExtra("number");
           num_whatsapp.setText(number);

        send_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = num_whatsapp.getText().toString();
                String message = whatsapp_text.getText().toString();

                boolean installed = appIntalledOrNot("com.whatsapp");

                if(installed){

                    Intent intent  = new Intent (Intent.ACTION_VIEW);

                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+38"+number+"&text="+message));
                    startActivity(intent);

                }else{

                    Toast.makeText(WhatsApp.this,"Встановіть Whats App",Toast.LENGTH_SHORT).show();

                }

                finish();

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

