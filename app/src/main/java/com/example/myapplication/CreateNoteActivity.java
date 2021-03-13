package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.note.Note;
import com.example.myapplication.note.NotesDatabase;
import com.example.myapplication.note.NoteDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText inputNoteTitle,inputNoteSubtitle,inputNoteText;
    private TextView textDataTime;

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

    }


    private void setViewUpdateNote(){




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