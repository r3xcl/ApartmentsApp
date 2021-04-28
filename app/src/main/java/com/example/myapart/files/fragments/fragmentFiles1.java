package com.example.myapart.files.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapart.files.FileInfoModel;
import com.example.myapart.files.FilesAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class fragmentFiles1 extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    RecyclerView recview1;
    FilesAdapter filesAdapter;



    public fragmentFiles1() {

    }

    public static fragmentFiles1 newInstance(String param1, String param2) {
        fragmentFiles1 fragment = new fragmentFiles1();
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
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("SHARED_PREF",MODE_PRIVATE);


        String auth = sharedPreferences.getString("auth","").replaceAll("[^A-Za-z0-9]","");

       View view =  inflater.inflate(R.layout.fragment_files1, container, false);

        recview1 = (RecyclerView) view.findViewById(R.id.recview_file1);
        recview1.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<FileInfoModel> options =
                new FirebaseRecyclerOptions.Builder<FileInfoModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference(auth).child("My_Documents").child("apartment1").orderByChild("id").equalTo("apartment1"), FileInfoModel.class).build();

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