package db;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

public class Client_adapter extends FirebaseRecyclerAdapter<ClientClass,Client_adapter.viewHolder> {

   public Client_adapter(@NonNull FirebaseRecyclerOptions<ClientClass>options){super(options);}

    protected void onBindViewHolder(@NonNull final viewHolder holder, final int position, @NonNull final ClientClass clientClass)
    {
     holder.surname_text.setText(clientClass.getSurname());
     holder.name_text.setText(clientClass.getName());
     holder.number_text.setText(clientClass.getNumber());

     holder.edit.setOnClickListener(v -> {

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

           surname_text=(TextView)itemView.findViewById(R.id.surname_text);
           name_text=(TextView)itemView.findViewById(R.id.name_text);
           number_text=(TextView)itemView.findViewById(R.id.number_text);

           edit=(ImageView)itemView.findViewById(R.id.editicon);
           delete=(ImageView)itemView.findViewById(R.id.deleteicon);


       }
   }
}


