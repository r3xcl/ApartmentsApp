package com.example.myapart;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user_email, user_pass,regi_mail,regi_pass,res_mail,regi_passs;
    TextView Forgot,Registr;
    Button button_vhod;

    CheckBox checkBoxSave;
    ImageView show_pass,hide_pass;

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reg_users;
    SharedPreferences sharedPreferences;
    private FirebaseAuth.AuthStateListener mAuthListener;

    RelativeLayout auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_);

        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        reg_users = db.getReference("Reg_Users");

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null) {


            } else {

            }
        };

        user_email = (EditText) findViewById(R.id.regi_mail);
        user_pass = (EditText) findViewById(R.id.regi_pass);
        res_mail = (EditText) findViewById(R.id.res_mail);
        regi_passs = (EditText) findViewById(R.id.regi_passs);
        show_pass = (ImageView) findViewById(R.id.show_pass);
        hide_pass = (ImageView) findViewById(R.id.hide_pass);

        Forgot = (TextView) findViewById(R.id.Forgot);
        Forgot.setOnClickListener(this);


        Registr = (TextView)findViewById(R.id.Registr) ;
        Registr.setOnClickListener(this);

        findViewById(R.id.button_vhod).setOnClickListener(this);


        auth = findViewById(R.id.auth);



        checkBoxSave = findViewById(R.id.checkBoxSave);

        hide_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!regi_passs.getText().toString().isEmpty()){
                regi_passs.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hide_pass.setVisibility(View.INVISIBLE);
                    show_pass.setVisibility(View.VISIBLE);

                }
            }
        });

        show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!regi_passs.getText().toString().isEmpty()){

                    regi_passs.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    show_pass.setVisibility(View.INVISIBLE);
                    hide_pass.setVisibility(View.VISIBLE);

                }
            }
        });


    }

    private void showRegWindow() {

        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setTitle("Реєстрація");
        builder.setMessage("Введіть дані для реєстрації!");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_wind = inflater.inflate(R.layout.register_wind,null);
        builder.setView(register_wind);

         regi_mail = register_wind.findViewById(R.id.regi_mail);
         regi_pass = register_wind.findViewById(R.id.regi_pass);

        builder.setNegativeButton("Відмінити", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

        builder.setPositiveButton("Зареєструватися", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 String email = regi_mail.getText().toString().trim();
                 String password = regi_pass.getText().toString().trim();

              if(TextUtils.isEmpty(email)){

                  Toast.makeText(AuthActivity.this, "Введіть пошту!", Toast.LENGTH_SHORT).show();
                  return;
              }

                 if(TextUtils.isEmpty(password)){


                     Toast.makeText(AuthActivity.this, "Введіть пароль!", Toast.LENGTH_SHORT).show();

                     return;
                 }

                 if(password.length()<6){

                     Toast.makeText(AuthActivity.this, "Пароль менше 6 символів!", Toast.LENGTH_SHORT).show();
                     return;
                 }



                 mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()) {
                             Toast.makeText(AuthActivity.this, "Реєстрація успішна!", Toast.LENGTH_SHORT).show();


                         }else
                             Toast.makeText(AuthActivity.this, "Помилка, користувач не зареєстрований! "+task.getException().getMessage()
                                     , Toast.LENGTH_LONG).show();
                     }
                 });



             }

         });

         android.app.AlertDialog alertDialog = builder.create();
         alertDialog.show();

         Button color = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
         color.setTextColor(Color.RED);

         Button color1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
         color1.setTextColor(Color.RED);


    }

    public void ForgotShow() {

        android.app.AlertDialog.Builder builder=new android.app.AlertDialog.Builder(this);
        builder.setTitle("Відновити пароль?");
        builder.setMessage("Введіть свою електронну пошту.");

        LayoutInflater inflater = LayoutInflater.from(this);
        View resit_mail = inflater.inflate(R.layout.resit_mail,null);
        builder.setView(resit_mail);

        res_mail = resit_mail.findViewById(R.id.res_mail);




        builder.setPositiveButton("Так", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String email = res_mail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(AuthActivity.this, "Введіть пошту!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String mail = res_mail.getText().toString().trim();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AuthActivity.this, "Посилання для зміни пароля відправлено!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AuthActivity.this, "Помилка!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        builder.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        android.app.AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Button color = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        color.setTextColor(Color.RED);

        Button color1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        color1.setTextColor(Color.RED);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_vhod && regi_passs.getText().toString().length()!=0
                && user_email.getText().toString().length()!=0 ){
            signin(user_email.getText().toString().trim(), regi_passs.getText().toString().trim());

        }

        if(v.getId()==R.id.Registr){
            showRegWindow();
        }

        if(v.getId()==R.id.Forgot){
            ForgotShow();
        }




    }



    public void signin(String email , String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, task -> {

            if(task.isSuccessful()) {
                Toast.makeText(AuthActivity.this, "Авторизація успішна!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if(checkBoxSave.isChecked()){

                    editor.putString("authsave","yes");

                }

                editor.putString("auth",email);
                editor.apply();

                startActivity(new Intent(AuthActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();


            }else
                Toast.makeText(AuthActivity.this, "Дані не вірні! ", Toast.LENGTH_SHORT).show();

        });
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