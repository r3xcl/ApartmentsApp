package com.example.myapplication.addimages;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class AddImage extends AppCompatActivity  {

    private static final int REQUEST_CODE_IMAGE = 101;
    EditText textnamephoto;
    ImageView showaddphoto,addphotocamera,addphotogallery;
    Button uploadphotohome;


    Uri imageUri;
    boolean isImageAdded=false;

    DatabaseReference  DataRef;
    StorageReference StorageRef;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_image);

        getSupportActionBar().hide();

        textnamephoto = findViewById(R.id.textnamephoto);

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        showaddphoto = findViewById(R.id.showaddphoto);
        addphotocamera = findViewById(R.id.addphotocamera);
        addphotogallery = findViewById(R.id.addphotogallery);

        uploadphotohome = findViewById(R.id.uploadphotohome);



        DataRef = FirebaseDatabase.getInstance().getReference(auth).child("Image");
        StorageRef = FirebaseStorage.getInstance().getReference(auth).child("Image");

        Intent intent = getIntent();
        String action = intent.getAction();


        addphotogallery.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE_IMAGE);
            }
        });

        if(ContextCompat.checkSelfPermission(AddImage.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(AddImage.this,new String[]{

               Manifest.permission.CAMERA

            },2);

        }

        addphotocamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,2);

            }
        });


        if (action.equals("addimage1")) {
            uploadphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String imageName = textnamephoto.getText().toString();

                    if (isImageAdded != false && !textnamephoto.getText().toString().equals("")) {

                        uploadImage1(imageName);

                    } else if (isImageAdded == false) {

                        Toast.makeText(AddImage.this, "Оберіть фото!", Toast.LENGTH_SHORT).show();

                    } else if (textnamephoto.getText().toString().equals("")) {


                        Toast.makeText(AddImage.this, "Введіть назву фото!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (action.equals("addimage2")) {
            uploadphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String imageName = textnamephoto.getText().toString();

                    if (isImageAdded != false && !textnamephoto.getText().toString().equals("")) {

                        uploadImage2(imageName);

                    } else if (isImageAdded == false) {

                        Toast.makeText(AddImage.this, "Оберіть фото!", Toast.LENGTH_SHORT).show();

                    } else if (textnamephoto.getText().toString().equals("")) {


                        Toast.makeText(AddImage.this, "Введіть назву фото!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (action.equals("addimage3")) {
            uploadphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String imageName = textnamephoto.getText().toString();

                    if (isImageAdded != false && !textnamephoto.getText().toString().equals("")) {

                        uploadImage3(imageName);

                    } else if (isImageAdded == false) {

                        Toast.makeText(AddImage.this, "Оберіть фото!", Toast.LENGTH_SHORT).show();

                    } else if (textnamephoto.getText().toString().equals("")) {


                        Toast.makeText(AddImage.this, "Введіть назву фото!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (action.equals("addimage4")) {
            uploadphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String imageName = textnamephoto.getText().toString();

                    if (isImageAdded != false && !textnamephoto.getText().toString().equals("")) {

                        uploadImage4(imageName);

                    } else if (isImageAdded == false) {

                        Toast.makeText(AddImage.this, "Оберіть фото!", Toast.LENGTH_SHORT).show();

                    } else if (textnamephoto.getText().toString().equals("")) {


                        Toast.makeText(AddImage.this, "Введіть назву фото!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    private void uploadImage1(String imageName) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Файл завантажується!");
        pd.show();

        final String key = DataRef.push().getKey();
        StorageRef.child(key+".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                StorageRef.child(key+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        pd.dismiss();

                        HashMap hashMap = new HashMap();
                        hashMap.put("ImageName",imageName);
                        hashMap.put("ImageID","apartment1");
                        hashMap.put("ImageUrl",uri.toString());

                        DataRef.child("apartment1").child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(AddImage.this,"Фото додано успішно!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {


                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pd.setMessage("Завантаження :" + " " + (int) percent + "%");

            }
        });



    }

    private void uploadImage2(String imageName) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Файл завантажується!");
        pd.show();

        final String key = DataRef.push().getKey();
        StorageRef.child(key + ".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                StorageRef.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        HashMap hashMap = new HashMap();
                        hashMap.put("ImageName", imageName);
                        hashMap.put("ImageID", "apartment2");
                        hashMap.put("ImageUrl", uri.toString());

                        pd.dismiss();

                        DataRef.child("apartment2").child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(AddImage.this, "Фото додано успішно!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {


                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pd.setMessage("Завантаження :" + " " + (int) percent + "%");

            }
        });
    }

    private void uploadImage3(String imageName) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Файл завантажується!");
        pd.show();

        final String key = DataRef.push().getKey();
        StorageRef.child(key + ".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                StorageRef.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        HashMap hashMap = new HashMap();
                        hashMap.put("ImageName", imageName);
                        hashMap.put("ImageID", "apartment3");
                        hashMap.put("ImageUrl", uri.toString());

                        pd.dismiss();

                        DataRef.child("apartment3").child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(AddImage.this, "Фото додано успішно!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {


                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pd.setMessage("Завантаження :" + " " + (int) percent + "%");

            }
        });
    }

    private void uploadImage4(String imageName) {

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Файл завантажується!");
        pd.show();

        final String key = DataRef.push().getKey();
        StorageRef.child(key + ".jpg").putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                StorageRef.child(key + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        HashMap hashMap = new HashMap();
                        hashMap.put("ImageName", imageName);
                        hashMap.put("ImageID", "apartment4");
                        hashMap.put("ImageUrl", uri.toString());

                        pd.dismiss();

                        DataRef.child("apartment4").child(key).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(AddImage.this, "Фото додано успішно!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                pd.setMessage("Завантаження :" + " " + (int) percent + "%");
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_IMAGE && data!=null)
        {

            imageUri = data.getData();
            isImageAdded=true;
            addphotogallery.setVisibility(View.INVISIBLE);
            addphotocamera.setVisibility(View.INVISIBLE);
            showaddphoto.setVisibility(View.VISIBLE);
            showaddphoto.setImageURI(imageUri);
            textnamephoto.setVisibility(View.VISIBLE);
            uploadphotohome.setVisibility(View.VISIBLE);


        }

        if(requestCode == 2 && resultCode != RESULT_CANCELED){


               Bitmap bitmap = (Bitmap) data.getExtras().get("data");
               ByteArrayOutputStream bytes = new ByteArrayOutputStream();
               bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
               String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), bitmap, "val", null);

               imageUri = Uri.parse(path);

               isImageAdded = true;
               addphotogallery.setVisibility(View.INVISIBLE);
               addphotocamera.setVisibility(View.INVISIBLE);
               showaddphoto.setVisibility(View.VISIBLE);
               showaddphoto.setImageURI(imageUri);
               textnamephoto.setVisibility(View.VISIBLE);
               uploadphotohome.setVisibility(View.VISIBLE);


        }

    }

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
}