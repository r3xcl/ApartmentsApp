package com.example.myapart;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {

    ImageView iv_house,iv_beat;
    TextView splash_text;

    SharedPreferences sharedPreferences;

    CharSequence charSequence;

    int index;
    long delay = 100;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_);

        getSupportActionBar().hide();

        iv_house = (ImageView)findViewById(R.id.iv_house);
        iv_beat = (ImageView)findViewById(R.id.iv_beat);
        splash_text = (TextView) findViewById(R.id.splash_text);

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder
                (iv_house,
                        PropertyValuesHolder.ofFloat("scaleX",1.2f),
                 PropertyValuesHolder.ofFloat("scaleY",1.2f));

        objectAnimator1.setDuration(550);
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator1.setRepeatMode(ValueAnimator.REVERSE);

        objectAnimator1.start();

         animatText("МОЇ КВАРТИРИ");



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String authsave = sharedPreferences.getString("authsave","");

                if(authsave.equals("yes")){

                    startActivity(new Intent(SplashActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();

                }else {

                    startActivity(new Intent(SplashActivity.this, AuthActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }

            }
        },2500);


    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            splash_text.setText(charSequence.subSequence(0,index++));

            if (index <= charSequence.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };

    public void animatText(CharSequence cs){
        charSequence = cs;

        index = 0 ;

        splash_text.setText("");

        handler.removeCallbacks(runnable);

        handler.postDelayed(runnable,delay);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String authsave = sharedPreferences.getString("authsave","");

        if(authsave.equals("yes")){

            startActivity(new Intent(SplashActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }else {

            startActivity(new Intent(SplashActivity.this, AuthActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }






}