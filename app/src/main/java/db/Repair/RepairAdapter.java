package db.Repair;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class RepairAdapter extends FirebaseRecyclerAdapter<RepairClass, RepairAdapter.viewHolder> {


    public RepairAdapter(@NonNull FirebaseRecyclerOptions<RepairClass>options){super(options);
    }


    protected void onBindViewHolder(@NonNull final viewHolder holder, final int position, @NonNull final RepairClass repairClass)
    {
        holder.repair_date.setText(repairClass.getDate());
        holder.repair_name.setText(repairClass.getName());
        holder.repair_money.setText(repairClass.getSum()+" Грн.");


        holder.delete_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Оберіть дію");



                builder.setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Repairs_History")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

        holder.edit_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus= DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit_repair))
                        .create();



                View myview=dialogPlus.getHolderView();





                final EditText name=myview.findViewById(R.id.name_edit_repair);
                final EditText date=myview.findViewById(R.id.date_edit_repair);
                final EditText pay=myview.findViewById(R.id.sum_edit_repair);


                Button update_repair=myview.findViewById(R.id.update_repair);


                name.setText(repairClass.getName());
                pay.setText(repairClass.getSum());
                date.setText(repairClass.getDate());



                dialogPlus.show();

                update_repair.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();

                        map.put("name",name.getText().toString());
                        map.put("sum",pay.getText().toString());
                        map.put("date",date.getText().toString());



                        FirebaseDatabase.getInstance().getReference().child("Repairs_History")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });


                    }


                });




            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_archive_repair,parent,false);
        return new viewHolder(view);


    }


    class viewHolder extends RecyclerView.ViewHolder{

        TextView repair_date,repair_name,repair_money;
        ImageView edit_repair,delete_repair;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            repair_date=(TextView)itemView.findViewById(R.id.repair_date);
            repair_name=(TextView)itemView.findViewById(R.id.repair_name);
            repair_money=(TextView)itemView.findViewById(R.id.repair_money);

            edit_repair=(ImageButton)itemView.findViewById(R.id.edit_repair);
            delete_repair=(ImageView) itemView.findViewById(R.id.delete_repair);


        }
    }


}
