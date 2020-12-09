package com.example.incidenciesfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    ArrayList<Incidencia> incidencies = new ArrayList<Incidencia>();
    private Context context;
    int tamaño = incidencies.size();


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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                ExtraInfo info = new ExtraInfo();
                info.contador = position;
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.extraInfo, info).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return incidencies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaIncidencia, etiquetaPrioritat, etiquetaEstat;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaIncidencia = itemView.findViewById(R.id.incidencia);
            etiquetaPrioritat = itemView.findViewById(R.id.Nivell);
            etiquetaEstat = itemView.findViewById(R.id.EstatType);
            layout = itemView.findViewById(R.id.layout);
        }
        public TextView txt(String estat) {
            etiquetaEstat.setText(estat);
            return etiquetaEstat;
        }
    }

}

