package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import db.Regi_Urers;

public class Auth_Activity extends AppCompatActivity implements View.OnClickListener {

    EditText user_email, user_pass,regi_mail,regi_pass,res_mail;
    TextView Forgot,Registr;
    Button button_vhod;
    ProgressBar progressBar;

    private FirebaseAuth mAuth;
    FirebaseDatabase db;
    DatabaseReference reg_users;
    private FirebaseAuth.AuthStateListener mAuthListener;

    ConstraintLayout auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_);

        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ

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

        Forgot = (TextView) findViewById(R.id.Forgot);
        Forgot.setOnClickListener(this);


        Registr = (TextView)findViewById(R.id.Registr) ;
        Registr.setOnClickListener(this);



        findViewById(R.id.button_vhod).setOnClickListener(this);


        auth = findViewById(R.id.auth);

       progressBar = (ProgressBar) findViewById(R.id.progressBar);


    }

    private void showRegWindow() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Реєстрація");
        dialog.setMessage("Введіть дані для реєстрації!");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_wind = inflater.inflate(R.layout.register_wind,null);
        dialog.setView(register_wind);

         regi_mail = register_wind.findViewById(R.id.regi_mail);
         regi_pass = register_wind.findViewById(R.id.regi_pass);

         dialog.setNegativeButton("Відмінити", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });

         dialog.setPositiveButton("Зареєструватися", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {

                 String email = regi_mail.getText().toString().trim();
                 String password = regi_pass.getText().toString().trim();

              if(TextUtils.isEmpty(email)){

                  Toast.makeText(Auth_Activity.this, "Введіть пошту!", Toast.LENGTH_SHORT).show();
                  return;
              }

                 if(TextUtils.isEmpty(password)){


                     Toast.makeText(Auth_Activity.this, "Введіть пароль!", Toast.LENGTH_SHORT).show();

                     return;
                 }

                 if(password.length()<6){

                     Toast.makeText(Auth_Activity.this, "Пароль менше 6 символів!", Toast.LENGTH_SHORT).show();
                     return;
                 }





                 mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()) {
                             Toast.makeText(Auth_Activity.this, "Реєстрація успішна!", Toast.LENGTH_SHORT).show();


                         }else
                             Toast.makeText(Auth_Activity.this, "Помилка, користувач не зареєстрований! "+task.getException().getMessage()
                                     , Toast.LENGTH_LONG).show();
                     }
                 });



             }

         });
         dialog.show();


    }

    public void ForgotShow() {

        AlertDialog.Builder ResetPass = new AlertDialog.Builder(this);
        ResetPass.setTitle("Відновити пароль?");
        ResetPass.setMessage("Введіть свою електронну пошту.");

        LayoutInflater inflater = LayoutInflater.from(this);
        View resit_mail = inflater.inflate(R.layout.resit_mail,null);
        ResetPass.setView(resit_mail);

        res_mail = resit_mail.findViewById(R.id.res_mail);




        ResetPass.setPositiveButton("Так", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String email = res_mail.getText().toString().trim();

                if(TextUtils.isEmpty(email)){

                    Toast.makeText(Auth_Activity.this, "Введіть пошту!", Toast.LENGTH_SHORT).show();
                    return;
                }

                String mail = res_mail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Auth_Activity.this, "Посилання для зміни пароля відправлено!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Auth_Activity.this, "Помилка!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        ResetPass.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ResetPass.show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_vhod && user_pass.getText().toString().length()!=0
                && user_email.getText().toString().length()!=0 ){
            signin(user_email.getText().toString(), user_pass.getText().toString());

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
                Toast.makeText(Auth_Activity.this, "Авторизація успішна!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Auth_Activity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

                progressBar.setVisibility(View.VISIBLE);
            }else
                Toast.makeText(Auth_Activity.this, "Дані не вірні! ", Toast.LENGTH_SHORT).show();

        });
    }
}