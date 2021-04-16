package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.myapplication.message.MessageIntegration;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import db.Client.ClientClass;
import db.Pay.PayClass;

public class  ActivityApartmentManager extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPreferences;
    DatabaseReference reference;
    Uri ImageUri;



    String _Name;
    String _Number;

    Button add_client, delete_client, find_client;

    ImageButton apartment_edit, call,  addphoto, map, archive, whatsapp ,files,apartment_edit_client,back;

    TextView info_address, info_patronymic, info_name, info_surname, info_number,
            info_date_start, info_rooms, info_floor, info_dateown, info_date_end, info_pay
            , textView15, textView16, info_zastava
            , textView20, textView23,info_email,textView10,textView11,city_id,info_district,info_city,info_uid,info_size;



    ImageView imageView4, image_client , delphoto_client,info_home,imageshome;
    private FusedLocationProviderClient fusedLocationProviderClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_manager);
        getSupportActionBar().hide();




        info_address = (TextView) findViewById(R.id.info_address);
        city_id = (TextView) findViewById(R.id.city_id);
        info_district = (TextView) findViewById(R.id.info_district);
        info_patronymic = (TextView) findViewById(R.id.info_patronymic);
        info_name = (TextView) findViewById(R.id.info_name);
        info_surname = (TextView) findViewById(R.id.info_surname);
        info_number = (TextView) findViewById(R.id.info_number);
        info_date_start = (TextView) findViewById(R.id.info_date_start);
        info_date_end = (TextView) findViewById(R.id.info_date_end);
        info_pay = (TextView) findViewById(R.id.info_pay);
        info_zastava = (TextView) findViewById(R.id.info_zastava);
        info_size = (TextView) findViewById(R.id.info_size);
        info_email = (TextView) findViewById(R.id.info_email);
        info_uid = (TextView) findViewById(R.id.info_uid);
        back =  findViewById(R.id.back);


        textView23 = (TextView) findViewById(R.id.textView23);
        textView10 = (TextView) findViewById(R.id.textView10);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView16 = (TextView) findViewById(R.id.textView16);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView11 = (TextView) findViewById(R.id.textView11);

        imageView4 = (ImageView) findViewById(R.id.imageView4);

        image_client = (ImageView) findViewById(R.id.image_client);
        info_rooms = (TextView) findViewById(R.id.info_rooms);
        info_floor = (TextView) findViewById(R.id.info_floor);
        info_dateown = (TextView) findViewById(R.id.info_dateown);
        info_city = (TextView) findViewById(R.id.info_city);


        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);


        call = (ImageButton) findViewById(R.id.call);
        call.setOnClickListener(this);

        apartment_edit_client = (ImageButton) findViewById(R.id.apartment_edit_client);
        apartment_edit_client.setOnClickListener(this);

        files = (ImageButton) findViewById(R.id.files);
        files.setOnClickListener(this);

        imageshome = (ImageView) findViewById(R.id.imageshome);
        imageshome.setOnClickListener(this);

        delphoto_client = (ImageView) findViewById(R.id.delphoto_client);
        delphoto_client.setOnClickListener(this);

        info_home = (ImageView) findViewById(R.id.info_home);
        info_home.setOnClickListener(this);

        whatsapp = (ImageButton) findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(this);

        archive = (ImageButton) findViewById(R.id.archive);
        archive.setOnClickListener(this);


        map = (ImageButton) findViewById(R.id.map);
        map.setOnClickListener(this);

        addphoto = (ImageButton) findViewById(R.id.addphoto);
        addphoto.setOnClickListener(this);


        add_client = (Button) findViewById(R.id.add_client);
        add_client.setOnClickListener(this);

        find_client = (Button) findViewById(R.id.find_client);
        find_client.setOnClickListener(this);

        delete_client = (Button) findViewById(R.id.delete_client);
        delete_client.setOnClickListener(this);

        apartment_edit = (ImageButton) findViewById(R.id.apartment_edit);
        apartment_edit.setOnClickListener(this);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ActivityApartmentManager.this);

        if(ActivityCompat.checkSelfPermission (ActivityApartmentManager.this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ActivityApartmentManager.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)==
                        PackageManager.PERMISSION_GRANTED )
        {

            getCurrentLocation();}




        call.setOnClickListener(v -> {
            String number = info_number.getText().toString();
            if (!TextUtils.isEmpty(number)) {
                String dial = "tel: " + number;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            } else {
                Toast.makeText(ActivityApartmentManager.this, " Номер телефону не знайдено!", Toast.LENGTH_SHORT).show();
            }

        });


        Intent intent = getIntent();
        String action = intent.getAction();



        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityApartmentManager.this, MessageIntegration.class);
                intent.putExtra("number",info_number.getText().toString());
                intent.putExtra("email",info_email.getText().toString());
                startActivity(intent);
            }
        });


        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        if (action.equals("new_home1")) {


            imageshome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("showimage1");
                    startActivityForResult(intent1,129);
                }
            });

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
            Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment1");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot data : snapshot.getChildren()){

                        String address = data.child("address").getValue().toString();
                        String city = data.child("city").getValue().toString();
                        String district = data.child("district").getValue().toString();
                        String dateown = data.child("dateown").getValue().toString();
                        String floor = data.child("floor").getValue().toString();
                        String rooms = data.child("rooms").getValue().toString();
                        String size = data.child("size").getValue().toString();




                        info_address.setText(address);
                        info_city.setText(city+",");
                        info_district.setText(district);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);
                        info_size.setText(size);

                        if(city.equals("Київ")){

                            city_id.setText("Київ");

                        }




                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            Query query4 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart1");
            query4.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                            String dateend = snapShot.child("dateend").getValue().toString();
                            String datestart = snapShot.child("datestart").getValue().toString();
                            String email = snapShot.child("email").getValue().toString();
                            String name = snapShot.child("name").getValue().toString();
                            String number = snapShot.child("number").getValue().toString();
                            String patronymic = snapShot.child("patronymic").getValue().toString();
                            String pay = snapShot.child("pay").getValue().toString();
                            String surname = snapShot.child("surname").getValue().toString();
                            String zastava = snapShot.child("zastava").getValue().toString();
                            String uid = snapShot.getKey();


                            info_date_end.setText(dateend);
                            info_date_start.setText(datestart);
                            info_email.setText(email);
                            info_name.setText(name);
                            info_number.setText(number);
                            info_patronymic.setText(patronymic);
                            info_surname.setText(surname);
                            info_uid.setText(uid);

                            if(pay.equals("")){

                                info_pay.setText("    "+"0 Грн.");
                            }else {
                                info_pay.setText(pay + " Грн.");
                            }

                            if(zastava.equals("")){

                                info_zastava.setText("    "+"0 Грн.");

                            }else {
                                info_zastava.setText(zastava + " Грн.");
                            }

                            if(info_name.length()>0){

                                addphoto.setVisibility(View.VISIBLE);
                                info_pay.setVisibility(View.VISIBLE);
                                call.setVisibility(View.VISIBLE);
                                whatsapp.setVisibility(View.VISIBLE);
                                imageView4.setVisibility(View.VISIBLE);
                                apartment_edit_client.setVisibility(View.VISIBLE);
                                textView10.setVisibility(View.VISIBLE);
                                textView11.setVisibility(View.INVISIBLE);

                                info_surname.setVisibility(View.VISIBLE);

                                info_name.setVisibility(View.VISIBLE);

                                info_patronymic.setVisibility(View.VISIBLE);

                                info_number.setVisibility(View.VISIBLE);
                                textView15.setVisibility(View.VISIBLE);
                                info_date_start.setVisibility(View.VISIBLE);
                                textView16.setVisibility(View.VISIBLE);
                                info_date_end.setVisibility(View.VISIBLE);
                                textView20.setVisibility(View.VISIBLE);
                                info_zastava.setVisibility(View.VISIBLE);

                                add_client.setVisibility(View.INVISIBLE);
                                find_client.setVisibility(View.INVISIBLE);

                                delete_client.setVisibility(View.VISIBLE);


                            }else {

                                addphoto.setVisibility(View.INVISIBLE);
                                info_pay.setVisibility(View.INVISIBLE);
                                call.setVisibility(View.INVISIBLE);
                                whatsapp.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                apartment_edit_client.setVisibility(View.INVISIBLE);

                                info_surname.setVisibility(View.INVISIBLE);

                                info_name.setVisibility(View.INVISIBLE);

                                info_patronymic.setVisibility(View.INVISIBLE);

                                info_number.setVisibility(View.INVISIBLE);
                                textView15.setVisibility(View.INVISIBLE);
                                textView10.setVisibility(View.INVISIBLE);
                                info_date_start.setVisibility(View.INVISIBLE);
                                textView16.setVisibility(View.INVISIBLE);
                                info_date_end.setVisibility(View.INVISIBLE);
                                textView20.setVisibility(View.INVISIBLE);
                                info_zastava.setVisibility(View.INVISIBLE);

                                add_client.setVisibility(View.VISIBLE);
                                find_client.setVisibility(View.VISIBLE);
                                textView11.setVisibility(View.VISIBLE);

                                delete_client.setVisibility(View.INVISIBLE);

                            }

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("client_name1",name);
                            editor.putString("client_surname1",surname);
                            editor.putString("client_patronymic1",patronymic);
                            editor.putString("client_number1",number);
                            editor.putString("client_datestart1",datestart);
                            editor.putString("client_dateend1",dateend);
                            editor.putString("client_pay1",pay);
                            editor.putString("client_zastava1",zastava);
                            editor.putString("client_email1",email);

                            editor.apply();


                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client1");
                startActivityForResult(intent1,11);

            });

            archive.setOnClickListener(v -> {
                Intent intent5 = new Intent("archive1");
                startActivityForResult(intent5,991);

            });

            files.setOnClickListener(v -> {
                Intent intent12 = new Intent("files1");
                startActivityForResult(intent12, 200);
            });



            info_home.setOnClickListener(v -> {

                Intent intent1 = new Intent("info1");
                intent1.putExtra("city",city_id.getText().toString());
                intent1.putExtra("district",info_district.getText().toString());
                intent1.putExtra("rooms",info_rooms.getText().toString());
                intent1.putExtra("year",info_dateown.getText().toString());
                intent1.putExtra("size",info_size.getText().toString());
                startActivityForResult(intent1, 0111);

            });

            apartment_edit_client.setOnClickListener(v ->{

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit=myview.findViewById(R.id.update_edit);

                final EditText surname = myview.findViewById(R.id.surname_edit);
                final EditText name = myview.findViewById(R.id.name_edit);
                final EditText patronymic = myview.findViewById(R.id.patronymic_edit);
                final EditText number = myview.findViewById(R.id.number_edit);
                final EditText email = myview.findViewById(R.id.email_edit);
                final EditText datestart = myview.findViewById(R.id.datestart_edit);
                final EditText dateend = myview.findViewById(R.id.dateend_edit);
                final EditText pay = myview.findViewById(R.id.pay_edit);
                final EditText zastava = myview.findViewById(R.id.zastava_client);
                final EditText uid = myview.findViewById(R.id.uid);


                datestart.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            datestart.setText(current);
                            datestart.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                dateend.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            dateend.setText(current);
                            dateend.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                surname.setText(info_surname.getText().toString());
                name.setText(info_name.getText().toString());
                patronymic.setText(info_patronymic.getText().toString());
                number.setText(info_number.getText().toString());
                email.setText(info_email.getText().toString());
                datestart.setText(info_date_start.getText().toString());
                dateend.setText(info_date_end.getText().toString());
                pay.setText(info_pay.getText().toString().replaceAll("[^0-9]",""));
                zastava.setText(info_zastava.getText().toString().replaceAll("[^0-9]",""));
                uid.setText(info_uid.getText().toString());


                dialogPlus.show();

                update_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("surname",surname.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("patronymic",patronymic.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("datestart",datestart.getText().toString());
                        map.put("dateend",dateend.getText().toString());
                        map.put("pay",pay.getText().toString());
                        map.put("zastava",zastava.getText().toString());

                        info_surname.setText(surname.getText().toString());
                        info_name.setText(name.getText().toString());
                        info_patronymic.setText(patronymic.getText().toString());
                        info_number.setText(number.getText().toString());
                        info_email.setText(email.getText().toString());
                        info_date_start.setText(datestart.getText().toString());
                        info_date_end.setText(dateend.getText().toString());
                        info_pay.setText(pay.getText().toString()+ " Грн.");
                        info_zastava.setText(zastava.getText().toString()+ " Грн.");


                        Intent intent1 = new Intent();

                        setResult(RESULT_OK,intent1);

                        String uidd = uid.getText().toString();

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Client").child(uidd).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });
            });

            apartment_edit.setOnClickListener(v -> {

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit_apartment))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit_ap=myview.findViewById(R.id.update_edit_ap);

                  final EditText address = myview.findViewById(R.id.address_edit);
                  final EditText rooms = myview.findViewById(R.id.rooms_edit);
                  final EditText floor = myview.findViewById(R.id.floor_edit);
                  final EditText dateown = myview.findViewById(R.id.dateown_edit);
                  final EditText size = myview.findViewById(R.id.size_edit);



                address.setText(info_address.getText().toString());
                rooms.setText(info_rooms.getText().toString());
                floor.setText(info_floor.getText().toString());
                dateown.setText(info_dateown.getText().toString());
                size.setText(info_size.getText().toString());

                  dialogPlus.show();


                update_edit_ap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("address",address.getText().toString());
                        map.put("rooms",rooms.getText().toString());
                        map.put("floor",floor.getText().toString());
                        map.put("dateown",dateown.getText().toString());
                        map.put("size",size.getText().toString());

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("address1",address.getText().toString());

                        editor.apply();

                        Intent intent1 = new Intent();
                        intent1.putExtra("address1",address.getText().toString());
                        setResult(RESULT_OK,intent1);



                       FirebaseDatabase.getInstance().getReference(auth).child("New_Apartment").child("newApartment1").updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });

            });


            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client1");
                startActivityForResult(intent2,111);

            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ActivityCompat.checkSelfPermission (ActivityApartmentManager.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(ActivityApartmentManager.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)==
                                    PackageManager.PERMISSION_GRANTED )
                    {

                        getCurrentLocation();

                        String sSource = info_address.getText().toString().trim();
                        String sDestination = textView23.getText().toString().trim();

                        DisplayTrack (sDestination,sSource);


                    }
                    else {

                        ActivityCompat.requestPermissions(ActivityApartmentManager.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                ,Manifest.permission.ACCESS_COARSE_LOCATION},100);



                    }

                }

            });

            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
            String ImageURI = sharedPreferences1.getString("image1",null);

            if(ImageURI != null){

                image_client.setImageURI(Uri.parse(ImageURI));
                addphoto.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.VISIBLE);
                delphoto_client.setVisibility(View.VISIBLE);

            }


            delete_client.setOnClickListener(v -> {



                String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);

                Query query11 = reference1.child("New_Client").orderByChild("busyness").equalTo("idApart1");
                query11.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                                String key = snapShot.getKey();

                                reference1.child("New_Client").child(key).child("dateend").setValue(date);

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                NObusyness();


                info_surname.setText("");
                String client_surname = info_surname.getText().toString();

                info_name.setText("");
                String client_name = info_name.getText().toString();

                info_patronymic.setText("");
                String client_patronymic = info_patronymic.getText().toString();

                info_number.setText("");
                String client_number = info_number.getText().toString();

                info_date_start.setText("");
                String client_datestart = info_date_start.getText().toString();

                info_date_end.setText("");
                String client_dateend = info_date_end.getText().toString();

                info_pay.setText("");
                String client_pay = info_pay.getText().toString();

                info_zastava.setText("");
                String client_zastava = info_zastava.getText().toString();


                textView20.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name1",client_name);
                editor.putString("client_surname1",client_surname);
                editor.putString("client_patronymic1",client_patronymic);
                editor.putString("client_number1",client_number);
                editor.putString("client_datestart1",client_datestart);
                editor.putString("client_dateend1",client_dateend);
                editor.putString("client_pay1",client_pay);
                editor.putString("client_zastava1",client_zastava);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("image1",null);
                editor1.commit();




                editor.apply();

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.INVISIBLE);
                delphoto_client.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);
                apartment_edit_client.setVisibility(View.INVISIBLE);



                textView15.setVisibility(View.INVISIBLE);
                textView10.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                image_client.setVisibility(View.INVISIBLE);


                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });


            delphoto_client.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delphoto_client.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.INVISIBLE);
                    addphoto.setVisibility(View.VISIBLE);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityApartmentManager.this);
                    SharedPreferences.Editor editor1 = preferences.edit();
                    editor1.putString("image1",null);
                    editor1.commit();
                }
            });

            addphoto.setOnClickListener(v->  {

                permissionsCheck();
                Intent intent1;
                if (Build.VERSION.SDK_INT < 19){
                intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                }else {

                    intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);

                }
                intent1.setType("image/*");
                startActivityForResult(Intent.createChooser(intent1,"Select Picture"),01);

            });
        }

        if (action.equals("new_home2")) {
            info_address.setText(getIntent().getStringExtra("address_2"));


            imageshome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("showimage2");
                    startActivityForResult(intent1,139);
                }
            });

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
            Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment2");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot data : snapshot.getChildren()){

                        String address = data.child("address").getValue().toString();
                        String city = data.child("city").getValue().toString();
                        String district = data.child("district").getValue().toString();
                        String dateown = data.child("dateown").getValue().toString();
                        String floor = data.child("floor").getValue().toString();
                        String rooms = data.child("rooms").getValue().toString();
                        String size = data.child("size").getValue().toString();


                        info_address.setText(address);
                        info_city.setText(city+",");
                        info_district.setText(district);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);
                        info_size.setText(size);

                        if(city.equals("Київ")){

                            city_id.setText("Київ");

                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            Query query5 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart2");
            query5.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                            String dateend = snapShot.child("dateend").getValue().toString();
                            String datestart = snapShot.child("datestart").getValue().toString();
                            String email = snapShot.child("email").getValue().toString();
                            String name = snapShot.child("name").getValue().toString();
                            String number = snapShot.child("number").getValue().toString();
                            String patronymic = snapShot.child("patronymic").getValue().toString();
                            String pay = snapShot.child("pay").getValue().toString();
                            String surname = snapShot.child("surname").getValue().toString();
                            String zastava = snapShot.child("zastava").getValue().toString();
                            String uid = snapShot.getKey();


                            info_date_end.setText(dateend);
                            info_date_start.setText(datestart);
                            info_email.setText(email);
                            info_name.setText(name);
                            info_number.setText(number);
                            info_patronymic.setText(patronymic);
                            info_surname.setText(surname);
                            info_uid.setText(uid);

                            if(pay.equals("")){

                                info_pay.setText("    "+"0 Грн.");
                            }else {
                                info_pay.setText(pay + " Грн.");
                            }

                            if(zastava.equals("")){

                                info_zastava.setText("    "+"0 Грн.");

                            }else {
                                info_zastava.setText(zastava + " Грн.");
                            }


                            if(info_name.length()>0){

                                addphoto.setVisibility(View.VISIBLE);
                                info_pay.setVisibility(View.VISIBLE);
                                call.setVisibility(View.VISIBLE);
                                whatsapp.setVisibility(View.VISIBLE);
                                imageView4.setVisibility(View.VISIBLE);
                                apartment_edit_client.setVisibility(View.VISIBLE);
                                textView10.setVisibility(View.VISIBLE);

                                info_surname.setVisibility(View.VISIBLE);

                                info_name.setVisibility(View.VISIBLE);

                                info_patronymic.setVisibility(View.VISIBLE);

                                info_number.setVisibility(View.VISIBLE);
                                textView15.setVisibility(View.VISIBLE);
                                info_date_start.setVisibility(View.VISIBLE);
                                textView16.setVisibility(View.VISIBLE);
                                info_date_end.setVisibility(View.VISIBLE);
                                textView20.setVisibility(View.VISIBLE);
                                info_zastava.setVisibility(View.VISIBLE);

                                add_client.setVisibility(View.INVISIBLE);
                                find_client.setVisibility(View.INVISIBLE);
                                textView11.setVisibility(View.INVISIBLE);

                                delete_client.setVisibility(View.VISIBLE);


                            }else {

                                addphoto.setVisibility(View.INVISIBLE);
                                info_pay.setVisibility(View.INVISIBLE);
                                call.setVisibility(View.INVISIBLE);
                                whatsapp.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                apartment_edit_client.setVisibility(View.INVISIBLE);

                                info_surname.setVisibility(View.INVISIBLE);

                                info_name.setVisibility(View.INVISIBLE);

                                info_patronymic.setVisibility(View.INVISIBLE);

                                info_number.setVisibility(View.INVISIBLE);
                                textView15.setVisibility(View.INVISIBLE);
                                textView10.setVisibility(View.INVISIBLE);
                                info_date_start.setVisibility(View.INVISIBLE);
                                textView16.setVisibility(View.INVISIBLE);
                                info_date_end.setVisibility(View.INVISIBLE);
                                textView20.setVisibility(View.INVISIBLE);
                                info_zastava.setVisibility(View.INVISIBLE);

                                add_client.setVisibility(View.VISIBLE);
                                find_client.setVisibility(View.VISIBLE);
                                textView11.setVisibility(View.VISIBLE);

                                delete_client.setVisibility(View.INVISIBLE);

                            }

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("client_name2",name);
                            editor.putString("client_surname2",surname);
                            editor.putString("client_patronymic2",patronymic);
                            editor.putString("client_number2",number);
                            editor.putString("client_datestart2",datestart);
                            editor.putString("client_dateend2",dateend);
                            editor.putString("client_pay2",pay);
                            editor.putString("client_zastava2",zastava);
                            editor.putString("client_email2",email);

                            editor.apply();

                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client2");
                startActivityForResult(intent1,22);
            });


            info_home.setOnClickListener(v -> {

                Intent intent1 = new Intent("info1");
                intent1.putExtra("city",city_id.getText().toString());
                intent1.putExtra("district",info_district.getText().toString());
                intent1.putExtra("rooms",info_rooms.getText().toString());
                intent1.putExtra("year",info_dateown.getText().toString());
                intent1.putExtra("size",info_size.getText().toString());
                startActivityForResult(intent1, 0111);

            });

            apartment_edit_client.setOnClickListener(v ->{

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit=myview.findViewById(R.id.update_edit);

                final EditText surname = myview.findViewById(R.id.surname_edit);
                final EditText name = myview.findViewById(R.id.name_edit);
                final EditText patronymic = myview.findViewById(R.id.patronymic_edit);
                final EditText number = myview.findViewById(R.id.number_edit);
                final EditText email = myview.findViewById(R.id.email_edit);
                final EditText datestart = myview.findViewById(R.id.datestart_edit);
                final EditText dateend = myview.findViewById(R.id.dateend_edit);
                final EditText pay = myview.findViewById(R.id.pay_edit);
                final EditText zastava = myview.findViewById(R.id.zastava_client);
                final EditText uid = myview.findViewById(R.id.uid);


                datestart.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            datestart.setText(current);
                            datestart.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                dateend.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            dateend.setText(current);
                            dateend.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                surname.setText(info_surname.getText().toString());
                name.setText(info_name.getText().toString());
                patronymic.setText(info_patronymic.getText().toString());
                number.setText(info_number.getText().toString());
                email.setText(info_email.getText().toString());
                datestart.setText(info_date_start.getText().toString());
                dateend.setText(info_date_end.getText().toString());
                pay.setText(info_pay.getText().toString().replaceAll("[^0-9]",""));
                zastava.setText(info_zastava.getText().toString().replaceAll("[^0-9]",""));
                uid.setText(info_uid.getText().toString());


                dialogPlus.show();

                update_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("surname",surname.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("patronymic",patronymic.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("datestart",datestart.getText().toString());
                        map.put("dateend",dateend.getText().toString());
                        map.put("pay",pay.getText().toString());
                        map.put("zastava",zastava.getText().toString());

                        info_surname.setText(surname.getText().toString());
                        info_name.setText(name.getText().toString());
                        info_patronymic.setText(patronymic.getText().toString());
                        info_number.setText(number.getText().toString());
                        info_email.setText(email.getText().toString());
                        info_date_start.setText(datestart.getText().toString());
                        info_date_end.setText(dateend.getText().toString());
                        info_pay.setText(pay.getText().toString()+ " Грн.");
                        info_zastava.setText(zastava.getText().toString()+ " Грн.");


                        Intent intent1 = new Intent();

                        setResult(RESULT_OK,intent1);

                        String uidd = uid.getText().toString();

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Client").child(uidd).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });
            });

            apartment_edit.setOnClickListener(v -> {

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit_apartment))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit_ap=myview.findViewById(R.id.update_edit_ap);

                final EditText address = myview.findViewById(R.id.address_edit);
                final EditText rooms = myview.findViewById(R.id.rooms_edit);
                final EditText floor = myview.findViewById(R.id.floor_edit);
                final EditText dateown = myview.findViewById(R.id.dateown_edit);
                final EditText size = myview.findViewById(R.id.size_edit);


                address.setText(info_address.getText().toString());
                rooms.setText(info_rooms.getText().toString());
                floor.setText(info_floor.getText().toString());
                dateown.setText(info_dateown.getText().toString());
                size.setText(info_size.getText().toString());

                dialogPlus.show();


                update_edit_ap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("address",address.getText().toString());
                        map.put("rooms",rooms.getText().toString());
                        map.put("floor",floor.getText().toString());
                        map.put("dateown",dateown.getText().toString());
                        map.put("size",size.getText().toString());

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("address2",address.getText().toString());

                        editor.apply();

                        Intent intent1 = new Intent();
                        intent1.putExtra("address2",address.getText().toString());
                        setResult(RESULT_OK,intent1);

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Apartment").child("newApartment2").updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });

            });

            archive.setOnClickListener(v -> {
                Intent intent5 = new Intent("archive2");
                startActivityForResult(intent5,992);

            });

            files.setOnClickListener(v -> {
                Intent intent12 = new Intent("files2");
                startActivityForResult(intent12, 201);
            });



            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ActivityCompat.checkSelfPermission (ActivityApartmentManager.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(ActivityApartmentManager.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)==
                                    PackageManager.PERMISSION_GRANTED )
                    {

                        getCurrentLocation();

                        String sSource = info_address.getText().toString().trim();
                        String sDestination = textView23.getText().toString().trim();

                        DisplayTrack (sDestination,sSource);


                    }
                    else {

                        ActivityCompat.requestPermissions(ActivityApartmentManager.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                ,Manifest.permission.ACCESS_COARSE_LOCATION},100);



                    }

                }

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client2");
                startActivityForResult(intent2,222);

            });

            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
            String ImageURI = sharedPreferences1.getString("image2",null);

            if(ImageURI != null){

                image_client.setImageURI(Uri.parse(ImageURI));
                addphoto.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.VISIBLE);
                delphoto_client.setVisibility(View.VISIBLE);

            }

            delete_client.setOnClickListener(v -> {


                String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);

                Query query22 = reference1.child("New_Client").orderByChild("busyness").equalTo("idApart2");
                query22.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                                String key = snapShot.getKey();

                                reference1.child("New_Client").child(key).child("dateend").setValue(date);

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                NObusyness();

                info_surname.setText("");
                String client_surname = info_surname.getText().toString();

                info_name.setText("");
                String client_name = info_name.getText().toString();

                info_patronymic.setText("");
                String client_patronymic = info_patronymic.getText().toString();

                info_number.setText("");
                String client_number = info_number.getText().toString();

                info_date_start.setText("");
                String client_datestart = info_date_start.getText().toString();

                info_date_end.setText("");
                String client_dateend = info_date_end.getText().toString();

                info_pay.setText("");
                String client_pay = info_pay.getText().toString();

                info_zastava.setText("");
                String client_zastava = info_zastava.getText().toString();

                textView20.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name2",client_name);
                editor.putString("client_surname2",client_surname);
                editor.putString("client_patronymic2",client_patronymic);
                editor.putString("client_number2",client_number);
                editor.putString("client_datestart2",client_datestart);
                editor.putString("client_dateend2",client_dateend);
                editor.putString("client_pay2",client_pay);
                editor.putString("client_zastava2",client_zastava);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("image2",null);
                editor1.commit();

                editor.apply();

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);
                apartment_edit_client.setVisibility(View.INVISIBLE);

                textView15.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);
                textView10.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                image_client.setVisibility(View.INVISIBLE);


                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });

            delphoto_client.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delphoto_client.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.INVISIBLE);
                    addphoto.setVisibility(View.VISIBLE);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityApartmentManager.this);
                    SharedPreferences.Editor editor1 = preferences.edit();
                    editor1.putString("image2",null);
                    editor1.commit();
                }
            });

            addphoto.setOnClickListener(v->  {

                permissionsCheck();
                Intent intent1;
                if (Build.VERSION.SDK_INT < 19){
                    intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                }else {

                    intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);

                }
                intent1.setType("image/*");
                startActivityForResult(Intent.createChooser(intent1,"Select Picture"),02);

            });

        }

        if (action.equals("new_home3")) {

            imageshome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("showimage3");
                    startActivityForResult(intent1,149);
                }
            });

            info_address.setText(getIntent().getStringExtra("address_3"));

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
            Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment3");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot data : snapshot.getChildren()){

                        String address = data.child("address").getValue().toString();
                        String city = data.child("city").getValue().toString();
                        String district = data.child("district").getValue().toString();
                        String dateown = data.child("dateown").getValue().toString();
                        String floor = data.child("floor").getValue().toString();
                        String rooms = data.child("rooms").getValue().toString();
                        String size = data.child("size").getValue().toString();


                        info_address.setText(address);
                        info_city.setText(city+",");
                        info_district.setText(district);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);
                        info_size.setText(size);

                        if(city.equals("Київ")){

                            city_id.setText("Київ");

                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            Query query5 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart3");
            query5.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                            String dateend = snapShot.child("dateend").getValue().toString();
                            String datestart = snapShot.child("datestart").getValue().toString();
                            String email = snapShot.child("email").getValue().toString();
                            String name = snapShot.child("name").getValue().toString();
                            String number = snapShot.child("number").getValue().toString();
                            String patronymic = snapShot.child("patronymic").getValue().toString();
                            String pay = snapShot.child("pay").getValue().toString();
                            String surname = snapShot.child("surname").getValue().toString();
                            String zastava = snapShot.child("zastava").getValue().toString();
                            String uid = snapShot.getKey();
                            info_uid.setText(uid);

                            info_date_end.setText(dateend);
                            info_date_start.setText(datestart);
                            info_email.setText(email);
                            info_name.setText(name);
                            info_number.setText(number);
                            info_patronymic.setText(patronymic);
                            info_surname.setText(surname);

                            if(pay.equals("")){

                                info_pay.setText("    "+"0 Грн.");
                            }else {
                                info_pay.setText(pay + " Грн.");
                            }

                            if(zastava.equals("")){

                                info_zastava.setText("    "+"0 Грн.");

                            }else {
                                info_zastava.setText(zastava + " Грн.");
                            }


                            if(info_name.length()>0){

                                addphoto.setVisibility(View.VISIBLE);
                                info_pay.setVisibility(View.VISIBLE);
                                call.setVisibility(View.VISIBLE);
                                whatsapp.setVisibility(View.VISIBLE);
                                imageView4.setVisibility(View.VISIBLE);
                                apartment_edit_client.setVisibility(View.VISIBLE);
                                textView10.setVisibility(View.VISIBLE);

                                info_surname.setVisibility(View.VISIBLE);

                                info_name.setVisibility(View.VISIBLE);

                                info_patronymic.setVisibility(View.VISIBLE);

                                info_number.setVisibility(View.VISIBLE);
                                textView15.setVisibility(View.VISIBLE);
                                info_date_start.setVisibility(View.VISIBLE);
                                textView16.setVisibility(View.VISIBLE);
                                info_date_end.setVisibility(View.VISIBLE);
                                textView20.setVisibility(View.VISIBLE);
                                info_zastava.setVisibility(View.VISIBLE);

                                add_client.setVisibility(View.INVISIBLE);
                                find_client.setVisibility(View.INVISIBLE);
                                textView11.setVisibility(View.INVISIBLE);

                                delete_client.setVisibility(View.VISIBLE);


                            }else {

                                addphoto.setVisibility(View.INVISIBLE);
                                info_pay.setVisibility(View.INVISIBLE);
                                call.setVisibility(View.INVISIBLE);
                                whatsapp.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                apartment_edit_client.setVisibility(View.INVISIBLE);

                                info_surname.setVisibility(View.INVISIBLE);

                                info_name.setVisibility(View.INVISIBLE);

                                info_patronymic.setVisibility(View.INVISIBLE);

                                info_number.setVisibility(View.INVISIBLE);
                                textView15.setVisibility(View.INVISIBLE);
                                textView10.setVisibility(View.INVISIBLE);
                                info_date_start.setVisibility(View.INVISIBLE);
                                textView16.setVisibility(View.INVISIBLE);
                                info_date_end.setVisibility(View.INVISIBLE);
                                textView20.setVisibility(View.INVISIBLE);
                                info_zastava.setVisibility(View.INVISIBLE);

                                add_client.setVisibility(View.VISIBLE);
                                find_client.setVisibility(View.VISIBLE);
                                textView11.setVisibility(View.VISIBLE);

                                delete_client.setVisibility(View.INVISIBLE);

                            }

                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("client_name3",name);
                            editor.putString("client_surname3",surname);
                            editor.putString("client_patronymic3",patronymic);
                            editor.putString("client_number3",number);
                            editor.putString("client_datestart3",datestart);
                            editor.putString("client_dateend3",dateend);
                            editor.putString("client_pay3",pay);
                            editor.putString("client_zastava3",zastava);
                            editor.putString("client_email3",email);

                            editor.apply();



                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client3");
                startActivityForResult(intent1,33);
            });

            archive.setOnClickListener(v -> {
                Intent intent5 = new Intent("archive3");
                startActivityForResult(intent5,993);

            });

            files.setOnClickListener(v -> {
                Intent intent12 = new Intent("files3");
                startActivityForResult(intent12, 202);
            });


            info_home.setOnClickListener(v -> {

                Intent intent1 = new Intent("info1");
                intent1.putExtra("city",city_id.getText().toString());
                intent1.putExtra("district",info_district.getText().toString());
                intent1.putExtra("rooms",info_rooms.getText().toString());
                intent1.putExtra("year",info_dateown.getText().toString());
                intent1.putExtra("size",info_size.getText().toString());
                startActivityForResult(intent1, 0111);

            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ActivityCompat.checkSelfPermission (ActivityApartmentManager.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(ActivityApartmentManager.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)==
                                    PackageManager.PERMISSION_GRANTED )
                    {

                        getCurrentLocation();

                        String sSource = info_address.getText().toString().trim();
                        String sDestination = textView23.getText().toString().trim();

                        DisplayTrack (sDestination,sSource);


                    }
                    else {

                        ActivityCompat.requestPermissions(ActivityApartmentManager.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                ,Manifest.permission.ACCESS_COARSE_LOCATION},100);



                    }

                }

            });

            apartment_edit_client.setOnClickListener(v ->{

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit=myview.findViewById(R.id.update_edit);

                final EditText surname = myview.findViewById(R.id.surname_edit);
                final EditText name = myview.findViewById(R.id.name_edit);
                final EditText patronymic = myview.findViewById(R.id.patronymic_edit);
                final EditText number = myview.findViewById(R.id.number_edit);
                final EditText email = myview.findViewById(R.id.email_edit);
                final EditText datestart = myview.findViewById(R.id.datestart_edit);
                final EditText dateend = myview.findViewById(R.id.dateend_edit);
                final EditText pay = myview.findViewById(R.id.pay_edit);
                final EditText zastava = myview.findViewById(R.id.zastava_client);
                final EditText uid = myview.findViewById(R.id.uid);


                datestart.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            datestart.setText(current);
                            datestart.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                dateend.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            dateend.setText(current);
                            dateend.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                surname.setText(info_surname.getText().toString());
                name.setText(info_name.getText().toString());
                patronymic.setText(info_patronymic.getText().toString());
                number.setText(info_number.getText().toString());
                email.setText(info_email.getText().toString());
                datestart.setText(info_date_start.getText().toString());
                dateend.setText(info_date_end.getText().toString());
                pay.setText(info_pay.getText().toString().replaceAll("[^0-9]",""));
                zastava.setText(info_zastava.getText().toString().replaceAll("[^0-9]",""));
                uid.setText(info_uid.getText().toString());


                dialogPlus.show();

                update_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("surname",surname.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("patronymic",patronymic.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("datestart",datestart.getText().toString());
                        map.put("dateend",dateend.getText().toString());
                        map.put("pay",pay.getText().toString());
                        map.put("zastava",zastava.getText().toString());

                        info_surname.setText(surname.getText().toString());
                        info_name.setText(name.getText().toString());
                        info_patronymic.setText(patronymic.getText().toString());
                        info_number.setText(number.getText().toString());
                        info_email.setText(email.getText().toString());
                        info_date_start.setText(datestart.getText().toString());
                        info_date_end.setText(dateend.getText().toString());
                        info_pay.setText(pay.getText().toString()+ " Грн.");
                        info_zastava.setText(zastava.getText().toString()+ " Грн.");


                        Intent intent1 = new Intent();

                        setResult(RESULT_OK,intent1);

                        String uidd = uid.getText().toString();

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Client").child(uidd).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });
            });

            apartment_edit.setOnClickListener(v -> {

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit_apartment))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit_ap=myview.findViewById(R.id.update_edit_ap);

                final EditText address = myview.findViewById(R.id.address_edit);
                final EditText rooms = myview.findViewById(R.id.rooms_edit);
                final EditText floor = myview.findViewById(R.id.floor_edit);
                final EditText dateown = myview.findViewById(R.id.dateown_edit);
                final EditText size = myview.findViewById(R.id.size_edit);

                address.setText(info_address.getText().toString());
                rooms.setText(info_rooms.getText().toString());
                floor.setText(info_floor.getText().toString());
                dateown.setText(info_dateown.getText().toString());
                size.setText(info_size.getText().toString());



                dialogPlus.show();


                update_edit_ap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("address",address.getText().toString());
                        map.put("rooms",rooms.getText().toString());
                        map.put("floor",floor.getText().toString());
                        map.put("dateown",dateown.getText().toString());
                        map.put("size",size.getText().toString());

                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("address3",address.getText().toString());

                        editor.apply();

                        Intent intent1 = new Intent();
                        intent1.putExtra("address3",address.getText().toString());
                        setResult(RESULT_OK,intent1);

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Apartment").child("newApartment3").updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client3");
                startActivityForResult(intent2,333);

            });

            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
            String ImageURI = sharedPreferences1.getString("image3",null);

            if(ImageURI != null){

                image_client.setImageURI(Uri.parse(ImageURI));
                addphoto.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.VISIBLE);
                delphoto_client.setVisibility(View.VISIBLE);

            }

            delete_client.setOnClickListener(v -> {


                String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();

                Query query33 = reference1.child("New_Client").orderByChild("busyness").equalTo("idApart3");
                query33.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                                String key = snapShot.getKey();

                                reference1.child("New_Client").child(key).child("dateend").setValue(date);

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                NObusyness();

                info_surname.setText("");
                String client_surname = info_surname.getText().toString();

                info_name.setText("");
                String client_name = info_name.getText().toString();

                info_patronymic.setText("");
                String client_patronymic = info_patronymic.getText().toString();

                info_number.setText("");
                String client_number = info_number.getText().toString();

                info_date_start.setText("");
                String client_datestart = info_date_start.getText().toString();

                info_date_end.setText("");
                String client_dateend = info_date_end.getText().toString();

                info_pay.setText("");
                String client_pay = info_pay.getText().toString();

                info_zastava.setText("");
                String client_zastava = info_zastava.getText().toString();

                textView20.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name3",client_name);
                editor.putString("client_surname3",client_surname);
                editor.putString("client_patronymic3",client_patronymic);
                editor.putString("client_number3",client_number);
                editor.putString("client_datestart3",client_datestart);
                editor.putString("client_dateend3",client_dateend);
                editor.putString("client_pay3",client_pay);
                editor.putString("client_zastava3",client_zastava);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("image3",null);
                editor1.commit();

                editor.apply();

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);
                apartment_edit_client.setVisibility(View.INVISIBLE);


                textView15.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);
                textView10.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                image_client.setVisibility(View.INVISIBLE);


                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });

        delphoto_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delphoto_client.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.VISIBLE);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityApartmentManager.this);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("image3",null);
                editor1.commit();
            }
        });

        addphoto.setOnClickListener(v->  {

            permissionsCheck();
            Intent intent1;
            if (Build.VERSION.SDK_INT < 19){
                intent1 = new Intent(Intent.ACTION_GET_CONTENT);
            }else {

                intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent1.addCategory(Intent.CATEGORY_OPENABLE);

            }
            intent1.setType("image/*");
            startActivityForResult(Intent.createChooser(intent1,"Select Picture"),03);

        });

        }

        if (action.equals("new_home4")) {

            imageshome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("showimage4");
                    startActivityForResult(intent1,159);
                }
            });

            info_address.setText(getIntent().getStringExtra("address_4"));

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
            Query query = databaseReference.child("New_Apartment").orderByChild("id").equalTo("newApartment4");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot data : snapshot.getChildren()){

                        String address = data.child("address").getValue().toString();
                        String city = data.child("city").getValue().toString();
                        String district = data.child("district").getValue().toString();
                        String dateown = data.child("dateown").getValue().toString();
                        String floor = data.child("floor").getValue().toString();
                        String rooms = data.child("rooms").getValue().toString();
                        String size = data.child("size").getValue().toString();


                        info_address.setText(address);
                        info_city.setText(city+",");
                        info_district.setText(district);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);
                        info_size.setText(size);

                        if(city.equals("Київ")){

                            city_id.setText("Київ");

                        }


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            Query query7 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart4");
            query7.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {

                        for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                            String dateend = snapShot.child("dateend").getValue().toString();
                            String datestart = snapShot.child("datestart").getValue().toString();
                            String email = snapShot.child("email").getValue().toString();
                            String name = snapShot.child("name").getValue().toString();
                            String number = snapShot.child("number").getValue().toString();
                            String patronymic = snapShot.child("patronymic").getValue().toString();
                            String pay = snapShot.child("pay").getValue().toString();
                            String surname = snapShot.child("surname").getValue().toString();
                            String zastava = snapShot.child("zastava").getValue().toString();
                            String uid = snapShot.getKey();
                            info_uid.setText(uid);

                            info_date_end.setText(dateend);
                            info_date_start.setText(datestart);
                            info_email.setText(email);
                            info_name.setText(name);
                            info_number.setText(number);
                            info_patronymic.setText(patronymic);
                            info_surname.setText(surname);

                            if(pay.equals("")){

                                info_pay.setText("    "+"0 Грн.");
                            }else {
                                info_pay.setText(pay + " Грн.");
                            }

                            if(zastava.equals("")){

                                info_zastava.setText("    "+"0 Грн.");

                            }else {
                                info_zastava.setText(zastava + " Грн.");
                            }



                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("client_name4",name);
                            editor.putString("client_surname4",surname);
                            editor.putString("client_patronymic4",patronymic);
                            editor.putString("client_number4",number);
                            editor.putString("client_datestart4",datestart);
                            editor.putString("client_dateend4",dateend);
                            editor.putString("client_pay4",pay);
                            editor.putString("client_zastava4",zastava);
                            editor.putString("client_email4",email);

                            editor.apply();

                            if(info_name.length()>0){

                                addphoto.setVisibility(View.VISIBLE);
                                info_pay.setVisibility(View.VISIBLE);
                                call.setVisibility(View.VISIBLE);
                                whatsapp.setVisibility(View.VISIBLE);
                                imageView4.setVisibility(View.VISIBLE);
                                apartment_edit_client.setVisibility(View.VISIBLE);

                                info_surname.setVisibility(View.VISIBLE);

                                info_name.setVisibility(View.VISIBLE);

                                info_patronymic.setVisibility(View.VISIBLE);

                                info_number.setVisibility(View.VISIBLE);
                                textView15.setVisibility(View.VISIBLE);
                                info_date_start.setVisibility(View.VISIBLE);
                                textView16.setVisibility(View.VISIBLE);
                                textView10.setVisibility(View.VISIBLE);
                                info_date_end.setVisibility(View.VISIBLE);
                                textView20.setVisibility(View.VISIBLE);
                                info_zastava.setVisibility(View.VISIBLE);

                                add_client.setVisibility(View.INVISIBLE);
                                find_client.setVisibility(View.INVISIBLE);
                                textView11.setVisibility(View.INVISIBLE);

                                delete_client.setVisibility(View.VISIBLE);


                            }else {

                                addphoto.setVisibility(View.INVISIBLE);
                                info_pay.setVisibility(View.INVISIBLE);
                                call.setVisibility(View.INVISIBLE);
                                whatsapp.setVisibility(View.INVISIBLE);
                                imageView4.setVisibility(View.INVISIBLE);
                                apartment_edit_client.setVisibility(View.INVISIBLE);
                                textView10.setVisibility(View.INVISIBLE);

                                info_surname.setVisibility(View.INVISIBLE);

                                info_name.setVisibility(View.INVISIBLE);

                                info_patronymic.setVisibility(View.INVISIBLE);

                                info_number.setVisibility(View.INVISIBLE);
                                textView15.setVisibility(View.INVISIBLE);
                                info_date_start.setVisibility(View.INVISIBLE);
                                textView16.setVisibility(View.INVISIBLE);
                                info_date_end.setVisibility(View.INVISIBLE);
                                textView20.setVisibility(View.INVISIBLE);
                                info_zastava.setVisibility(View.INVISIBLE);

                                add_client.setVisibility(View.VISIBLE);
                                find_client.setVisibility(View.VISIBLE);
                                textView11.setVisibility(View.VISIBLE);

                                delete_client.setVisibility(View.INVISIBLE);

                            }

                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client4");
                startActivityForResult(intent1,44);
            });


            files.setOnClickListener(v -> {
                Intent intent12 = new Intent("files4");
                startActivityForResult(intent12, 203);
            });


            info_home.setOnClickListener(v -> {

                Intent intent1 = new Intent("info1");
                intent1.putExtra("city",city_id.getText().toString());
                intent1.putExtra("district",info_district.getText().toString());
                intent1.putExtra("rooms",info_rooms.getText().toString());
                intent1.putExtra("year",info_dateown.getText().toString());
                intent1.putExtra("size",info_size.getText().toString());
                startActivityForResult(intent1, 0111);

            });

            archive.setOnClickListener(v -> {
                Intent intent5 = new Intent("archive4");
                startActivityForResult(intent5,994);

            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ActivityCompat.checkSelfPermission (ActivityApartmentManager.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(ActivityApartmentManager.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)==
                                    PackageManager.PERMISSION_GRANTED )
                    {

                        getCurrentLocation();

                        String sSource = info_address.getText().toString().trim();
                        String sDestination = textView23.getText().toString().trim();

                        DisplayTrack (sDestination,sSource);


                    }
                    else {

                        ActivityCompat.requestPermissions(ActivityApartmentManager.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                ,Manifest.permission.ACCESS_COARSE_LOCATION},100);



                    }

                }

            });

            apartment_edit_client.setOnClickListener(v ->{

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit=myview.findViewById(R.id.update_edit);

                final EditText surname = myview.findViewById(R.id.surname_edit);
                final EditText name = myview.findViewById(R.id.name_edit);
                final EditText patronymic = myview.findViewById(R.id.patronymic_edit);
                final EditText number = myview.findViewById(R.id.number_edit);
                final EditText email = myview.findViewById(R.id.email_edit);
                final EditText datestart = myview.findViewById(R.id.datestart_edit);
                final EditText dateend = myview.findViewById(R.id.dateend_edit);
                final EditText pay = myview.findViewById(R.id.pay_edit);
                final EditText zastava = myview.findViewById(R.id.zastava_client);
                final EditText uid = myview.findViewById(R.id.uid);


                datestart.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            datestart.setText(current);
                            datestart.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });


                dateend.addTextChangedListener(new TextWatcher() {

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

                                year = (year<1900)?1900:(year>2100)?2100:year;
                                cal.set(Calendar.YEAR, year);


                                day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                                clean = String.format("%02d%02d%02d",day, mon, year);
                            }

                            clean = String.format("%s/%s/%s", clean.substring(0, 2),
                                    clean.substring(2, 4),
                                    clean.substring(4, 8));

                            sel = sel < 0 ? 0 : sel;
                            current = clean;
                            dateend.setText(current);
                            dateend.setSelection(sel < current.length() ? sel : current.length());



                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                surname.setText(info_surname.getText().toString());
                name.setText(info_name.getText().toString());
                patronymic.setText(info_patronymic.getText().toString());
                number.setText(info_number.getText().toString());
                email.setText(info_email.getText().toString());
                datestart.setText(info_date_start.getText().toString());
                dateend.setText(info_date_end.getText().toString());
                pay.setText(info_pay.getText().toString().replaceAll("[^0-9]",""));
                zastava.setText(info_zastava.getText().toString().replaceAll("[^0-9]",""));
                uid.setText(info_uid.getText().toString());


                dialogPlus.show();

                update_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("surname",surname.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("patronymic",patronymic.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("datestart",datestart.getText().toString());
                        map.put("dateend",dateend.getText().toString());
                        map.put("pay",pay.getText().toString());
                        map.put("zastava",zastava.getText().toString());

                        info_surname.setText(surname.getText().toString());
                        info_name.setText(name.getText().toString());
                        info_patronymic.setText(patronymic.getText().toString());
                        info_number.setText(number.getText().toString());
                        info_email.setText(email.getText().toString());
                        info_date_start.setText(datestart.getText().toString());
                        info_date_end.setText(dateend.getText().toString());
                        info_pay.setText(pay.getText().toString()+ " Грн.");
                        info_zastava.setText(zastava.getText().toString()+ " Грн.");


                        Intent intent1 = new Intent();

                        setResult(RESULT_OK,intent1);

                        String uidd = uid.getText().toString();

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Client").child(uidd).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });
            });

            apartment_edit.setOnClickListener(v -> {

                DialogPlus dialogPlus = DialogPlus.newDialog(this)
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit_apartment))
                        .create();

                View myview=dialogPlus.getHolderView();

                Button update_edit_ap=myview.findViewById(R.id.update_edit_ap);

                final EditText address = myview.findViewById(R.id.address_edit);
                final EditText rooms = myview.findViewById(R.id.rooms_edit);
                final EditText floor = myview.findViewById(R.id.floor_edit);
                final EditText dateown = myview.findViewById(R.id.dateown_edit);
                final EditText size = myview.findViewById(R.id.size_edit);


                address.setText(info_address.getText().toString());
                rooms.setText(info_rooms.getText().toString());
                floor.setText(info_floor.getText().toString());
                dateown.setText(info_dateown.getText().toString());
                size.setText(info_size.getText().toString());

                dialogPlus.show();


                update_edit_ap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();

                        map.put("address",address.getText().toString());
                        map.put("rooms",rooms.getText().toString());
                        map.put("floor",floor.getText().toString());
                        map.put("dateown",dateown.getText().toString());
                        map.put("size",size.getText().toString());


                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("address4",address.getText().toString());

                        editor.apply();

                        Intent intent1 = new Intent();
                        intent1.putExtra("address4",address.getText().toString());
                        setResult(RESULT_OK,intent1);

                        FirebaseDatabase.getInstance().getReference(auth).child("New_Apartment").child("newApartment4").updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialogPlus.dismiss();
                            }
                        });

                    }
                });

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client4");
                startActivityForResult(intent2,444);

            });

            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(this);
            String ImageURI = sharedPreferences1.getString("image4",null);

            if(ImageURI != null){

                image_client.setImageURI(Uri.parse(ImageURI));
                addphoto.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.VISIBLE);
                delphoto_client.setVisibility(View.VISIBLE);

            }

            delete_client.setOnClickListener(v -> {

                String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

                DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference();

                Query query44 = reference1.child("New_Client").orderByChild("busyness").equalTo("idApart4");
                query44.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                                String key = snapShot.getKey();

                                reference1.child("New_Client").child(key).child("dateend").setValue(date);

                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


                NObusyness();

                info_surname.setText("");
                String client_surname = info_surname.getText().toString();

                info_name.setText("");
                String client_name = info_name.getText().toString();

                info_patronymic.setText("");
                String client_patronymic = info_patronymic.getText().toString();

                info_number.setText("");
                String client_number = info_number.getText().toString();

                info_date_start.setText("");
                String client_datestart = info_date_start.getText().toString();

                info_date_end.setText("");
                String client_dateend = info_date_end.getText().toString();

                info_pay.setText("");
                String client_pay = info_pay.getText().toString();

                info_zastava.setText("");
                String client_zastava = info_zastava.getText().toString();

                textView20.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name4",client_name);
                editor.putString("client_surname4",client_surname);
                editor.putString("client_patronymic4",client_patronymic);
                editor.putString("client_number4",client_number);
                editor.putString("client_datestart4",client_datestart);
                editor.putString("client_dateend4",client_dateend);
                editor.putString("client_pay4",client_pay);
                editor.putString("client_zastava4",client_zastava);


                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor1 = preferences.edit();
                editor1.putString("image3",null);
                editor1.commit();

                editor.apply();


                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);
                apartment_edit_client.setVisibility(View.INVISIBLE);

                textView15.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);
                textView10.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                image_client.setVisibility(View.INVISIBLE);


                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });

            delphoto_client.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delphoto_client.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.INVISIBLE);
                    addphoto.setVisibility(View.VISIBLE);

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ActivityApartmentManager.this);
                    SharedPreferences.Editor editor1 = preferences.edit();
                    editor1.putString("image4",null);
                    editor1.commit();
                }
            });

            addphoto.setOnClickListener(v->  {

                permissionsCheck();
                Intent intent1;
                if (Build.VERSION.SDK_INT < 19){
                    intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                }else {

                    intent1 = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                    intent1.addCategory(Intent.CATEGORY_OPENABLE);

                }
                intent1.setType("image/*");
                startActivityForResult(Intent.createChooser(intent1,"Select Picture"),04);

            });

        }



    }

    private void DisplayTrack(String sSource, String sDestination) {

        try{

            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/"+sSource + "/"+ sDestination);

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setPackage("com.google.android.apps.maps");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }catch (ActivityNotFoundException e ){

            Uri uri = Uri.parse("http://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length>0 && (grantResults[0]+grantResults[1]==PackageManager.PERMISSION_GRANTED)){

            getCurrentLocation();

        }else{

            Toast.makeText(getApplicationContext(),"Дайте дозвіл на GPS!",Toast.LENGTH_SHORT);

        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {

        LocationManager locationManager = (LocationManager)getSystemService(
                Context.LOCATION_SERVICE
        );

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {

                    Location location = task.getResult();

                    if(location!=null){
                        textView23.setText(String.valueOf(location.getLatitude() + " , " +location.getLongitude()));
                    }else {

                        LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback(){

                            @Override
                            public void onLocationResult(LocationResult locationResult) {


                                textView23.setText(String.valueOf(location.getLatitude() + " , " +location.getLongitude()));
                            }
                        };

                             fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());

                    }
                }

            });

        }else {

            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));


        }
    }

    public void permissionsCheck() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 01) {



            if(resultCode == RESULT_OK) {

                if (data != null) {

                    ImageUri = data.getData();

                    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("image1",String.valueOf(ImageUri));
                    editor.commit();

                    image_client.setImageURI(ImageUri);
                    image_client.invalidate();

                    addphoto.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.VISIBLE);
                    delphoto_client.setVisibility(View.VISIBLE);
                }
            }
        }

        if(requestCode == 02) {

            if(resultCode == RESULT_OK) {
                if (data != null) {

                    ImageUri = data.getData();

                    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("image2", String.valueOf(ImageUri));
                    editor.commit();

                    image_client.setImageURI(ImageUri);
                    image_client.invalidate();

                    addphoto.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.VISIBLE);
                    delphoto_client.setVisibility(View.VISIBLE);
                }
            }
        }

        if(requestCode == 03) {

            if(resultCode == RESULT_OK) {
                if (data != null) {

                    ImageUri = data.getData();

                    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("image3", String.valueOf(ImageUri));
                    editor.commit();

                    image_client.setImageURI(ImageUri);
                    image_client.invalidate();

                    addphoto.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.VISIBLE);
                    delphoto_client.setVisibility(View.VISIBLE);
                }
            }
        }

        if(requestCode == 04) {

            if(resultCode == RESULT_OK) {
                if (data != null) {

                    ImageUri = data.getData();

                    SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putString("image4", String.valueOf(ImageUri));
                    editor.commit();

                    image_client.setImageURI(ImageUri);
                    image_client.invalidate();

                    addphoto.setVisibility(View.INVISIBLE);
                    image_client.setVisibility(View.VISIBLE);
                    delphoto_client.setVisibility(View.VISIBLE);
                }
            }
        }


        if (requestCode == 11) {
            if(resultCode==RESULT_OK){
                String client_name = data.getStringExtra("client_name1");
                info_name.setText(client_name);


                String client_surname = data.getStringExtra("client_surname1");
                info_surname.setText(client_surname);

                String client_patronymic = data.getStringExtra("client_patronymic1");
                info_patronymic.setText(client_patronymic);

                String client_number = data.getStringExtra("client_number1");
                info_number.setText(client_number);

                String client_date_start = data.getStringExtra("client_datestart1");
                info_date_start.setText(client_date_start);

                String client_email = data.getStringExtra("client_email1");
                info_email.setText(client_email);

                String client_date_end = data.getStringExtra("client_dateend1");
                info_date_end.setText(client_date_end);

                String client_pay = data.getStringExtra("client_pay1");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info1");
                info_zastava.setText(client_zastava + " Грн.");

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name1",client_name);
                editor.putString("client_surname1",client_surname);
                editor.putString("client_patronymic1",client_patronymic);
                editor.putString("client_number1",client_number);
                editor.putString("client_datestart1",client_date_start);
                editor.putString("client_dateend1",client_date_end);
                editor.putString("client_pay1",client_pay);
                editor.putString("client_zastava1",client_zastava);
                editor.putString("client_email1",client_email);


                editor.apply();


                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);


                imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);



                textView15.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

                busynessid1();

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 22) {
            if(resultCode==RESULT_OK){
                String client_name = data.getStringExtra("client_name2");
                info_name.setText(client_name);


                String client_surname = data.getStringExtra("client_surname2");
                info_surname.setText(client_surname);

                String client_patronymic = data.getStringExtra("client_patronymic2");
                info_patronymic.setText(client_patronymic);

                String client_number = data.getStringExtra("client_number2");
                info_number.setText(client_number);

                String client_date_start = data.getStringExtra("client_datestart2");
                info_date_start.setText(client_date_start);

                String client_date_end = data.getStringExtra("client_dateend2");
                info_date_end.setText(client_date_end);

                String client_email = data.getStringExtra("client_email2");
                info_email.setText(client_email);

                String client_pay = data.getStringExtra("client_pay2");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info2");
                info_zastava.setText(client_zastava + " Грн.");

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name2",client_name);
                editor.putString("client_surname2",client_surname);
                editor.putString("client_patronymic2",client_patronymic);
                editor.putString("client_number2",client_number);
                editor.putString("client_datestart2",client_date_start);
                editor.putString("client_dateend2",client_date_end);
                editor.putString("client_pay2",client_pay);
                editor.putString("client_zastava2",client_zastava);
                editor.putString("client_email2",client_email);


                editor.apply();


                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);



                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

                busynessid2();

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 33) {
            if(resultCode==RESULT_OK){
                String client_name = data.getStringExtra("client_name3");
                info_name.setText(client_name);


                String client_surname = data.getStringExtra("client_surname3");
                info_surname.setText(client_surname);

                String client_patronymic = data.getStringExtra("client_patronymic3");
                info_patronymic.setText(client_patronymic);

                String client_number = data.getStringExtra("client_number3");
                info_number.setText(client_number);

                String client_date_start = data.getStringExtra("client_datestart3");
                info_date_start.setText(client_date_start);

                String client_date_end = data.getStringExtra("client_dateend3");
                info_date_end.setText(client_date_end);

                String client_email = data.getStringExtra("client_email3");
                info_email.setText(client_email);

                String client_pay = data.getStringExtra("client_pay3");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info3");
                info_zastava.setText(client_zastava + " Грн.");

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name3",client_name);
                editor.putString("client_surname3",client_surname);
                editor.putString("client_patronymic3",client_patronymic);
                editor.putString("client_number3",client_number);
                editor.putString("client_datestart3",client_date_start);
                editor.putString("client_dateend3",client_date_end);
                editor.putString("client_pay3",client_pay);
                editor.putString("client_zastava3",client_zastava);
                editor.putString("client_email3",client_email);


                editor.apply();

                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);



                textView15.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

                busynessid3();

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 44) {
            if(resultCode==RESULT_OK){
                String client_name = data.getStringExtra("client_name4");
                info_name.setText(client_name);


                String client_surname = data.getStringExtra("client_surname4");
                info_surname.setText(client_surname);

                String client_patronymic = data.getStringExtra("client_patronymic4");
                info_patronymic.setText(client_patronymic);

                String client_number = data.getStringExtra("client_number4");
                info_number.setText(client_number);

                String client_date_start = data.getStringExtra("client_datestart4");
                info_date_start.setText(client_date_start);

                String client_date_end = data.getStringExtra("client_dateend4");
                info_date_end.setText(client_date_end);

                String client_email = data.getStringExtra("client_email4");
                info_email.setText(client_email);

                String client_pay = data.getStringExtra("client_pay4");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info4");
                info_zastava.setText(client_zastava + " Грн.");

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("client_name4",client_name);
                editor.putString("client_surname4",client_surname);
                editor.putString("client_patronymic4",client_patronymic);
                editor.putString("client_number4",client_number);
                editor.putString("client_datestart4",client_date_start);
                editor.putString("client_dateend4",client_date_end);
                editor.putString("client_pay4",client_pay);
                editor.putString("client_zastava4",client_zastava);
                editor.putString("client_email4",client_email);


                editor.apply();


                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);



                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView10.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

                busynessid4();

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }




        if (requestCode == 1111) {
            if(resultCode==RESULT_OK){
                String info_rooms1 = data.getStringExtra("apartment_room1");
                info_rooms.setText(info_rooms1);


                String info_floor1 = data.getStringExtra("apartment_floor1");
                info_floor.setText(info_floor1);

                String info_dateown1 = data.getStringExtra("apartment_purchase_date1");
                info_dateown.setText(info_dateown1);

                String info_address1 = data.getStringExtra("apartment_address1");
                info_address.setText(info_address1);





                Toast toast = Toast.makeText(getApplicationContext(), "Змінено!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 2222) {
            if(resultCode==RESULT_OK){
                String info_rooms1 = data.getStringExtra("apartment_room2");
                info_rooms.setText(info_rooms1);


                String info_floor1 = data.getStringExtra("apartment_floor2");
                info_floor.setText(info_floor1);

                String info_dateown1 = data.getStringExtra("apartment_purchase_date2");
                info_dateown.setText(info_dateown1);

                String info_address1 = data.getStringExtra("apartment_address2");
                info_address.setText(info_address1);





                Toast toast = Toast.makeText(getApplicationContext(), "Змінено!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 3333) {
            if(resultCode==RESULT_OK){
                String info_rooms1 = data.getStringExtra("apartment_room3");
                info_rooms.setText(info_rooms1);


                String info_floor1 = data.getStringExtra("apartment_floor3");
                info_floor.setText(info_floor1);

                String info_dateown1 = data.getStringExtra("apartment_purchase_date3");
                info_dateown.setText(info_dateown1);

                String info_address1 = data.getStringExtra("apartment_address3");
                info_address.setText(info_address1);





                Toast toast = Toast.makeText(getApplicationContext(), "Змінено!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 4444) {
            if(resultCode==RESULT_OK){
                String info_rooms1 = data.getStringExtra("apartment_room4");
                info_rooms.setText(info_rooms1);


                String info_floor1 = data.getStringExtra("apartment_floor4");
                info_floor.setText(info_floor1);

                String info_dateown1 = data.getStringExtra("apartment_purchase_date4");
                info_dateown.setText(info_dateown1);

                String info_address1 = data.getStringExtra("apartment_address4");
                info_address.setText(info_address1);





                Toast toast = Toast.makeText(getApplicationContext(), "Змінено!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }


        if (requestCode == 111) {
            if(resultCode==RESULT_OK) {

                String uid = data.getStringExtra("uid1");
                info_uid.setText(uid);

                String client_name = data.getStringExtra("name1");


                    info_name.setText(client_name);


                    String client_surname = data.getStringExtra("surname1");
                    info_surname.setText(client_surname);

                    String client_patronymic = data.getStringExtra("patronymic1");
                    info_patronymic.setText(client_patronymic);

                    String client_number = data.getStringExtra("number1");
                    info_number.setText(client_number);

                    String client_datestart = data.getStringExtra("datestart1");
                    info_date_start.setText(client_datestart);

                    String client_dateend = data.getStringExtra("dateend1");
                    info_date_end.setText(client_dateend);

                    String client_pay = data.getStringExtra("sumpay1");
                    if (client_pay.isEmpty()) {

                        info_pay.setText(client_pay + "     " + 0 + " Грн.");

                    } else {
                        info_pay.setText(client_pay + " Грн.");
                    }

                    String client_zastava = data.getStringExtra("zastava1");
                    info_zastava.setText(client_zastava + " Грн.");

                    String client_email = data.getStringExtra("email1");
                    info_email.setText(client_email);


                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("client_name1", client_name);
                    editor.putString("client_surname1", client_surname);
                    editor.putString("client_patronymic1", client_patronymic);
                    editor.putString("client_number1", client_number);
                    editor.putString("client_datestart1", client_datestart);
                    editor.putString("client_dateend1", client_dateend);
                    editor.putString("client_pay1", client_pay);
                    editor.putString("client_zastava1", client_zastava);
                    editor.putString("client_email1", client_email);

                    editor.apply();


                    delete_client.setVisibility(View.VISIBLE);
                    info_pay.setVisibility(View.VISIBLE);
                    find_client.setVisibility(View.INVISIBLE);
                    textView11.setVisibility(View.INVISIBLE);
                    add_client.setVisibility(View.INVISIBLE);

                    imageView4.setVisibility(View.VISIBLE);
                    apartment_edit_client.setVisibility(View.VISIBLE);

                    call.setVisibility(View.VISIBLE);
                    addphoto.setVisibility(View.VISIBLE);
                    whatsapp.setVisibility(View.VISIBLE);


                    textView15.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);

                    info_surname.setVisibility(View.VISIBLE);
                    info_name.setVisibility(View.VISIBLE);
                    info_patronymic.setVisibility(View.VISIBLE);
                    info_number.setVisibility(View.VISIBLE);
                    info_date_start.setVisibility(View.VISIBLE);
                    info_date_end.setVisibility(View.VISIBLE);
                    info_zastava.setVisibility(View.VISIBLE);

                    busynessid1();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uid1();
                        }
                    },100);

                    Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                    toast.show();
            }

        }

        if (requestCode == 222) {
            if(resultCode==RESULT_OK) {
                String client_name = data.getStringExtra("name2");

                    info_name.setText(client_name);


                    String client_surname = data.getStringExtra("surname2");
                    info_surname.setText(client_surname);

                    String client_patronymic = data.getStringExtra("patronymic2");
                    info_patronymic.setText(client_patronymic);

                    String client_number = data.getStringExtra("number2");
                    info_number.setText(client_number);

                    String client_datestart = data.getStringExtra("datestart2");
                    info_date_start.setText(client_datestart);

                    String client_dateend = data.getStringExtra("dateend2");
                    info_date_end.setText(client_dateend);

                    String client_pay = data.getStringExtra("sumpay2");
                    if (client_pay.isEmpty()) {

                        info_pay.setText(client_pay + "     " + 0 + " Грн.");

                    } else {
                        info_pay.setText(client_pay + " Грн.");
                    }

                    String client_zastava = data.getStringExtra("zastava2");
                    info_zastava.setText(client_zastava + " Грн.");

                    String client_email = data.getStringExtra("email2");
                    info_email.setText(client_email);


                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("client_name2", client_name);
                    editor.putString("client_surname2", client_surname);
                    editor.putString("client_patronymic2", client_patronymic);
                    editor.putString("client_number2", client_number);
                    editor.putString("client_datestart2", client_datestart);
                    editor.putString("client_dateend2", client_dateend);
                    editor.putString("client_pay2", client_pay);
                    editor.putString("client_zastava2", client_zastava);
                    editor.putString("client_email2", client_email);

                    editor.apply();


                    delete_client.setVisibility(View.VISIBLE);
                    info_pay.setVisibility(View.VISIBLE);
                    find_client.setVisibility(View.INVISIBLE);
                    textView11.setVisibility(View.INVISIBLE);
                    add_client.setVisibility(View.INVISIBLE);

                    call.setVisibility(View.VISIBLE);
                    addphoto.setVisibility(View.VISIBLE);
                    whatsapp.setVisibility(View.VISIBLE);

                    imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);


                    textView15.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);

                    info_surname.setVisibility(View.VISIBLE);
                    info_name.setVisibility(View.VISIBLE);
                    info_patronymic.setVisibility(View.VISIBLE);
                    info_number.setVisibility(View.VISIBLE);
                    info_date_start.setVisibility(View.VISIBLE);
                    info_date_end.setVisibility(View.VISIBLE);
                    info_zastava.setVisibility(View.VISIBLE);

                    busynessid2();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uid2();
                        }
                    },100);

                    Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                    toast.show();
                }

        }

        if (requestCode == 333) {
            if(resultCode==RESULT_OK) {
                String client_name = data.getStringExtra("name3");

                    info_name.setText(client_name);


                    String client_surname = data.getStringExtra("surname3");
                    info_surname.setText(client_surname);

                    String client_patronymic = data.getStringExtra("patronymic3");
                    info_patronymic.setText(client_patronymic);

                    String client_number = data.getStringExtra("number3");
                    info_number.setText(client_number);

                    String client_datestart = data.getStringExtra("datestart3");
                    info_date_start.setText(client_datestart);

                    String client_dateend = data.getStringExtra("dateend3");
                    info_date_end.setText(client_dateend);

                    String client_pay = data.getStringExtra("sumpay3");
                    if (client_pay.isEmpty()) {

                        info_pay.setText(client_pay + "     " + 0 + " Грн.");

                    } else {
                        info_pay.setText(client_pay + " Грн.");
                    }

                    String client_zastava = data.getStringExtra("zastava3");
                    info_zastava.setText(client_zastava + " Грн.");

                    String client_email = data.getStringExtra("email3");
                    info_email.setText(client_email);


                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("client_name3", client_name);
                    editor.putString("client_surname3", client_surname);
                    editor.putString("client_patronymic3", client_patronymic);
                    editor.putString("client_number3", client_number);
                    editor.putString("client_datestart3", client_datestart);
                    editor.putString("client_dateend3", client_dateend);
                    editor.putString("client_pay3", client_pay);
                    editor.putString("client_zastava3", client_zastava);
                    editor.putString("client_email3", client_email);

                    editor.apply();

                    delete_client.setVisibility(View.VISIBLE);
                    info_pay.setVisibility(View.VISIBLE);
                    find_client.setVisibility(View.INVISIBLE);
                    textView11.setVisibility(View.INVISIBLE);
                    add_client.setVisibility(View.INVISIBLE);

                    imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);

                    call.setVisibility(View.VISIBLE);
                    addphoto.setVisibility(View.VISIBLE);
                    whatsapp.setVisibility(View.VISIBLE);


                    textView15.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);

                    info_surname.setVisibility(View.VISIBLE);
                    info_name.setVisibility(View.VISIBLE);
                    info_patronymic.setVisibility(View.VISIBLE);
                    info_number.setVisibility(View.VISIBLE);
                    info_date_start.setVisibility(View.VISIBLE);
                    info_date_end.setVisibility(View.VISIBLE);
                    info_zastava.setVisibility(View.VISIBLE);

                    busynessid3();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uid3();
                        }
                    },100);

                    Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                    toast.show();
                }

        }
        if (requestCode == 444) {
            if(resultCode==RESULT_OK) {
                String client_name = data.getStringExtra("name4");


                    info_name.setText(client_name);


                    String client_surname = data.getStringExtra("surname4");
                    info_surname.setText(client_surname);

                    String client_patronymic = data.getStringExtra("patronymic4");
                    info_patronymic.setText(client_patronymic);

                    String client_number = data.getStringExtra("number4");
                    info_number.setText(client_number);

                    String client_datestart = data.getStringExtra("datestart4");
                    info_date_start.setText(client_datestart);

                    String client_dateend = data.getStringExtra("dateend4");
                    info_date_end.setText(client_dateend);

                    String client_pay = data.getStringExtra("sumpay4");
                    if (client_pay.isEmpty()) {

                        info_pay.setText(client_pay + "     " + 0 + " Грн.");

                    } else {
                        info_pay.setText(client_pay + " Грн.");
                    }

                    String client_zastava = data.getStringExtra("zastava4");
                    info_zastava.setText(client_zastava + " Грн.");

                    String client_email = data.getStringExtra("email4");
                    info_email.setText(client_email);


                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("client_name4", client_name);
                    editor.putString("client_surname4", client_surname);
                    editor.putString("client_patronymic4", client_patronymic);
                    editor.putString("client_number4", client_number);
                    editor.putString("client_datestart4", client_datestart);
                    editor.putString("client_dateend4", client_dateend);
                    editor.putString("client_pay4", client_pay);
                    editor.putString("client_zastava4", client_zastava);
                    editor.putString("client_email4", client_email);

                    editor.apply();

                    delete_client.setVisibility(View.VISIBLE);
                    info_pay.setVisibility(View.VISIBLE);
                    find_client.setVisibility(View.INVISIBLE);
                    textView11.setVisibility(View.INVISIBLE);
                    add_client.setVisibility(View.INVISIBLE);

                    imageView4.setVisibility(View.VISIBLE);
                apartment_edit_client.setVisibility(View.VISIBLE);

                    call.setVisibility(View.VISIBLE);
                    addphoto.setVisibility(View.VISIBLE);
                    whatsapp.setVisibility(View.VISIBLE);


                    textView15.setVisibility(View.VISIBLE);
                    textView16.setVisibility(View.VISIBLE);
                    textView10.setVisibility(View.VISIBLE);
                    textView20.setVisibility(View.VISIBLE);

                    info_surname.setVisibility(View.VISIBLE);
                    info_name.setVisibility(View.VISIBLE);
                    info_patronymic.setVisibility(View.VISIBLE);
                    info_number.setVisibility(View.VISIBLE);
                    info_date_start.setVisibility(View.VISIBLE);
                    info_date_end.setVisibility(View.VISIBLE);
                    info_zastava.setVisibility(View.VISIBLE);

                    busynessid4();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uid4();
                        }
                    },100);

                    Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                    toast.show();
                }

        }




    }





    @Override
    public void onClick(View v) {



    }



    @Override
    protected void onResume() {

        super.onResume();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    private void uid1(){

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
        Query query4 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart1");
        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                        String uid = snapShot.getKey();

                        info_uid.setText(uid);



                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void uid2(){

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
        Query query4 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart2");
        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                        String uid = snapShot.getKey();

                        info_uid.setText(uid);



                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void uid3(){

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
        Query query4 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart3");
        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                        String uid = snapShot.getKey();

                        info_uid.setText(uid);



                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void uid4(){

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(auth);
        Query query4 = databaseReference.child("New_Client").orderByChild("busyness").equalTo("idApart4");
        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {


                        String uid = snapShot.getKey();

                        info_uid.setText(uid);



                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    private  void busynessid1(){


        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        _Name = info_name.getText().toString();



        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);
        Query query111 = reference1.child("New_Client").orderByChild("name").equalTo(_Name);
        query111.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                        String key = snapShot.getKey();

                        reference1.child("New_Client").child(key).child("busyness").setValue("idApart1");

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    private  void busynessid2(){

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        _Name = info_name.getText().toString();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);
        Query query222 = reference1.child("New_Client").orderByChild("name").equalTo(_Name);
        query222.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                        String key = snapShot.getKey();

                        reference1.child("New_Client").child(key).child("busyness").setValue("idApart2");

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private  void busynessid3(){


        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        _Name = info_name.getText().toString();


        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);
        Query query333 = reference1.child("New_Client").orderByChild("name").equalTo(_Name);
        query333.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                        String key = snapShot.getKey();

                        reference1.child("New_Client").child(key).child("busyness").setValue("idApart3");

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private  void busynessid4(){


        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        _Name = info_name.getText().toString();

        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);
        Query query444 = reference1.child("New_Client").orderByChild("name").equalTo(_Name);
        query444.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                        String key = snapShot.getKey();

                        reference1.child("New_Client").child(key).child("busyness").setValue("idApart4");

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private  void NObusyness() {


        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        _Name = info_name.getText().toString();


        DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference(auth);
        Query query11 = reference1.child("New_Client").orderByChild("name").equalTo(_Name);
        query11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {

                        String key = snapShot.getKey();

                        reference1.child("New_Client").child(key).child("busyness").setValue("0");

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }






}