package db;

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

public class PayAdapter extends FirebaseRecyclerAdapter<PayClass,PayAdapter.viewHolder> {


    public PayAdapter(@NonNull FirebaseRecyclerOptions<PayClass>options){super(options);
    }


    protected void onBindViewHolder(@NonNull final viewHolder holder, final int position, @NonNull final PayClass payClass)
    {
        holder.money_date.setText(payClass.getDate());
        holder.money_money.setText(payClass.getPay()+" Грн.");
        holder.money_name.setText(payClass.getName());



        holder.delete_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Оберіть дію");



                builder.setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Pay_History")
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

        /*holder.edit_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
                        .setExpanded(true,2050)
                        .create();



                View myview=dialogPlus.getHolderView();





                final EditText surname=myview.findViewById(R.id.surname_edit);
                final EditText name=myview.findViewById(R.id.name_edit);
                final EditText number=myview.findViewById(R.id.number_edit);
                final EditText patronymic=myview.findViewById(R.id.patronymic_edit);
                final EditText datestart=myview.findViewById(R.id.datestart_edit);
                final EditText dateend=myview.findViewById(R.id.dateend_edit);
                final EditText pay=myview.findViewById(R.id.pay_edit);
                final EditText zastava=myview.findViewById(R.id.zastava_client);

                Button update_edit=myview.findViewById(R.id.update_edit);


                name.setText(clientClass.getName());
                surname.setText(clientClass.getSurname());
                patronymic.setText(clientClass.getPatronymic());
                number.setText(clientClass.getNumber());
                datestart.setText(clientClass.getDatestart());
                dateend.setText(clientClass.getDateend());
                pay.setText(clientClass.getPay());
                zastava.setText(clientClass.getZastava());





                dialogPlus.show();

                update_edit.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();

                        map.put("surname",surname.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("patronymic",patronymic.getText().toString());
                        map.put("number",number.getText().toString());
                        map.put("datestart",datestart.getText().toString());
                        map.put("dateend",dateend.getText().toString());
                        map.put("pay",pay.getText().toString());
                        map.put("zastava",zastava.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("New_Client")
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
        });*/




    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_archive_money,parent,false);
        return new viewHolder(view);


    }


    class viewHolder extends RecyclerView.ViewHolder{

        TextView money_money,money_date,money_name;
        ImageView edit_pay,delete_pay;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            money_money=(TextView)itemView.findViewById(R.id.money_money);
            money_date=(TextView)itemView.findViewById(R.id.money_date);
            money_name=(TextView)itemView.findViewById(R.id.money_name);

            edit_pay=(ImageButton)itemView.findViewById(R.id.edit_clients);
            delete_pay=(ImageView) itemView.findViewById(R.id.delete_pay);


        }
    }
}
