package com.example.myapart.addimages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ShowImages extends AppCompatActivity {

    RecyclerView recyclerViewAllPhoto;
    FloatingActionButton faddphotohome;

    ImageButton back;

    FirebaseRecyclerOptions<Image> options;
    FirebaseRecyclerAdapter<Image,MyViewHolder> adapter;

    DatabaseReference DataRef;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        getSupportActionBar().hide();

        sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        DataRef = FirebaseDatabase.getInstance().getReference(auth).child("Image");

        recyclerViewAllPhoto = findViewById(R.id.recyclerViewAllPhoto);

        faddphotohome = findViewById(R.id.faddphotohome);
        back = findViewById(R.id.back);

        recyclerViewAllPhoto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewAllPhoto.setHasFixedSize(true);

        Intent intent = getIntent();
        String action = intent.getAction();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(action.equals("showimage1")) {

            faddphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("addimage1");
                    startActivityForResult(intent1, 1);

                }
            });

            LoadData1();
        }

        if(action.equals("showimage2")) {

            faddphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("addimage2");
                    startActivityForResult(intent1, 2);

                }
            });

            LoadData2();
        }

        if(action.equals("showimage3")) {

            faddphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("addimage3");
                    startActivityForResult(intent1, 1);

                }
            });

            LoadData3();
        }

        if(action.equals("showimage4")) {

            faddphotohome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent1 = new Intent("addimage4");
                    startActivityForResult(intent1, 4);

                }
            });

            LoadData4();
        }
    }

    private void LoadData1() {


        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        options = new FirebaseRecyclerOptions.Builder<Image>().setQuery(FirebaseDatabase.getInstance().getReference(auth)
                .child("Image").child("apartment1").orderByChild("ImageID").equalTo("apartment1"),Image.class).build();

        adapter = new FirebaseRecyclerAdapter<Image, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Image model) {

                holder.textphoto.setText(model.getImageName());

                Picasso.get().load(model.getImageUrl()).into(holder.image_single_view);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowImages.this,ViewActivity.class);

                        intent.putExtra("ImageKey",getRef(position).getKey());
                        intent.putExtra("id","apartment1");

                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_home,parent,false);

                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerViewAllPhoto.setAdapter(adapter);


    }

    private void LoadData2() {


        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        options = new FirebaseRecyclerOptions.Builder<Image>().setQuery(FirebaseDatabase.getInstance().getReference(auth)
                .child("Image").child("apartment2").orderByChild("ImageID").equalTo("apartment2"),Image.class).build();

        adapter = new FirebaseRecyclerAdapter<Image, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Image model) {

                holder.textphoto.setText(model.getImageName());

                Picasso.get().load(model.getImageUrl()).into(holder.image_single_view);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowImages.this,ViewActivity.class);

                        intent.putExtra("ImageKey",getRef(position).getKey());
                        intent.putExtra("id","apartment2");

                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_home,parent,false);

                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerViewAllPhoto.setAdapter(adapter);


    }

    private void LoadData3() {


        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        options = new FirebaseRecyclerOptions.Builder<Image>().setQuery(FirebaseDatabase.getInstance().getReference(auth)
                .child("Image").child("apartment3").orderByChild("ImageID").equalTo("apartment3"),Image.class).build();

        adapter = new FirebaseRecyclerAdapter<Image, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Image model) {

                holder.textphoto.setText(model.getImageName());

                Picasso.get().load(model.getImageUrl()).into(holder.image_single_view);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowImages.this,ViewActivity.class);

                        intent.putExtra("ImageKey",getRef(position).getKey());
                        intent.putExtra("id","apartment3");

                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_home,parent,false);

                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerViewAllPhoto.setAdapter(adapter);


    }

    private void LoadData4() {


        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF",MODE_PRIVATE);
        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

        options = new FirebaseRecyclerOptions.Builder<Image>().setQuery(FirebaseDatabase.getInstance().getReference(auth)
                .child("Image").child("apartment4").orderByChild("ImageID").equalTo("apartment4"),Image.class).build();

        adapter = new FirebaseRecyclerAdapter<Image, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Image model) {

                holder.textphoto.setText(model.getImageName());

                Picasso.get().load(model.getImageUrl()).into(holder.image_single_view);

                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShowImages.this,ViewActivity.class);

                        intent.putExtra("ImageKey",getRef(position).getKey());
                        intent.putExtra("id","apartment4");

                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_home,parent,false);

                return new MyViewHolder(v);
            }
        };

        adapter.startListening();
        recyclerViewAllPhoto.setAdapter(adapter);


    }


}