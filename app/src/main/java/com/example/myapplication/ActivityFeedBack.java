package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.feedback.MailAPI;

public class ActivityFeedBack extends AppCompatActivity implements View.OnClickListener {

    EditText feedback_text;
    Button send_feedback;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        getSupportActionBar().hide();

        feedback_text = findViewById(R.id.feedback_text);

        send_feedback = findViewById(R.id.send_feedback);
        send_feedback.setOnClickListener(this);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        send_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                senEmail();
                Toast.makeText(ActivityFeedBack.this, "Відправлено!", Toast.LENGTH_LONG).show();
                finish();
            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void senEmail() {
        String mEmail = "apartmentapp01@gmail.com";
        String mSubject = "FEEDBACK";
        String mMessage = feedback_text.getText().toString();


        MailAPI javaMailAPI = new MailAPI(this, mEmail, mSubject, mMessage);

        javaMailAPI.execute();
    }

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
}