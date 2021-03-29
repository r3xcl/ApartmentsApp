package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.example.myapplication.note.Note;
import com.example.myapplication.note.NotesDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText inputNoteTitle,inputNoteSubtitle,inputNoteText;
    private TextView textDataTime;
    private ImageView delNote;

    private Note alreadyAvailableNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        getSupportActionBar().hide();//УБИРАЕМ ВЕРХНЮЮ ШАПК

        ImageView imageBack = findViewById(R.id.imageBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        inputNoteSubtitle = findViewById(R.id.inputNoteSubtitle);
        inputNoteTitle = findViewById(R.id.inputNoteTitle);
        inputNoteText = findViewById(R.id.inputNoteText);
        delNote = findViewById(R.id.imageView14);

        textDataTime = findViewById(R.id.textDateTime);

        textDataTime.setText(

                    new SimpleDateFormat("EEEE,dd MMMM yyyy HH:mm a", Locale.getDefault()).format(new Date())
        );

        ImageView imageSave = findViewById(R.id.imageSave);
        imageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        if(getIntent().getBooleanExtra("update",false)){

            alreadyAvailableNote = (Note) getIntent().getSerializableExtra("note");
            setViewUpdateNote();

            inputNoteTitle.setText(alreadyAvailableNote.getTitle());
            inputNoteText.setText(alreadyAvailableNote.getNoteText());
            inputNoteSubtitle.setText(alreadyAvailableNote.getSubtitle());
            textDataTime.setText(alreadyAvailableNote.getDateTime());

        }

        if(alreadyAvailableNote!= null){

            delNote.setVisibility(View.VISIBLE);
            delNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    class DeleteNote extends AsyncTask<Void,Void,Void>{


                        @Override
                        protected Void doInBackground(Void... voids) {
                            NotesDatabase.getDataBase(getApplicationContext()).noteDao().deleteNote(alreadyAvailableNote);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid){

                            super.onPostExecute(aVoid);
                            Intent intent = new Intent();
                            intent.putExtra("isNoteDel",true);
                            setResult(RESULT_OK,intent);
                            finish();

                        }
                    }
                    new DeleteNote().execute();
                }
            });

        }

    }


    private void setViewUpdateNote(){


        inputNoteSubtitle.setText(alreadyAvailableNote.getSubtitle());
        inputNoteText.setText(alreadyAvailableNote.getNoteText());
        inputNoteTitle.setText(alreadyAvailableNote.getTitle());


    }


    private  void saveNote(){

        if (inputNoteTitle.getText().toString().trim().isEmpty()){

            Toast.makeText(this,"Заголовок порожній!",Toast.LENGTH_SHORT).show();
            return;

        }else if(inputNoteSubtitle.getText().toString().trim().isEmpty()&&inputNoteText
        .getText().toString().trim().isEmpty()){

            Toast.makeText(this,"Введіть текст нотатку!",Toast.LENGTH_SHORT).show();
            return;

        }



        final Note note = new Note();
        note.setTitle(inputNoteTitle.getText().toString());
        note.setSubtitle(inputNoteSubtitle.getText().toString());
        note.setNoteText(inputNoteText.getText().toString());
        note.setDateTime(textDataTime.getText().toString());

        if(alreadyAvailableNote!=null){

            note.setId(alreadyAvailableNote.getId());

        }

        @SuppressLint("StaticFieldLeak")
        class  SaveNoteTask extends AsyncTask<Void,Void,Void>{

            @Override
            protected  Void doInBackground(Void... voids){

                NotesDatabase.getDataBase(getApplicationContext()).noteDao().insertNote(note);
                return null;

            }

            @Override
            protected void onPostExecute(Void aVoid){

                super.onPostExecute(aVoid);
                Intent intent = new Intent();

                setResult(RESULT_OK,intent);
                finish();

            }
        }

        new SaveNoteTask().execute();

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