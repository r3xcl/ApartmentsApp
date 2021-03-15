package com.example.myapplication.fragments.fragments1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import db.Repair.RepairAdapter;
import db.Repair.RepairClass;

public class Fragment_Repair1 extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    RepairAdapter repairAdapter;

    public Fragment_Repair1() {

    }



    public static Fragment_Repair1 newInstance(String param1, String param2) {
        Fragment_Repair1 fragment = new Fragment_Repair1();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__repair1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recview_remont);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<RepairClass> options1 =
                new FirebaseRecyclerOptions.Builder<RepairClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Repairs_History").orderByChild("id").equalTo("apartment1"), RepairClass.class).build();

        repairAdapter = new RepairAdapter(options1);
        recyclerView.setAdapter(repairAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        repairAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        repairAdapter.stopListening();
    }
}