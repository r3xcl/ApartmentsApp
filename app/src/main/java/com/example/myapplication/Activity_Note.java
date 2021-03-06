package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.example.myapplication.note.Note;
import com.example.myapplication.note.NotesAdapter;
import com.example.myapplication.note.NotesDatabase;

import java.util.ArrayList;
import java.util.List;

public class Activity_Note extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD_NOTE = 1 ;

    private RecyclerView noteRecyclerView;

    private List<Note>noteList;
    private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__note);

        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПКУ

        ImageView imageAddNoteMain = findViewById(R.id.imageAddNoteMain);
        imageAddNoteMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(),CreateNoteActivity.class),REQUEST_CODE_ADD_NOTE);
            }
        });

        noteRecyclerView = findViewById(R.id.notesRecyclerView);
        noteRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        notesAdapter = new NotesAdapter(noteList);
        noteRecyclerView.setAdapter(notesAdapter);

        getNotes();
    }

    private void getNotes () {

        class GetNotesTask extends AsyncTask<Void,Void, List<Note>>{

            @Override
            protected  List<Note> doInBackground(Void... voids){
                return NotesDatabase
                        .getDataBase(getApplicationContext())
                        .noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes){

                super.onPostExecute(notes);
                if(noteList.size()==0){

                    noteList.addAll(notes);
                    notesAdapter.notifyDataSetChanged();

                }else{
                    noteList.add(0,notes.get(0));
                    notesAdapter.notifyItemInserted(0);
                }
                noteRecyclerView.smoothScrollToPosition(0);
            }

        }
        new GetNotesTask().execute();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_ADD_NOTE&&resultCode == RESULT_OK){

            getNotes();
        }
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