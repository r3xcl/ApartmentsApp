package com.example.myapplication.files.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.files.FileInfoModel;
import com.example.myapplication.files.Files;
import com.example.myapplication.files.FilesAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import db.Pay.PayAdapter;
import db.Pay.PayClass;

public class fragmentFiles2 extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recview1;
    FilesAdapter filesAdapter;

    public fragmentFiles2() {

    }

    public static fragmentFiles2 newInstance(String param1, String param2) {
        fragmentFiles2 fragment = new fragmentFiles2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_files2, container, false);

        recview1 = (RecyclerView) view.findViewById(R.id.recview_file2);
        recview1.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<FileInfoModel> options =
                new FirebaseRecyclerOptions.Builder<FileInfoModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("My_Documents").orderByChild("id").equalTo("apartment2"), FileInfoModel.class).build();

        filesAdapter = new FilesAdapter(options);
        recview1.setAdapter(filesAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        filesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        filesAdapter.stopListening();
    }
}