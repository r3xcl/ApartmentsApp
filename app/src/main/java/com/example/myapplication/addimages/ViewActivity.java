package com.example.myapplication.addimages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ViewActivity extends AppCompatActivity {

    Button btnDeletePhoto,downloadphotohome;

    TextView textphoto_activity;

    ImageButton back;

    ImageView image_single_view_activity;

    ProgressBar progressBarphotohome;

    DatabaseReference ref ,DataRef;

    StorageReference StorageRef;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo_home);

        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        btnDeletePhoto = findViewById(R.id.btnDeletePhoto);
        back = findViewById(R.id.back);

        textphoto_activity = findViewById(R.id.textphoto_activity);

        downloadphotohome = findViewById(R.id.downloadphotohome);

        progressBarphotohome = findViewById(R.id.progressBarphotohome);

        image_single_view_activity = findViewById(R.id.image_single_view_activity);

        ref = FirebaseDatabase.getInstance().getReference(auth).child("Image");

        String ImageKey = getIntent().getStringExtra("ImageKey");
        String id = getIntent().getStringExtra("id");

        DataRef = FirebaseDatabase.getInstance().getReference(auth).child("Image").child(id).child(ImageKey);

        StorageRef = FirebaseStorage.getInstance().getReference(auth).child("Image").child(ImageKey + ".jpg");

        ref.child(id).child(ImageKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    String ImageName = snapshot.child("ImageName").getValue().toString();
                    String ImageUrl = snapshot.child("ImageUrl").getValue().toString();

                    Picasso.get().load(ImageUrl).into(image_single_view_activity);

                    textphoto_activity.setText(ImageName);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDeletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnDeletePhoto.setVisibility(View.INVISIBLE);
                progressBarphotohome.setVisibility(View.VISIBLE);


                DataRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        StorageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(ViewActivity.this,"Фото видалено!",Toast.LENGTH_SHORT).show();

                                finish();

                            }
                        });

                    }
                });

            }
        });

        downloadphotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.child(id).child(ImageKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){


                            String ImageUrl = snapshot.child("ImageUrl").getValue().toString();


                            downloadFile(ViewActivity.this,"image",".jpg", Environment.DIRECTORY_DOWNLOADS,ImageUrl);

                            Toast.makeText(ViewActivity.this,"Завантажено!",Toast.LENGTH_SHORT).show();

                            finish();



                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }

    public  void downloadFile (Context context , String fileName , String fileExtension , String destinationDirectory , String url){

        DownloadManager  downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);



        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName + fileExtension);

        downloadManager.enqueue(request);

    }
}