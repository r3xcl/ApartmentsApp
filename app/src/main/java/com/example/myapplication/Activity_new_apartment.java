package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Activity_new_apartment extends AppCompatActivity implements View.OnClickListener {

    Button add_client,delete_client,find_client;

    ImageButton apartment_edit,call,money,addphoto;

    TextView info_address,info_patronymic,info_name,info_surname,info_number,
            info_date_start,info_rooms,info_floor,info_dateown,info_date_end,info_pay,textView9,textView11,textView13
            ,textView14,textView15,textView16,info_zastava,textView20;

    ImageView imageView4,image_client;





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


        call = (ImageButton)findViewById(R.id.call);
        call.setOnClickListener(this);

        addphoto = (ImageButton)findViewById(R.id.addphoto);
        addphoto.setOnClickListener(this);

        money = (ImageButton)findViewById(R.id.money);
        money.setOnClickListener(this);

        add_client = (Button) findViewById(R.id.add_client);
        add_client.setOnClickListener(this);

        find_client = (Button) findViewById(R.id.find_client);
        find_client.setOnClickListener(this);

        delete_client = (Button) findViewById(R.id.delete_client);
        delete_client.setOnClickListener(this);

        apartment_edit= (ImageButton) findViewById(R.id.apartment_edit);
        apartment_edit.setOnClickListener(this);

        call.setOnClickListener(v -> {
            String number = info_number.getText().toString();
            if (!TextUtils.isEmpty(number)){
                String dial = "tel: "+number;
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
            else {
                Toast.makeText(Activity_new_apartment.this," Номер телефона не найден!",Toast.LENGTH_SHORT).show();
            }

        });


        Intent intent = getIntent();
        String action = intent.getAction();


        if (action.equals("new_home1")) {

            info_address.setText(getIntent().getStringExtra("address_1"));

            info_rooms.setText(getIntent().getStringExtra("rooms1"));
            info_floor.setText(getIntent().getStringExtra("floor1"));
            info_dateown.setText(getIntent().getStringExtra("dateown1"));


            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client1");
                startActivityForResult(intent1,11);

            });

            apartment_edit.setOnClickListener(v -> {
                Intent intent3 = new Intent("apartment_edit1");
                startActivityForResult(intent3,1111);

            });

            find_client.setOnClickListener(v -> {
                Intent intent2 = new Intent("find_client1");
                startActivityForResult(intent2,111);

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

            info_rooms.setText(getIntent().getStringExtra("rooms2"));
            info_floor.setText(getIntent().getStringExtra("floor2"));
            info_dateown.setText(getIntent().getStringExtra("dateown2"));


            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client2");
                startActivityForResult(intent1,22);
            });

            apartment_edit.setOnClickListener(v -> {
                Intent intent3 = new Intent("apartment_edit2");
                startActivityForResult(intent3,2222);

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

            info_rooms.setText(getIntent().getStringExtra("rooms3"));
            info_floor.setText(getIntent().getStringExtra("floor3"));
            info_dateown.setText(getIntent().getStringExtra("dateown3"));



            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client3");
                startActivityForResult(intent1,33);
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

            info_rooms.setText(getIntent().getStringExtra("rooms4"));
            info_floor.setText(getIntent().getStringExtra("floor4"));
            info_dateown.setText(getIntent().getStringExtra("dateown4"));



            add_client.setOnClickListener(v -> {
                Intent intent1 = new Intent("new_client4");
                startActivityForResult(intent1,44);
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