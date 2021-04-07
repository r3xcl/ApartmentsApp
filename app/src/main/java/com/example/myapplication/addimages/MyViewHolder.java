package com.example.myapplication.addimages;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView image_single_view;
    TextView textphoto;

    View v;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        image_single_view = itemView.findViewById(R.id.image_single_view);

        textphoto = itemView.findViewById(R.id.textphoto);

        v = itemView;


    }
}
