package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    ImageButton add1,add2,add3,add4,add5,
            new_home,new_home2,new_home3,new_home4,
            allclient,bloknot;

    TextView add1_text,add2_text,add3_text,add4_text,
            rooms1,floor1,dateown1,
            rooms2,floor2,dateown2,
            rooms3,floor3,dateown3,
            rooms4,floor4,dateown4;

    private FusedLocationProviderClient fusedLocationProviderClient;
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
        add4_text = findViewById(R.id.add4_text);








        add1 = (ImageButton) findViewById(R.id.add1);
        add1.setOnClickListener(this);

        add2 = (ImageButton) findViewById(R.id.add2);
        add2.setOnClickListener(this);

        add3 = (ImageButton) findViewById(R.id.add3);
        add3.setOnClickListener(this);

        add4 = (ImageButton) findViewById(R.id.add4);
        add4.setOnClickListener(this);



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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title);



                builder.setPositiveButton("Видалити",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add1.setVisibility(View.VISIBLE);
                                add1_text.setText("");



                            }
                        });
                builder.setNeutralButton("Назад",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title);



                builder.setPositiveButton("Видалити",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add2.setVisibility(View.VISIBLE);
                                add2_text.setText("");


                            }
                        });
                builder.setNeutralButton("Назад",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title);



                builder.setPositiveButton("Видалити",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add3.setVisibility(View.VISIBLE);
                                add3_text.setText("");



                            }
                        });
                builder.setNeutralButton("Назад",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(title);



                builder.setPositiveButton("Видалити",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                add4.setVisibility(View.VISIBLE);
                                add4_text.setText("");



                            }
                        });
                builder.setNeutralButton("Назад",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                builder.show();
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
