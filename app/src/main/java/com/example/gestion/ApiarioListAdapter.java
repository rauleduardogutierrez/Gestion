package com.example.gestion;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.gestion.entities.Apiario;

public class ApiarioListAdapter extends ListAdapter<Apiario,ApiarioViewHolder> {

    private OnItemClickListener listener;



    public ApiarioListAdapter(@NonNull DiffUtil.ItemCallback<Apiario> diffCallbak){
        super(diffCallbak);
    }

    @NonNull
    @Override
    public ApiarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ApiarioViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiarioViewHolder holder, int position) {
        Apiario apiarioActual = getItem(position);

        holder.bind(apiarioActual.getEstablecimiento(),
                apiarioActual.getDireccion(),
                apiarioActual.getNumero_de_colmenas(),
                apiarioActual.getFecha_de_la_visita(),
                apiarioActual.getTrabajo(),
                apiarioActual.getProxima_visita(),
                apiarioActual.getGps(),
                apiarioActual.getObservaciones()
        );
        ImageButton deletebutton = holder.itemView.findViewById(R.id.imageButtonDelete);

        deletebutton.setOnClickListener(view -> {
            if (listener != null){
                listener.onItemDelete(apiarioActual);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onItemClick(apiarioActual);
                }
            }
        });

    }
    static class ApiarioDiff extends DiffUtil.ItemCallback<Apiario>{
        @Override
        public boolean areItemsTheSame(@NonNull Apiario oldItem, @NonNull Apiario newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Apiario oldItem, @NonNull Apiario newItem) {
            return oldItem.getEstablecimiento().equals(newItem.getEstablecimiento()) &&
                    oldItem.getDireccion().equals(newItem.getDireccion()) &&
                    oldItem.getNumero_de_colmenas().equals(newItem.getNumero_de_colmenas()) &&
                    oldItem.getFecha_de_la_visita().equals(newItem.getFecha_de_la_visita()) &&
                    oldItem.getTrabajo().equals(newItem.getTrabajo()) &&
                    oldItem.getProxima_visita().equals(newItem.getProxima_visita()) &&
                    oldItem.getGps().equals(newItem.getGps()) &&
                    oldItem.getObservaciones().equals(newItem.getObservaciones()) ;
        }
    }
    public interface OnItemClickListener{
        void onItemDelete(Apiario apiario);
        void onItemClick(Apiario apiario);
    }
    public void setOnItemSetListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
