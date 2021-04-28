package com.example.myapart;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapart.fragments.fragments1.Fragment_Pay1;
import com.example.myapart.fragments.fragments1.Fragment_Repair1;
import com.example.myapart.fragments.fragments2.Fragment_Pay2;
import com.example.myapart.fragments.fragments2.Fragment_Repair2;
import com.example.myapart.fragments.fragments3.Fragment_Pay3;
import com.example.myapart.fragments.fragments3.Fragment_Repair3;
import com.example.myapart.fragments.fragments4.Fragment_Pay4;
import com.example.myapart.fragments.fragments4.Fragment_Repair4;
import com.example.myapplication.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import db.Pay.PayClass;
import db.Repair.RepairClass;

public class ActivityArchive extends AppCompatActivity implements View.OnClickListener{

    ImageButton fragment2,fragment1;
    Fragment fragment = null;
    FloatingActionButton faddrepair,faddmoney;
    TextView amount_pay,amount_repair,amountall;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        amount_pay = (TextView) findViewById(R.id.amount_pay);
        amount_repair = (TextView) findViewById(R.id.amount_repair);
        amountall = (TextView) findViewById(R.id.amount);

        amount_repair.setText(""+0 + " Грн.");
        amount_pay.setText(""+0+ " Грн.");
        amountall.setText(""+0+ " Грн.");

        getSupportActionBar().hide();

        faddmoney = (FloatingActionButton)findViewById(R.id.faddmoney);
        faddmoney.setOnClickListener(this);

        faddrepair = (FloatingActionButton)findViewById(R.id.faddrepair);
        faddrepair.setOnClickListener(this);

        fragment2 = (ImageButton)findViewById(R.id.fragment2);
        fragment2.setOnClickListener(this);

        fragment1 = (ImageButton)findViewById(R.id.fragment1);
        fragment1.setOnClickListener(this);

        Intent intent = getIntent();
        String action = intent.getAction();

        fragment1.setVisibility(View.INVISIBLE);


        if (action.equals("archive1")) {


            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

            Query query = reference.child("Pay_History").child("apartment1").orderByChild("id").equalTo("apartment1");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            PayClass payClass = snapShot.getValue(PayClass.class);
                            int amount = Integer.parseInt(payClass.getPay());
                            sum = sum + amount;

                            amount_pay.setText(""+sum);


                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }


                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            amountall.setText(""+(p)+ " Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Query query2 = reference.child("Repairs_History").child("apartment1").orderByChild("id").equalTo("apartment1");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            RepairClass repairClass = snapShot.getValue(RepairClass.class);
                            int amount = Integer.parseInt(repairClass.getSum());
                            sum = sum + amount;

                            amount_repair.setText(""+sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }

                            if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_repair.setText(""+0);

                            }

                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                            amountall.setText(""+(p-r) + " Грн.");
                            amount_repair.setText(r+" Грн.");
                            amount_pay.setText(p+" Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay1()).commit();

              fragment2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair1()).commit();

                      fragment2.setVisibility(View.INVISIBLE);
                      fragment1.setVisibility(View.VISIBLE);
                  }
              });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay1()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });

            faddmoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent("pay1");
                    startActivityForResult(intent1,11111);
                }
            });

            faddrepair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent("remont1");
                    startActivityForResult(intent4,101);
                }
            });
        }

        if (action.equals("archive2")) {


            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

            Query query = reference.child("Pay_History").child("apartment2").orderByChild("id").equalTo("apartment2");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            PayClass payClass = snapShot.getValue(PayClass.class);
                            int amount = Integer.parseInt(payClass.getPay());
                            sum = sum + amount;

                            amount_pay.setText(""+sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }


                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            amountall.setText(""+(p)+" Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Query query2 = reference.child("Repairs_History").child("apartment2").orderByChild("id").equalTo("apartment2");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            RepairClass repairClass = snapShot.getValue(RepairClass.class);
                            int amount = Integer.parseInt(repairClass.getSum());
                            sum = sum + amount;

                            amount_repair.setText("" + sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }

                            if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_repair.setText(""+0);

                            }

                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                            amountall.setText(""+(p-r) + " Грн.");
                            amount_repair.setText(r+" Грн.");
                            amount_pay.setText(p+" Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay2()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair2()).commit();
                    fragment2.setVisibility(View.INVISIBLE);
                    fragment1.setVisibility(View.VISIBLE);
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay2()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });

            faddmoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent("pay2");
                    startActivityForResult(intent1,22222);
                }
            });

            faddrepair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent("remont2");
                    startActivityForResult(intent4,202);
                }
            });
        }

        if (action.equals("archive3")) {

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

            Query query = reference.child("Pay_History").child("apartment3").orderByChild("id").equalTo("apartment3");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            PayClass payClass = snapShot.getValue(PayClass.class);
                            int amount = Integer.parseInt(payClass.getPay());
                            sum = sum + amount;

                            amount_pay.setText(""+sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }


                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            amountall.setText(""+(p) + " Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Query query2 = reference.child("Repairs_History").child("apartment3").orderByChild("id").equalTo("apartment3");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            RepairClass repairClass = snapShot.getValue(RepairClass.class);
                            int amount = Integer.parseInt(repairClass.getSum());
                            sum = sum + amount;

                            amount_repair.setText(""+sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }

                            if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_repair.setText(""+0);

                            }

                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                            amountall.setText(""+(p-r) + " Грн.");
                            amount_repair.setText(r+" Грн.");
                            amount_pay.setText(p+" Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay3()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair3()).commit();
                    fragment2.setVisibility(View.INVISIBLE);
                    fragment1.setVisibility(View.VISIBLE);
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay3()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });

            faddmoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent("pay3");
                    startActivityForResult(intent1,33333);
                }
            });

            faddrepair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent("remont3");
                    startActivityForResult(intent4,303);
                }
            });
        }

        if (action.equals("archive4")) {


            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

            Query query = reference.child("Pay_History").child("apartment4").orderByChild("id").equalTo("apartment4");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            PayClass payClass = snapShot.getValue(PayClass.class);
                            int amount = Integer.parseInt(payClass.getPay());
                            sum = sum + amount;

                            amount_pay.setText(""+sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }


                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            amountall.setText(""+(p) + " Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            Query query2 = reference.child("Repairs_History").child("apartment4").orderByChild("id").equalTo("apartment4");
            query2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        int sum = 0;
                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                            RepairClass repairClass = snapShot.getValue(RepairClass.class);
                            int amount = Integer.parseInt(repairClass.getSum());
                            sum = sum + amount;

                            amount_repair.setText(""+sum);

                            if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_pay.setText(""+0);

                            }

                            if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                amount_repair.setText(""+0);

                            }

                            int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                            int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                            amountall.setText(""+(p-r) + " Грн.");
                            amount_repair.setText(r+" Грн.");
                            amount_pay.setText(p+" Грн.");
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay4()).commit();

            fragment2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Repair4()).commit();
                    fragment2.setVisibility(View.INVISIBLE);
                    fragment1.setVisibility(View.VISIBLE);
                }
            });

            fragment1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.archive,new Fragment_Pay4()).commit();
                    fragment1.setVisibility(View.INVISIBLE);
                    fragment2.setVisibility(View.VISIBLE);
                }
            });

            faddmoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent("pay4");
                    startActivityForResult(intent1,44444);
                }
            });

            faddrepair.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent4 = new Intent("remont4");
                    startActivityForResult(intent4,404);
                }
            });
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        if (requestCode == 11111) {

            if (resultCode == RESULT_OK) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment1").orderByChild("id").equalTo("apartment1");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText("" + sum);


                                if (amount_pay.getText().toString().replaceAll("[^0-9?!]", "") == "") {

                                    amount_pay.setText("" + 0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]", ""));
                                amountall.setText("" + (p) + " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment1").orderByChild("id").equalTo("apartment1");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText("" + sum);

                                if (amount_pay.getText().toString().replaceAll("[^0-9?!]", "") == "") {

                                    amount_pay.setText("" + 0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]", "") == "") {

                                    amount_repair.setText("" + 0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]", ""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]", ""));

                                amountall.setText("" + (p - r) + " Грн.");
                                amount_repair.setText(r + " Грн.");
                                amount_pay.setText(p + " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

        }


        if (requestCode == 22222) {

            if (resultCode == RESULT_OK) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment2").orderByChild("id").equalTo("apartment2");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p)+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment2").orderByChild("id").equalTo("apartment2");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText("" + sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        if (requestCode == 33333) {

            if (resultCode == RESULT_OK) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment3").orderByChild("id").equalTo("apartment3");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p) + " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment3").orderByChild("id").equalTo("apartment3");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        if (requestCode == 44444) {

            if (resultCode == RESULT_OK) {


                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment4").orderByChild("id").equalTo("apartment4");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p) + " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment4").orderByChild("id").equalTo("apartment4");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        if (requestCode == 101) {

            if (resultCode == RESULT_OK) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment1").orderByChild("id").equalTo("apartment1");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);


                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p)+ " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment1").orderByChild("id").equalTo("apartment1");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        if (requestCode == 202) {

            if (resultCode == RESULT_OK) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment2").orderByChild("id").equalTo("apartment2");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p)+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment2").orderByChild("id").equalTo("apartment2");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText("" + sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        if (requestCode == 303) {

            if (resultCode == RESULT_OK) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment3").orderByChild("id").equalTo("apartment3");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p) + " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment3").orderByChild("id").equalTo("apartment3");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

        if (requestCode == 404) {

            if (resultCode == RESULT_OK) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference(auth);

                Query query = reference.child("Pay_History").child("apartment4").orderByChild("id").equalTo("apartment4");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                PayClass payClass = snapShot.getValue(PayClass.class);
                                int amount = Integer.parseInt(payClass.getPay());
                                sum = sum + amount;

                                amount_pay.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }


                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                amountall.setText(""+(p) + " Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Query query2 = reference.child("Repairs_History").child("apartment4").orderByChild("id").equalTo("apartment4");
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            int sum = 0;
                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                                RepairClass repairClass = snapShot.getValue(RepairClass.class);
                                int amount = Integer.parseInt(repairClass.getSum());
                                sum = sum + amount;

                                amount_repair.setText(""+sum);

                                if(amount_pay.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_pay.setText(""+0);

                                }

                                if (amount_repair.getText().toString().replaceAll("[^0-9?!]","")==""){

                                    amount_repair.setText(""+0);

                                }

                                int p = Integer.parseInt(amount_pay.getText().toString().replaceAll("[^0-9?!]",""));
                                int r = Integer.parseInt(amount_repair.getText().toString().replaceAll("[^0-9?!]",""));

                                amountall.setText(""+(p-r) + " Грн.");
                                amount_repair.setText(r+" Грн.");
                                amount_pay.setText(p+" Грн.");
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }

    }

    @Override
    public void onClick(View v) {


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