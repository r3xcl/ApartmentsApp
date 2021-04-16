package com.example.myapplication.note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.ActivityNote;
import com.example.myapplication.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditNote extends AppCompatActivity {

    Intent data;
    EditText medittitleofnote,meditcontentofnote;
    ImageView msaveeditnote,imageBack;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        medittitleofnote=findViewById(R.id.inputNoteTitleEdit);
        meditcontentofnote=findViewById(R.id.inputNoteTextEdit);
        msaveeditnote=findViewById(R.id.imageSaveEdit);
        imageBack=findViewById(R.id.imageBack);

        data=getIntent();

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);

        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        msaveeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String newtitle=medittitleofnote.getText().toString();
                String newcontent=meditcontentofnote.getText().toString();

                if(newtitle.isEmpty()||newcontent.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Введіть заголовок і текст!",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    DocumentReference documentReference=firebaseFirestore.collection(auth).document(firebaseUser.getUid()).collection("myNotes").document(data.getStringExtra("noteId"));
                    Map<String,Object> note=new HashMap<>();
                    note.put("title",newtitle);
                    note.put("content",newcontent);
                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Оновлено!",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Помилка оновлення!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String notetitle=data.getStringExtra("title");
        String notecontent=data.getStringExtra("content");
        meditcontentofnote.setText(notecontent);
        medittitleofnote.setText(notetitle);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
