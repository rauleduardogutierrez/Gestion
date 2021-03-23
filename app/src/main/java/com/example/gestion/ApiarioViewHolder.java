package com.example.gestion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ApiarioViewHolder extends RecyclerView.ViewHolder{

    private final TextView establecimientoItemView;
    private final TextView direccionItemView;
    private final TextView colmenasItemView;
    private final TextView fechaItemView;
    private final TextView trabajoItemView;
    private final TextView proximaItemView;
    private final TextView gpsItemView;
    private final TextView observacionesItemView;

    private ApiarioViewHolder(View itemView){
        super(itemView);
        establecimientoItemView = itemView.findViewById(R.id.textViewestablecimiento);
        direccionItemView = itemView.findViewById(R.id.textViewdireccion);
        colmenasItemView = itemView.findViewById(R.id.textViewnumero_de_colmenas);
        fechaItemView = itemView.findViewById(R.id.textViewfecha_de_la_visita);
        trabajoItemView = itemView.findViewById(R.id.textViewtrabajo);
        proximaItemView = itemView.findViewById(R.id.textViewproxima);
        gpsItemView = itemView.findViewById(R.id.textViewgps);
        observacionesItemView = itemView.findViewById(R.id.textViewobservaciones);

    }
    public void bind(String establecimiento,
                     String direccion,
                     String numero_de_colmenas,
                     String fecha_de_la_visita,
                     String trabajo,
                     String proxima_visita,
                     String gps,
                     String observaciones){
        establecimientoItemView.setText(establecimiento);
        direccionItemView.setText(direccion);
        colmenasItemView.setText(numero_de_colmenas);
        fechaItemView.setText(fecha_de_la_visita);
        trabajoItemView.setText(trabajo);
        proximaItemView.setText(proxima_visita);
        gpsItemView.setText(gps);
        observacionesItemView.setText(observaciones);
    }

    static ApiarioViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apiario_item, parent, false);
        return new ApiarioViewHolder(view);
    }


}
