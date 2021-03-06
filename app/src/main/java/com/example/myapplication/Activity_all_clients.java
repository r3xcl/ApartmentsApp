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
import android.widget.Toast;

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
         Intent intent = new Intent("add_client");
       startActivityForResult(intent,1);
     });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            if(resultCode == RESULT_OK){
                Toast toast = Toast.makeText(getApplicationContext(), "Орендаря додано!", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
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

