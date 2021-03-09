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
        holder.money_money.setText(payClass.getPay());
        holder.money_name.setText(payClass.getName());


    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_archive_money,parent,false);
        return new viewHolder(view);


    }


    class viewHolder extends RecyclerView.ViewHolder{

        TextView money_money,money_date,money_name;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            money_money=(TextView)itemView.findViewById(R.id.money_money);
            money_date=(TextView)itemView.findViewById(R.id.money_date);
            money_name=(TextView)itemView.findViewById(R.id.money_name);


        }
    }
}
