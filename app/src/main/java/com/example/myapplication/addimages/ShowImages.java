package com.example.myapplication.addimages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_images);
        getSupportActionBar().hide();

        DataRef = FirebaseDatabase.getInstance().getReference().child("Image");

        recyclerViewAllPhoto = findViewById(R.id.recyclerViewAllPhoto);

        faddphotohome = findViewById(R.id.faddphotohome);

        recyclerViewAllPhoto.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerViewAllPhoto.setHasFixedSize(true);

        faddphotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AddImage.class));

            }
        });

        LoadData();
    }

    private void LoadData() {

        options = new FirebaseRecyclerOptions.Builder<Image>().setQuery(DataRef,Image.class).build();

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