package com.example.incidenciesfragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
    private Context context;
    int tamaño = incidencias.size();

    public RecyclerViewAdapter(Context con){
        context = con;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int tamaño) {
        holder.etiquetaIncidencia.setText(incidencias.get(tamaño).getIncidencia());
        holder.etiquetaPrioritat.setText(incidencias.get(tamaño).getPrioritat());
    }

    @Override
    public int getItemCount() {
        return incidencias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView etiquetaIncidencia, etiquetaPrioritat;
        ConstraintLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            etiquetaIncidencia = itemView.findViewById(R.id.incidencia);
            etiquetaPrioritat = itemView.findViewById(R.id.Nivell);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}

