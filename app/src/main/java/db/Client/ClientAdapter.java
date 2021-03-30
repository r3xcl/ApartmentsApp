package db.Client;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

public class ClientAdapter extends FirebaseRecyclerAdapter<ClientClass, ClientAdapter.viewHolder> {


   public ClientAdapter(@NonNull FirebaseRecyclerOptions<ClientClass>options){super(options);
   }


    protected void onBindViewHolder(@NonNull final viewHolder holder, final int position, @NonNull final ClientClass clientClass)
    {
     holder.surname_text.setText(clientClass.getSurname());
     holder.name_text.setText(clientClass.getName());
     holder.number_text.setText(clientClass.getNumber());




        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.itemView.getContext());
                builder.setTitle("Оберіть дію");



                builder.setPositiveButton("Видалити", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("New_Client")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button color = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
                color.setTextColor(Color.RED);

                Button color1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                color1.setTextColor(Color.RED);

                alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.itemView.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialog_edit))
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
                final EditText email=myview.findViewById(R.id.email_edit);

                Button update_edit=myview.findViewById(R.id.update_edit);


                name.setText(clientClass.getName());
                surname.setText(clientClass.getSurname());
                patronymic.setText(clientClass.getPatronymic());
                number.setText(clientClass.getNumber());
                datestart.setText(clientClass.getDatestart());
                dateend.setText(clientClass.getDateend());
                pay.setText(clientClass.getPay());
                zastava.setText(clientClass.getZastava());
                email.setText(clientClass.getEmail());





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
                        map.put("email",email.getText().toString());


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
        });
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row,parent,false);
        return new viewHolder(view);


    }


    class viewHolder extends RecyclerView.ViewHolder{

       TextView surname_text,name_text,number_text;
       ImageView edit,delete;

       public viewHolder(@NonNull View itemView) {
           super(itemView);

           surname_text=(TextView)itemView.findViewById(R.id.repair_name);
           name_text=(TextView)itemView.findViewById(R.id.money_money);
           number_text=(TextView)itemView.findViewById(R.id.money_date);

           edit=(ImageButton)itemView.findViewById(R.id.edit_clients);
           delete=(ImageView) itemView.findViewById(R.id.delete_clients);




       }
   }
}


