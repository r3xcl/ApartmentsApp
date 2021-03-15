package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.files.Files;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ActivityNewApartment extends AppCompatActivity implements View.OnClickListener {

    Button add_client, delete_client, find_client;

    ImageButton apartment_edit, call, money, addphoto, remont, map, archive, whatsapp ,files;

    TextView info_address, info_patronymic, info_name, info_surname, info_number,
            info_date_start, info_rooms, info_floor, info_dateown, info_date_end, info_pay
            , textView9, textView11, textView13, textView14, textView15, textView16, info_zastava
            , textView20, textView23;

    ImageView imageView4, image_client , delphoto_client;
    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_apartment);
        getSupportActionBar().hide(); //УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        info_address = (TextView) findViewById(R.id.info_address);
        info_patronymic = (TextView) findViewById(R.id.info_patronymic);
        info_name = (TextView) findViewById(R.id.info_name);
        info_surname = (TextView) findViewById(R.id.info_surname);
        info_number = (TextView) findViewById(R.id.info_number);
        info_date_start = (TextView) findViewById(R.id.info_date_start);
        info_date_end = (TextView) findViewById(R.id.info_date_end);
        info_pay = (TextView) findViewById(R.id.info_pay);
        info_zastava = (TextView) findViewById(R.id.info_zastava);


        textView23 = (TextView) findViewById(R.id.textView23);
        textView9 = (TextView) findViewById(R.id.textView9);
        textView11 = (TextView) findViewById(R.id.textView11);
        textView13 = (TextView) findViewById(R.id.textView13);
        textView14 = (TextView) findViewById(R.id.textView14);
        textView15 = (TextView) findViewById(R.id.textView15);
        textView16 = (TextView) findViewById(R.id.textView16);
        textView20 = (TextView) findViewById(R.id.textView20);

        imageView4 = (ImageView) findViewById(R.id.imageView4);
        image_client = (ImageView) findViewById(R.id.image_client);

        info_rooms = (TextView) findViewById(R.id.info_rooms);
        info_floor = (TextView) findViewById(R.id.info_floor);
        info_dateown = (TextView) findViewById(R.id.info_dateown);




        call = (ImageButton) findViewById(R.id.call);
        call.setOnClickListener(this);

        files = (ImageButton) findViewById(R.id.files);
        files.setOnClickListener(this);

        delphoto_client = (ImageView) findViewById(R.id.delphoto_client);
        delphoto_client.setOnClickListener(this);

        whatsapp = (ImageButton) findViewById(R.id.whatsapp);
        whatsapp.setOnClickListener(this);

        archive = (ImageButton) findViewById(R.id.archive);
        archive.setOnClickListener(this);

        remont = (ImageButton) findViewById(R.id.remont);
        remont.setOnClickListener(this);

        map = (ImageButton) findViewById(R.id.map);
        map.setOnClickListener(this);

        addphoto = (ImageButton) findViewById(R.id.addphoto);
        addphoto.setOnClickListener(this);

        money = (ImageButton) findViewById(R.id.money);
        money.setOnClickListener(this);

        add_client = (Button) findViewById(R.id.add_client);
        add_client.setOnClickListener(this);

        find_client = (Button) findViewById(R.id.find_client);
        find_client.setOnClickListener(this);

        delete_client = (Button) findViewById(R.id.delete_client);
        delete_client.setOnClickListener(this);

        apartment_edit = (ImageButton) findViewById(R.id.apartment_edit);
        apartment_edit.setOnClickListener(this);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(ActivityNewApartment.this);

        if(ActivityCompat.checkSelfPermission (ActivityNewApartment.this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ActivityNewApartment.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)==
                        PackageManager.PERMISSION_GRANTED )
        {

            getCurrentLocation();}


        delphoto_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delphoto_client.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.VISIBLE);
            }
        });


        call.setOnClickListener(v -> {
            String number = info_number.getText().toString();
            if (!TextUtils.isEmpty(number)) {
                String dial = "tel: " + number;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            } else {
                Toast.makeText(ActivityNewApartment.this, " Номера телефону не знайдено!", Toast.LENGTH_SHORT).show();
            }

        });


        Intent intent = getIntent();
        String action = intent.getAction();



        whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ActivityNewApartment.this,WhatsApp.class);
                intent.putExtra("number",info_number.getText().toString());
                startActivity(intent);
            }
        });



        if (action.equals("new_home1")) {


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



                        info_address.setText(address);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);



                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

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

            remont.setOnClickListener(v -> {

                Intent intent4 = new Intent("remont1");
                startActivityForResult(intent4,101);
            });

            money.setOnClickListener(v -> {
                Intent intent1 = new Intent("pay1");
                startActivityForResult(intent1,11111);

            });

            apartment_edit.setOnClickListener(v -> {
                Intent intent3 = new Intent("apartment_edit1");
                startActivityForResult(intent3,1111);

            });


            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client1");
                startActivityForResult(intent2,111);

            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ActivityCompat.checkSelfPermission (ActivityNewApartment.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(ActivityNewApartment.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)==
                                    PackageManager.PERMISSION_GRANTED )
                    {

                        getCurrentLocation();

                        String sSource = info_address.getText().toString().trim();
                        String sDestination = textView23.getText().toString().trim();

                        DisplayTrack (sDestination,sSource);


                    }
                    else {

                        ActivityCompat.requestPermissions(ActivityNewApartment.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                ,Manifest.permission.ACCESS_COARSE_LOCATION},100);



                    }

                }

            });



            delete_client.setOnClickListener(v -> {

                info_surname.setText("");
                info_name.setText("");
                info_patronymic.setText("");
                info_number.setText("");
                info_date_start.setText("");
                info_date_end.setText("");
                info_pay.setText("");
                info_zastava.setText("");
                textView20.setVisibility(View.INVISIBLE);
                call.setVisibility(View.INVISIBLE);
                money.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);

                textView9.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                textView13.setVisibility(View.INVISIBLE);
                textView14.setVisibility(View.INVISIBLE);
                textView15.setVisibility(View.INVISIBLE);
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

            addphoto.setOnClickListener(v->  {

                Intent imagePick = new Intent(Intent.ACTION_PICK);
                imagePick.setType("image/*");
                startActivityForResult(imagePick,0);

            });
        }

        if (action.equals("new_home2")) {
            info_address.setText(getIntent().getStringExtra("address_2"));



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


                        info_address.setText(address);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client2");
                startActivityForResult(intent1,22);
            });

            remont.setOnClickListener(v -> {

                Intent intent4 = new Intent("remont2");
                startActivityForResult(intent4,202);
            });

            apartment_edit.setOnClickListener(v -> {
                Intent intent3 = new Intent("apartment_edit2");
                startActivityForResult(intent3,2222);

            });

            archive.setOnClickListener(v -> {
                Intent intent5 = new Intent("archive2");
                startActivityForResult(intent5,992);

            });

            files.setOnClickListener(v -> {
                Intent intent12 = new Intent("files2");
                startActivityForResult(intent12, 201);
            });

            money.setOnClickListener(v -> {
                Intent intent1 = new Intent("pay2");
                startActivityForResult(intent1,22222);

            });

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(ActivityCompat.checkSelfPermission (ActivityNewApartment.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(ActivityNewApartment.this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)==
                                    PackageManager.PERMISSION_GRANTED )
                    {

                        getCurrentLocation();

                        String sSource = info_address.getText().toString().trim();
                        String sDestination = textView23.getText().toString().trim();

                        DisplayTrack (sDestination,sSource);


                    }
                    else {

                        ActivityCompat.requestPermissions(ActivityNewApartment.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                                ,Manifest.permission.ACCESS_COARSE_LOCATION},100);



                    }

                }

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client2");
                startActivityForResult(intent2,222);

            });

            delete_client.setOnClickListener(v -> {

                info_surname.setText("");
                info_name.setText("");
                info_patronymic.setText("");
                info_number.setText("");
                info_date_start.setText("");
                info_date_end.setText("");
                info_pay.setText("");

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                call.setVisibility(View.INVISIBLE);
                money.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);

                info_zastava.setText("");
                textView20.setVisibility(View.INVISIBLE);
                textView9.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                textView13.setVisibility(View.INVISIBLE);
                textView14.setVisibility(View.INVISIBLE);
                textView15.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });

            addphoto.setOnClickListener(v->  {

                Intent imagePick = new Intent(Intent.ACTION_PICK);
                imagePick.setType("image/*");
                startActivityForResult(imagePick,0);

            });
        }

        if (action.equals("new_home3")) {
            info_address.setText(getIntent().getStringExtra("address_3"));

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


                        info_address.setText(address);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

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

            remont.setOnClickListener(v -> {

                Intent intent4 = new Intent("remont3");
                startActivityForResult(intent4,303);
            });

            money.setOnClickListener(v -> {
                Intent intent1 = new Intent("pay3");
                startActivityForResult(intent1,33333);

            });



            apartment_edit.setOnClickListener(v -> {
                Intent intent3 = new Intent("apartment_edit3");
                startActivityForResult(intent3,3333);

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client3");
                startActivityForResult(intent2,333);

            });

            delete_client.setOnClickListener(v -> {

                info_surname.setText("");
                info_name.setText("");
                info_patronymic.setText("");
                info_number.setText("");
                info_date_start.setText("");
                info_date_end.setText("");
                info_pay.setText("");
                info_zastava.setText("");
                textView20.setVisibility(View.INVISIBLE);

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                call.setVisibility(View.INVISIBLE);
                money.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);

                textView9.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                textView13.setVisibility(View.INVISIBLE);
                textView14.setVisibility(View.INVISIBLE);
                textView15.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });

            addphoto.setOnClickListener(v->  {

                Intent imagePick = new Intent(Intent.ACTION_PICK);
                imagePick.setType("image/*");
                startActivityForResult(imagePick,0);

            });
        }

        if (action.equals("new_home4")) {
            info_address.setText(getIntent().getStringExtra("address_4"));

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


                        info_address.setText(address);
                        info_dateown.setText(dateown);
                        info_floor.setText(floor);
                        info_rooms.setText(rooms);


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client4");
                startActivityForResult(intent1,44);
            });

            remont.setOnClickListener(v -> {

                Intent intent4 = new Intent("remont4");
                startActivityForResult(intent4,404);
            });

            files.setOnClickListener(v -> {
                Intent intent12 = new Intent("files4");
                startActivityForResult(intent12, 203);
            });

            money.setOnClickListener(v -> {
                Intent intent1 = new Intent("pay4");
                startActivityForResult(intent1,44444);

            });

            archive.setOnClickListener(v -> {
                Intent intent5 = new Intent("archive4");
                startActivityForResult(intent5,994);

            });

            apartment_edit.setOnClickListener(v -> {
                Intent intent3 = new Intent("apartment_edit4");
                startActivityForResult(intent3,4444);

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client4");
                startActivityForResult(intent2,444);

            });

            delete_client.setOnClickListener(v -> {

                info_surname.setText("");
                info_name.setText("");
                info_patronymic.setText("");
                info_number.setText("");
                info_date_start.setText("");
                info_date_end.setText("");
                info_pay.setText("");
                info_zastava.setText("");
                textView20.setVisibility(View.INVISIBLE);

                call.setVisibility(View.INVISIBLE);
                money.setVisibility(View.INVISIBLE);
                addphoto.setVisibility(View.INVISIBLE);
                whatsapp.setVisibility(View.INVISIBLE);

                delete_client.setVisibility(View.INVISIBLE);
                find_client.setVisibility(View.VISIBLE);
                add_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.INVISIBLE);

                textView9.setVisibility(View.INVISIBLE);
                textView11.setVisibility(View.INVISIBLE);
                textView13.setVisibility(View.INVISIBLE);
                textView14.setVisibility(View.INVISIBLE);
                textView15.setVisibility(View.INVISIBLE);
                textView16.setVisibility(View.INVISIBLE);
                textView20.setVisibility(View.INVISIBLE);

                info_surname.setVisibility(View.INVISIBLE);
                info_name.setVisibility(View.INVISIBLE);
                info_patronymic.setVisibility(View.INVISIBLE);
                info_number.setVisibility(View.INVISIBLE);
                info_date_start.setVisibility(View.INVISIBLE);
                info_date_end.setVisibility(View.INVISIBLE);
                info_zastava.setVisibility(View.INVISIBLE);

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря видалено!", Toast.LENGTH_SHORT);
                toast.show();
            });

            addphoto.setOnClickListener(v->  {

                Intent imagePick = new Intent(Intent.ACTION_PICK);
                imagePick.setType("image/*");
                startActivityForResult(imagePick,0);

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
                                Location location1 = locationResult.getLastLocation();

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


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {

            if(resultCode == RESULT_OK){
                try {

                    //Получаем URI изображения, преобразуем его в Bitmap
                    //объект и отображаем в элементе ImageView нашего интерфейса:
                    final Uri imageUri =  data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    image_client.setImageBitmap(selectedImage);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                addphoto.setVisibility(View.INVISIBLE);
                image_client.setVisibility(View.VISIBLE);
                delphoto_client.setVisibility(View.VISIBLE);
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

                String client_date_end = data.getStringExtra("client_dateend1");
                info_date_end.setText(client_date_end);

                String client_pay = data.getStringExtra("client_pay1");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info1");
                info_zastava.setText(client_zastava + " Грн.");


                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);


                imageView4.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

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

                String client_pay = data.getStringExtra("client_pay2");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info2");
                info_zastava.setText(client_zastava + " Грн.");


                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

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

                String client_pay = data.getStringExtra("client_pay3");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info3");
                info_zastava.setText(client_zastava + " Грн.");

                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

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

                String client_pay = data.getStringExtra("client_pay4");
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("client_info4");
                info_zastava.setText(client_zastava + " Грн.");

                delete_client.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);
                info_pay.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

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
            if(resultCode==RESULT_OK){

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
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("zastava1");
                info_zastava.setText(client_zastava + " Грн.");



                delete_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);


                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 222) {
            if(resultCode==RESULT_OK){
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
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("zastava2");
                info_zastava.setText(client_zastava + " Грн.");


                delete_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        if (requestCode == 333) {
            if(resultCode==RESULT_OK){
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
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("zastava3");
                info_zastava.setText(client_zastava + " Грн.");


                delete_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        if (requestCode == 444) {
            if(resultCode==RESULT_OK){
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
                info_pay.setText(client_pay + " Грн.");

                String client_zastava = data.getStringExtra("zastava4");
                info_zastava.setText(client_zastava + " Грн.");


                delete_client.setVisibility(View.VISIBLE);
                info_pay.setVisibility(View.VISIBLE);
                find_client.setVisibility(View.INVISIBLE);
                add_client.setVisibility(View.INVISIBLE);

                imageView4.setVisibility(View.VISIBLE);

                call.setVisibility(View.VISIBLE);
                money.setVisibility(View.VISIBLE);
                addphoto.setVisibility(View.VISIBLE);
                whatsapp.setVisibility(View.VISIBLE);

                textView9.setVisibility(View.VISIBLE);
                textView11.setVisibility(View.VISIBLE);
                textView13.setVisibility(View.VISIBLE);
                textView14.setVisibility(View.VISIBLE);
                textView15.setVisibility(View.VISIBLE);
                textView16.setVisibility(View.VISIBLE);
                textView20.setVisibility(View.VISIBLE);

                info_surname.setVisibility(View.VISIBLE);
                info_name.setVisibility(View.VISIBLE);
                info_patronymic.setVisibility(View.VISIBLE);
                info_number.setVisibility(View.VISIBLE);
                info_date_start.setVisibility(View.VISIBLE);
                info_date_end.setVisibility(View.VISIBLE);
                info_zastava.setVisibility(View.VISIBLE);

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



}