package com.example.myapplication.fragments.fragments1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import db.Pay.PayAdapter;
import db.Pay.PayClass;
import db.Repair.RepairClass;


public class Fragment_Pay1 extends Fragment implements View.OnClickListener{

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView amount_pay,amount_repair,amountall;



    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    PayAdapter payAdapter;



    public Fragment_Pay1() {



    }

    public static Fragment_Pay1 newInstance(String param1, String param2) {
        Fragment_Pay1 fragment = new Fragment_Pay1();
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

        View view = inflater.inflate(R.layout.fragment__pay1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recview_pay);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<PayClass> options =
                new FirebaseRecyclerOptions.Builder<PayClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Pay_History").orderByChild("id").equalTo("apartment1"),PayClass.class).build();

        payAdapter = new PayAdapter(options);
        recyclerView.setAdapter(payAdapter);

        amount_pay = (TextView) view.findViewById(R.id.amount_pay);
        amount_repair = (TextView) view.findViewById(R.id.amount_repair);
        amountall = (TextView) view.findViewById(R.id.amount);

        amount_repair.setText(""+0);
        amount_pay.setText(""+0);
        amountall.setText(""+0+" Грн.");



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        Query query = reference.child("Pay_History").orderByChild("id").equalTo("apartment1");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int sum = 0;
                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                        PayClass payClass = snapShot.getValue(PayClass.class);
                        int amount = Integer.parseInt(payClass.getPay());
                        sum = sum + amount;

                        amount_pay.setText(""+sum);

                        if(amount_pay.getText().toString()==""){

                            amount_pay.setText(""+0+" Грн.");

                        }


                        int p = Integer.parseInt(amount_pay.getText().toString());
                        amountall.setText(""+(p) + " Грн.");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query2 = reference.child("Repairs_History").orderByChild("id").equalTo("apartment1");
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    int sum = 0;
                    for (DataSnapshot snapShot : dataSnapshot.getChildren()) {
                        RepairClass repairClass = snapShot.getValue(RepairClass.class);
                        int amount = Integer.parseInt(repairClass.getSum());
                        sum = sum + amount;

                        amount_repair.setText(""+sum);

                        if(amount_pay.getText().toString()==""){

                            amount_pay.setText(""+0);

                        }

                        if (amount_repair.getText().toString()==""){

                            amount_repair.setText(""+0);

                        }

                        int p = Integer.parseInt(amount_pay.getText().toString());
                        int r = Integer.parseInt(amount_repair.getText().toString());

                        amountall.setText(""+(p-r) + " Грн.");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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

    @Override
    public void onClick(View v) {

    }


}