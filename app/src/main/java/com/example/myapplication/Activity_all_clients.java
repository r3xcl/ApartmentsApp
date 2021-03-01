package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import db.ClientClass;
import db.Client_adapter;

public class Activity_all_clients extends AppCompatActivity {

    /*private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private DatabaseReference myDataBase ;
    private String New_Client = "New_Client";

    private List<ClientClass> listTemp;*/

    RecyclerView recView;
    Client_adapter adapter;
    FloatingActionButton fb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));

        setContentView(R.layout.activity_all_clients);
        setTitle("Пошук");

        recView=(RecyclerView)findViewById(R.id.recyclerView);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ClientClass> options =
         new FirebaseRecyclerOptions.Builder<ClientClass>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("New_Client"),ClientClass.class).build();

        adapter = new Client_adapter(options);
        recView.setAdapter(adapter);

     fb = (FloatingActionButton)findViewById(R.id.fadd);
     fb.setOnClickListener(v -> {
       startActivity(new Intent(getApplicationContext(),Activity_Add_Client.class));
     });

        /*listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.design_list,listData);

        listView.setAdapter(adapter);

        myDataBase = FirebaseDatabase.getInstance().getReference(New_Client);

        getDataFromDB();

        setOnClickItem();*/
    }




@Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    /*private void getDataFromDB(){

        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 if(listData.size() > 0 )listData.clear();
                 if(listTemp.size() > 0 )listTemp.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    ClientClass clientClass = ds.getValue(ClientClass.class);
                    assert clientClass != null;
                    listData.add(clientClass.surname +" "+ clientClass.name +" "+ clientClass.patronymic);
                    listTemp.add(clientClass);


                }
              adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        myDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             ClientClass clientClass = listTemp.get(position);
                Intent i = new Intent(Activity_all_clients.this, Activity_show_allinfo_client.class);
                i.putExtra("surname",clientClass.surname);
                i.putExtra("name",clientClass.name);
                i.putExtra("patronymic",clientClass.patronymic);
                i.putExtra("number",clientClass.number);
                i.putExtra("datestart",clientClass.datestart);
                i.putExtra("dateend",clientClass.dateend);
                i.putExtra("sumpay",clientClass.pay);
                startActivity(i);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String s) {

                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<ClientClass> options =
                new FirebaseRecyclerOptions.Builder<ClientClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("New_Client").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), ClientClass.class)
                        .build();

        adapter=new Client_adapter(options);
        adapter.startListening();
        recView.setAdapter(adapter);

    }

    //ПРИ НАЖАТИИ НА ЭКРАН СКРЫВАЕМ КЛАВИАТУРУ --->

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



    // <---
}

