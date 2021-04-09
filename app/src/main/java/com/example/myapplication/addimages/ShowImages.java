package com.example.myapplication.addimages;

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

        recyclerViewAllPhoto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewAllPhoto.setHasFixedSize(true);

        Intent intent = getIntent();
        String action = intent.getAction();

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
                .child("Image").orderByChild("ImageID").equalTo("id1"),Image.class).build();

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
                .child("Image").orderByChild("ImageID").equalTo("id2"),Image.class).build();

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
                .child("Image").orderByChild("ImageID").equalTo("id3"),Image.class).build();

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
                .child("Image").orderByChild("ImageID").equalTo("id4"),Image.class).build();

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