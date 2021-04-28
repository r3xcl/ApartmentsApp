package com.example.myapart.fragments.fragments2;

import android.content.SharedPreferences;
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

import db.Pay.PayAdapter;
import db.Pay.PayClass;

import static android.content.Context.MODE_PRIVATE;


public class Fragment_Pay2 extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    PayAdapter payAdapter;



    public Fragment_Pay2() {

    }

    public static Fragment_Pay2 newInstance(String param1, String param2) {
        Fragment_Pay2 fragment = new Fragment_Pay2();
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
        View view = inflater.inflate(R.layout.fragment_pay2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recview_pay2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<PayClass> options =
                new FirebaseRecyclerOptions.Builder<PayClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference(auth).child("Pay_History").child("apartment2").orderByChild("id").equalTo("apartment2"),PayClass.class).build();

        payAdapter = new PayAdapter(options);
        recyclerView.setAdapter(payAdapter);




        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        payAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        payAdapter.stopListening();
    }
}