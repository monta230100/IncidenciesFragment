package com.example.incidenciesfragment;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    ArrayList<Incidencia> incidencies = new ArrayList<Incidencia>();
    private Context context;
    int tamaño = incidencies.size();
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    public RecyclerViewAdapter(Context con, ArrayList<Incidencia> incidencias){
        context = con;
        incidencies = incidencias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.etiquetaIncidencia.setText(incidencies.get(position).getIncidencia());
        holder.etiquetaPrioritat.setText(incidencies.get(position).getPrioritat());
        holder.etiquetaEstat.setText(incidencies.get(position).getEstat());
        switch (incidencies.get(position).getEstat()){
            case "ASIGNADO":
                holder.etiquetaColor.setBackgroundColor(ContextCompat.getColor(context, R.color.asignado));
                break;
            case "PENDIENTE":
                holder.etiquetaColor.setBackgroundColor(ContextCompat.getColor(context, R.color.pendiente));
                break;
            case "REALIZADO":
                holder.etiquetaColor.setBackgroundColor(ContextCompat.getColor(context, R.color.resuelto));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                ExtraInfo info = new ExtraInfo();
                info.contador = incidencies.get(position).getId();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, info).addToBackStack(null).commit();
  //              activity.getFragmentManager().beginTransaction().replace(R.id.fragmentID, det).addToBackStack(null).commit();
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String estat = holder.etiquetaEstat.getText().toString();
                Dialogue(position, estat);
            }
        });

    }

    @Override
    public int getItemCount() {
        return incidencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaIncidencia, etiquetaPrioritat, etiquetaEstat;
        ImageView etiquetaColor;
        ConstraintLayout layout;
        Button btnEliminar;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaIncidencia = itemView.findViewById(R.id.incidencia);
            etiquetaPrioritat = itemView.findViewById(R.id.Nivell);
            etiquetaEstat = itemView.findViewById(R.id.EstatType);
            etiquetaColor = itemView.findViewById(R.id.EstatColor);
            layout = itemView.findViewById(R.id.layout);
            btnEliminar = itemView.findViewById(R.id.btnDrop);
            dbHelper = new IncidenciaDBHelper(context);
            db = dbHelper.getWritableDatabase();
        }
    }
    public void Dialogue(final int position, final String nivell){
        new AlertDialog.Builder(context)
                .setMessage("Deseas eliminar la incidencia?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (incidencies.size()>0) {
                            dbHelper.deleteOne(db, incidencies.get(position).getId());
                            incidencies = dbHelper.getAllIncidencies(db);
                            notifyDataSetChanged();
                            Toast.makeText(context, "Has eliminado correctamente la incidencia.", Toast.LENGTH_SHORT).show();
                        }else {
                            if(incidencies.isEmpty()){
                                Toast.makeText(context, "Has eliminado todas las incidencias.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

}

