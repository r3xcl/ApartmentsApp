package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class Activity_Note extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static  ArrayAdapter arrayAdapter;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.add_note)
        {

            Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
            startActivity(intent);
            return  true;
        }
        return false;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__note);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        setTitle("Нотатки");

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notes",null);



        if(set==null) {

            notes.add("Пример");
        }
        else{
            notes = new ArrayList<>(set);
        }

        ListView listView = (ListView)findViewById(R.id.listView);


          arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int itemToDelete = position;
                new AlertDialog.Builder(Activity_Note.this).setTitle("Видалити?").
                        setPositiveButton("Так", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(position);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(Activity_Note.notes);

                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        }).setNegativeButton("Ні",null)
                        .show();


                return true ;
            }
        });
    }
}