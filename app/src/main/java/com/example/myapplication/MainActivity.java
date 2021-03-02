package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    ImageButton add1,add2,add3,add4,add5,
            new_home,new_home2,new_home3,new_home4,new_home5,
            allclient;

    TextView add1_text,add2_text,add3_text,add4_text,add5_text,
            rooms1,floor1,dateown1,
            rooms2,floor2,dateown2,
            rooms3,floor3,dateown3,
            rooms4,floor4,dateown4,
            rooms5,floor5,dateown5;

     Vibrator vibr;

    private AlphaAnimation buttonClick = new AlphaAnimation(2F, 0.3F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        add1_text = (TextView) findViewById(R.id.add1_text);
        add2_text = (TextView) findViewById(R.id.add2_text);
        add3_text = (TextView) findViewById(R.id.add3_text);
        add4_text = findViewById(R.id.add4_text);
        add5_text = (TextView) findViewById(R.id.add5_text);

        rooms1 = (TextView) findViewById(R.id.rooms1);
        floor1 = (TextView) findViewById(R.id.floor1);
        dateown1 = (TextView) findViewById(R.id.dateown1);

        rooms2 = (TextView) findViewById(R.id.rooms2);
        floor2 = (TextView) findViewById(R.id.floor2);
        dateown2 = (TextView) findViewById(R.id.dateown2);

        rooms3 = (TextView) findViewById(R.id.rooms3);
        floor3 = (TextView) findViewById(R.id.floor3);
        dateown3 = (TextView) findViewById(R.id.dateown3);

        rooms4 = (TextView) findViewById(R.id.rooms4);
        floor4 = (TextView) findViewById(R.id.floor4);
        dateown4 = (TextView) findViewById(R.id.dateown4);

        rooms5 = (TextView) findViewById(R.id.rooms5);
        floor5 = (TextView) findViewById(R.id.floor5);
        dateown5 = (TextView) findViewById(R.id.dateown5);


        add1 = (ImageButton) findViewById(R.id.add1);
        add1.setOnClickListener(this);

        add2 = (ImageButton) findViewById(R.id.add2);
        add2.setOnClickListener(this);

        add3 = (ImageButton) findViewById(R.id.add3);
        add3.setOnClickListener(this);

        add4 = (ImageButton) findViewById(R.id.add4);
        add4.setOnClickListener(this);

        add5 = (ImageButton) findViewById(R.id.add5);
        add5.setOnClickListener(this);

        new_home = (ImageButton) findViewById(R.id.new_home);
        new_home.setOnClickListener(this);

        new_home2 = (ImageButton) findViewById(R.id.new_home2);
        new_home2.setOnClickListener(this);

        new_home3 = (ImageButton) findViewById(R.id.new_home3);
        new_home3.setOnClickListener(this);

        new_home4 = (ImageButton) findViewById(R.id.new_home4);
        new_home4.setOnClickListener(this);

        new_home5 = (ImageButton) findViewById(R.id.new_home5);
        new_home5.setOnClickListener(this);

        allclient = (ImageButton) findViewById(R.id.allclient);
        allclient.setOnClickListener(this);


       vibr = (Vibrator)getSystemService(VIBRATOR_SERVICE);

     add1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             vibr.vibrate(70);
             Intent intent = new Intent("1");
             startActivityForResult(intent,1);

         }
     });



        add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("2");
                startActivityForResult(intent,2);

            }
        });

        add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("3");
                startActivityForResult(intent,3);

            }
        });

        add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("4");
                startActivityForResult(intent,4);

            }
        });

        add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("5");
                startActivityForResult(intent,5);

            }
        });

        new_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("new_home1");

                intent.putExtra("address_1",add1_text.getText());
                intent.putExtra("rooms1",rooms1.getText());
                intent.putExtra("floor1",floor1.getText());
                intent.putExtra("dateown1",dateown1.getText());

                startActivity(intent);

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
                            
                            rooms1.setText("");
                            floor1.setText("");
                            dateown1.setText("");
                            
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

                intent.putExtra("address_2",add2_text.getText());
                intent.putExtra("rooms2",rooms2.getText());
                intent.putExtra("floor2",floor2.getText());
                intent.putExtra("dateown2",dateown2.getText());

                startActivity(intent);

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

                                rooms2.setText("");
                                floor2.setText("");
                                dateown2.setText("");

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

                intent.putExtra("address_3",add3_text.getText());
                intent.putExtra("rooms3",rooms3.getText());
                intent.putExtra("floor3",floor3.getText());
                intent.putExtra("dateown3",dateown3.getText());

                startActivity(intent);

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

                                rooms3.setText("");
                                floor3.setText("");
                                dateown3.setText("");

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

                intent.putExtra("address_4",add4_text.getText());
                intent.putExtra("rooms4",rooms4.getText());
                intent.putExtra("floor4",floor4.getText());
                intent.putExtra("dateown4",dateown4.getText());

                startActivity(intent);

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

                                rooms4.setText("");
                                floor4.setText("");
                                dateown4.setText("");

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

        new_home5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibr.vibrate(70);
                Intent intent = new Intent("new_home5");

                intent.putExtra("address_5",add5_text.getText());
                intent.putExtra("rooms5",rooms5.getText());
                intent.putExtra("floor5",floor5.getText());
                intent.putExtra("dateown5",dateown5.getText());

                startActivity(intent);

            }
        });

        new_home5.setOnLongClickListener(new View.OnLongClickListener() {
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
                                add5.setVisibility(View.VISIBLE);
                                add5_text.setText("");

                                rooms5.setText("");
                                floor5.setText("");
                                dateown5.setText("");

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

            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            if(resultCode==RESULT_OK){

                String rooms = data.getStringExtra("rooms1");
                rooms1.setText(rooms);

                String floor = data.getStringExtra("floor1");
                floor1.setText(floor);

                String dateown = data.getStringExtra("dateown1");
                dateown1.setText(dateown);

                String address = data.getStringExtra("address1");
                add1_text.setText(address);




                add1.setVisibility(View.GONE);

                Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
                toast.show();}
            }else if (requestCode == 2){
               if (resultCode==RESULT_OK){

                   String address = data.getStringExtra("address2");
                   add2_text.setText(address);

                   String rooms = data.getStringExtra("rooms2");
                   rooms2.setText(rooms);

                   String floor = data.getStringExtra("floor2");
                   floor2.setText(floor);

                   String dateown = data.getStringExtra("dateown2");
                   dateown2.setText(dateown);

            add2.setVisibility(View.GONE);

            Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
            toast.show();}
        }else if (requestCode == 3){
            if (resultCode==RESULT_OK){

                String address = data.getStringExtra("address3");
                add3_text.setText(address);

                String rooms = data.getStringExtra("rooms3");
                rooms3.setText(rooms);

                String floor = data.getStringExtra("floor3");
                floor3.setText(floor);

                String dateown = data.getStringExtra("dateown3");
                dateown3.setText(dateown);

            add3.setVisibility(View.GONE);

            Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
            toast.show();}
        }else if (requestCode == 4){
            if (resultCode==RESULT_OK){

                String address = data.getStringExtra("address4");
                add4_text.setText(address);

                String rooms = data.getStringExtra("rooms4");
                rooms4.setText(rooms);

                String floor = data.getStringExtra("floor4");
                floor4.setText(floor);

                String dateown = data.getStringExtra("dateown4");
                dateown4.setText(dateown);


            add4.setVisibility(View.GONE);

            Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
            toast.show();}
        }else if (requestCode == 5){
            if (resultCode==RESULT_OK){

                String address = data.getStringExtra("address5");
                add5_text.setText(address);

                String rooms = data.getStringExtra("rooms5");
                rooms5.setText(rooms);

                String floor = data.getStringExtra("floor5");
                floor5.setText(floor);

                String dateown = data.getStringExtra("dateown5");
                dateown5.setText(dateown);

            add5.setVisibility(View.GONE);

            Toast toast = Toast.makeText(getApplicationContext(), "Квартира додана!", Toast.LENGTH_SHORT);
            toast.show();}
        }


      }






        @Override
        public void onClick(View v){
        Intent intent;





        }

        public void onClickRead(View v){

         Intent i = new Intent(MainActivity.this, Activity_all_clients.class);
         startActivity(i);




      }

}