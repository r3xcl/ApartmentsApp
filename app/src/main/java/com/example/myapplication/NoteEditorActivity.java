package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
         int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        setTitle("Додати/Редагувати");

        EditText editNote = (EditText)findViewById(R.id.editTextTextMultiLine);

        Intent intent = getIntent();
       noteId = intent.getIntExtra("noteId",-1);

        if(noteId != -1){

            editNote.setText(Activity_Note.notes.get(noteId));
        }else {
            Activity_Note.notes.add("");
            noteId = Activity_Note.notes.size()-1;
            Activity_Note.arrayAdapter.notifyDataSetChanged();
        }

        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
             Activity_Note.notes.set(noteId,String.valueOf(s));
                Activity_Note.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.myapplication", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(Activity_Note.notes);

                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}