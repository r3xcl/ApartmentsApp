package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class  MainActivity extends AppCompatActivity  implements View.OnClickListener{

    SharedPreferences sharedPreferences;

    ImageButton add1,add2,add3,add4,
            new_home,new_home2,new_home3,new_home4,
            allclient,bloknot;

    TextView add1_text,add2_text,add3_text,add4_text;

    Vibrator vibr;

    private DatabaseReference myDataBase;

    private String New_Apartment = "New_Apartment";


    private AlphaAnimation buttonClick = new AlphaAnimation(2F, 0.3F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDataBase = FirebaseDatabase.getInstance().getReference(New_Apartment);

        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        add1_text = (TextView) findViewById(R.id.add1_text);
        add2_text = (TextView) findViewById(R.id.add2_text);
        add3_text = (TextView) findViewById(R.id.add3_text);
        add4_text = (TextView) findViewById(R.id.add4_text);

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        String address1 = sharedPreferences.getString("address1","");
        add1_text.setText(address1);

        String address2 = sharedPreferences.getString("address2","");
        add2_text.setText(address2);

        String address3 = sharedPreferences.getString("address3","");
        add3_text.setText(address3);

        String address4 = sharedPreferences.getString("address4","");
        add4_text.setText(address4);

        add1 = (ImageButton) findViewById(R.id.add1);
        add1.setOnClickListener(this);

        if(add1_text.length()>0){

            add1.setVisibility(View.INVISIBLE);

        }else {
            add1.setVisibility(View.VISIBLE);
        }

        add2 = (ImageButton) findViewById(R.id.add2);
        add2.setOnClickListener(this);

        if(add2_text.length()>0){

            add2.setVisibility(View.INVISIBLE);

        }else {
            add2.setVisibility(View.VISIBLE);
        }

        add3 = (ImageButton) findViewById(R.id.add3);
        add3.setOnClickListener(this);

        if(add3_text.length()>0){

            add3.setVisibility(View.INVISIBLE);

        }else {
            add3.setVisibility(View.VISIBLE);
        }

        add4 = (ImageButton) findViewById(R.id.add4);
        add4.setOnClickListener(this);

        if(add4_text.length()>0){

            add4.setVisibility(View.INVISIBLE);

        }else {
            add4.setVisibility(View.VISIBLE);
        }


        new_home = (ImageButton) findViewById(R.id.new_home);
        new_home.setOnClickListener(this);

        new_home2 = (ImageButton) findViewById(R.id.new_home2);
        new_home2.setOnClickListener(this);

        new_home3 = (ImageButton) findViewById(R.id.new_home3);
        new_home3.setOnClickListener(this);

        new_home4 = (ImageButton) findViewById(R.id.new_home4);
        new_home4.setOnClickListener(this);


        bloknot = (ImageButton) findViewById(R.id.bloknot);
        bloknot.setOnClickListener(this);


        allclient = (ImageButton) findViewById(R.id.allclient);
        allclient.setOnClickListener(this);


        vibr = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("1");
                startActivityForResult(intent,1);
                Animatoo.animateSlideRight(MainActivity.this);


            }
        });


        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("2");
                startActivityForResult(intent,2);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("3");
                startActivityForResult(intent,3);
                Animatoo.animateSlideRight(MainActivity.this);

            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("4");
                startActivityForResult(intent,4);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        new_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("new_home1");

                startActivity(intent);
                Animatoo.animateSlideRight(MainActivity.this);

            }
        });

        new_home.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vibr.vibrate(70);
                v.startAnimation(buttonClick);
                createOneDialog("Оберіть дію");
                return false;
            }

            private void createOneDialog(String title) {

                AlertDialog alertDialog;

                alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setCancelable(true)
                        .setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add1.setVisibility(View.VISIBLE);
                                add1_text.setText("");

                                String address1 = add1_text.getText().toString();

                                String client_name = "";
                                String client_surname = "";
                                String client_patronymic = "";
                                String client_number = "";
                                String client_email = "";
                                String client_datestart = "";
                                String client_dateend = "";
                                String client_pay = "";
                                String client_zastava = "";

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("client_name1",client_name);
                                editor.putString("client_surname1",client_surname);
                                editor.putString("client_patronymic1",client_patronymic);
                                editor.putString("client_number1",client_number);
                                editor.putString("client_datestart1",client_datestart);
                                editor.putString("client_dateend1",client_dateend);
                                editor.putString("client_pay1",client_pay);
                                editor.putString("client_zastava1",client_zastava);
                                editor.putString("client_email1",client_email);
                                editor.putString("address1",address1);

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                SharedPreferences.Editor editor1 = preferences.edit();
                                editor1.putString("image1",null);
                                editor1.commit();

                                editor.apply();

                                add1_text.setText(address1);
                            }
                        })
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED);


            }
        });

        new_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("new_home2");


                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        new_home2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vibr.vibrate(70);
                v.startAnimation(buttonClick);
                createOneDialog("Оберіть дію");
                return false;

            }

            private void createOneDialog(String title) {
                AlertDialog alertDialog;

                alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setCancelable(true)
                        .setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add2.setVisibility(View.VISIBLE);
                                add2_text.setText("");

                                String address2 = add2_text.getText().toString();

                                String client_name = "";
                                String client_surname = "";
                                String client_patronymic = "";
                                String client_number = "";
                                String client_datestart = "";
                                String client_dateend = "";
                                String client_pay = "";
                                String client_zastava = "";
                                String client_email = "";

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("client_name2",client_name);
                                editor.putString("client_surname2",client_surname);
                                editor.putString("client_patronymic2",client_patronymic);
                                editor.putString("client_number2",client_number);
                                editor.putString("client_datestart2",client_datestart);
                                editor.putString("client_dateend2",client_dateend);
                                editor.putString("client_pay2",client_pay);
                                editor.putString("client_zastava2",client_zastava);
                                editor.putString("address2",address2);
                                editor.putString("client_email2",client_email);

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                SharedPreferences.Editor editor1 = preferences.edit();
                                editor1.putString("image2",null);
                                editor1.commit();

                                editor.apply();

                                add2_text.setText(address2);
                            }
                        })
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED);
            }
        });

        new_home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("new_home3");


                startActivity(intent);
                Animatoo.animateSlideRight(MainActivity.this);

            }
        });

        new_home3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vibr.vibrate(70);
                v.startAnimation(buttonClick);
                createOneDialog("Оберіть дію");
                return false;
            }

            private void createOneDialog(String title) {
                AlertDialog alertDialog;

                alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setCancelable(true)
                        .setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add3.setVisibility(View.VISIBLE);
                                add3_text.setText("");

                                String address3 = add3_text.getText().toString();

                                String client_name = "";
                                String client_surname = "";
                                String client_patronymic = "";
                                String client_number = "";
                                String client_datestart = "";
                                String client_dateend = "";
                                String client_email = "";
                                String client_pay = "";
                                String client_zastava = "";

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("client_name3",client_name);
                                editor.putString("client_surname3",client_surname);
                                editor.putString("client_patronymic3",client_patronymic);
                                editor.putString("client_number3",client_number);
                                editor.putString("client_datestart3",client_datestart);
                                editor.putString("client_dateend3",client_dateend);
                                editor.putString("client_pay3",client_pay);
                                editor.putString("client_zastava3",client_zastava);
                                editor.putString("client_email3",client_email);
                                editor.putString("address3",address3);

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                SharedPreferences.Editor editor1 = preferences.edit();
                                editor1.putString("image3",null);
                                editor1.commit();

                                editor.apply();

                                add3_text.setText(address3);
                            }
                        })
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED);
            }
        });

        new_home4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("new_home4");


                startActivity(intent);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        new_home4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                vibr.vibrate(70);
                v.startAnimation(buttonClick);
                createOneDialog("Оберіть дію");
                return false;
            }

            private void createOneDialog(String title) {
                AlertDialog alertDialog;

                alertDialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle(title)
                        .setCancelable(true)
                        .setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add4.setVisibility(View.VISIBLE);
                                add4_text.setText("");

                                String address4 = add4_text.getText().toString();

                                String client_name = "";
                                String client_surname = "";
                                String client_patronymic = "";
                                String client_number = "";
                                String client_datestart = "";
                                String client_dateend = "";
                                String client_pay = "";
                                String client_email = "";
                                String client_zastava = "";

                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                editor.putString("client_name4",client_name);
                                editor.putString("client_surname4",client_surname);
                                editor.putString("client_patronymic4",client_patronymic);
                                editor.putString("client_number4",client_number);
                                editor.putString("client_datestart4",client_datestart);
                                editor.putString("client_dateend4",client_dateend);
                                editor.putString("client_pay4",client_pay);
                                editor.putString("client_zastava4",client_zastava);
                                editor.putString("client_email4",client_email);
                                editor.putString("address4",address4);

                                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                                SharedPreferences.Editor editor1 = preferences.edit();
                                editor1.putString("image4",null);
                                editor1.commit();

                                editor.apply();

                                add4_text.setText(address4);
                            }
                        })
                        .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED);
            }
        });



        allclient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("allclient");
                startActivityForResult(intent,66);
                Animatoo.animateSlideLeft(MainActivity.this);

            }
        });

        bloknot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("bloknot");
                startActivityForResult(intent,77);
                Animatoo.animateSlideRight(MainActivity.this);

            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode==RESULT_OK){

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment1");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot data : snapshot.getChildren()){

                            String address = data.child("address").getValue().toString();
                            String dateown = data.child("dateown").getValue().toString();
                            String floor = data.child("floor").getValue().toString();
                            String rooms = data.child("rooms").getValue().toString();


                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("address1",address);
                            editor.apply();

                            add1_text.setText(address);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





                add1.setVisibility(View.GONE);

                Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
                toast.show();}
        }else if (requestCode == 2){
            if (resultCode==RESULT_OK){

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment2");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot data : snapshot.getChildren()){

                            String address = data.child("address").getValue().toString();
                            String dateown = data.child("dateown").getValue().toString();
                            String floor = data.child("floor").getValue().toString();
                            String rooms = data.child("rooms").getValue().toString();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("address2",address);
                            editor.apply();


                            add2_text.setText(address);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                add2.setVisibility(View.GONE);

                Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
                toast.show();}
        }else if (requestCode == 3){
            if (resultCode==RESULT_OK){

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment3");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot data : snapshot.getChildren()){

                            String address = data.child("address").getValue().toString();
                            String dateown = data.child("dateown").getValue().toString();
                            String floor = data.child("floor").getValue().toString();
                            String rooms = data.child("rooms").getValue().toString();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("address3",address);
                            editor.apply();


                            add3_text.setText(address);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                add3.setVisibility(View.GONE);

                Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
                toast.show();}
        }else if (requestCode == 4){
            if (resultCode==RESULT_OK){

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment4");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot data : snapshot.getChildren()){

                            String address = data.child("address").getValue().toString();
                            String dateown = data.child("dateown").getValue().toString();
                            String floor = data.child("floor").getValue().toString();
                            String rooms = data.child("rooms").getValue().toString();

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("address4",address);
                            editor.apply();


                            add4_text.setText(address);

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                add4.setVisibility(View.GONE);

                Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
                toast.show();}

        }


    }






    @Override
    public void onClick(View v){
        Intent intent;





    }

    public void onClickRead(View v){

        Intent i = new Intent(MainActivity.this, ActivityAllClients.class);
        startActivity(i);




    }

    public void onClickBloknot(View v){

        Intent i = new Intent(MainActivity.this, ActivityNote.class);
        startActivity(i);




    }
}
