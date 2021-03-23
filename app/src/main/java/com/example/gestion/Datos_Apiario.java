package com.example.gestion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.gestion.entities.Apiario;
import com.example.gestion.models.ApiarioViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Datos_Apiario extends AppCompatActivity {

    private ApiarioViewModel apiarioViewModel;

    public static final int NEW_APIARIO_REQ_CODE = 1;
    public static final int UPDATE_APIARIO_REQ_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos__apiario);

        RecyclerView recyclerViewApi = findViewById(R.id.recyclerViewApiarios);
        final ApiarioListAdapter adapter = new ApiarioListAdapter(new ApiarioListAdapter.ApiarioDiff());
        recyclerViewApi.setAdapter(adapter);
        recyclerViewApi.setLayoutManager(new LinearLayoutManager(this));
        
        apiarioViewModel = new ViewModelProvider(this,new ApiarioFactory(getApplication())).get(ApiarioViewModel.class);

        apiarioViewModel.getApiarios().observe(this, apiarios -> {
            adapter.submitList(apiarios);
        });

        FloatingActionButton fab = findViewById(R.id.btnAgregar);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(Datos_Apiario.this, Ingreso_Datos_apiarios.class);

            startActivityForResult(intent, NEW_APIARIO_REQ_CODE);
        });
        adapter.setOnItemSetListener(new ApiarioListAdapter.OnItemClickListener() {

            @Override
            public void onItemDelete(Apiario apiario) {
                apiarioViewModel.delete(apiario);
                Toast.makeText(getApplicationContext(), R.string.borrado, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemClick(Apiario apiario) {
                Intent intent = new Intent(Datos_Apiario.this,Ingreso_Datos_apiarios.class);
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_ESTABLECIMIENTO,apiario.getEstablecimiento());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_DIRECCION,apiario.getDireccion());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_COLMENAS,apiario.getNumero_de_colmenas());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_FECHA,apiario.getFecha_de_la_visita());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_TRABAJO,apiario.getTrabajo());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_PROXIMA,apiario.getProxima_visita());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_GPS,apiario.getGps());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_OBSERVACIONES,apiario.getObservaciones());
                intent.putExtra(Ingreso_Datos_apiarios.EXTRA_MSG_ID,apiario.getId());
                startActivityForResult(intent, UPDATE_APIARIO_REQ_CODE);
            }
        });


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_APIARIO_REQ_CODE && resultCode == RESULT_OK) {
            Apiario apiario = new Apiario();
            apiario.setEstablecimiento(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_ESTABLECIMIENTO));
            apiario.setDireccion(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_DIRECCION));
            apiario.setNumero_de_colmenas(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_COLMENAS));
            apiario.setFecha_de_la_visita(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_FECHA));
            apiario.setTrabajo(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_TRABAJO));
            apiario.setProxima_visita(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_PROXIMA));
            apiario.setGps(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_GPS));
            apiario.setObservaciones(data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_OBSERVACIONES));

            apiarioViewModel.insert(apiario);
        }else if(requestCode == UPDATE_APIARIO_REQ_CODE && resultCode == RESULT_OK){
            int id = data.getIntExtra(Ingreso_Datos_apiarios.EXTRA_MSG_ID,-1);
            if (id == -1){
                Toast.makeText(getApplicationContext(), R.string.no_actulisado, Toast.LENGTH_LONG).show();
            }
            String establecimiento = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_ESTABLECIMIENTO);
            String direccion = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_DIRECCION);
            String numero_de_colmenas = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_COLMENAS);
            String fecha_de_la_visita = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_FECHA);
            String trabajo = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_TRABAJO);
            String proxima_visita = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_PROXIMA);
            String gps = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_GPS);
            String observaciones = data.getStringExtra(Ingreso_Datos_apiarios.EXTRA_MSG_OBSERVACIONES);

            Apiario apiario = new Apiario(id,
                    establecimiento,
                    direccion,
                    numero_de_colmenas,
                    fecha_de_la_visita,
                    trabajo,
                    proxima_visita,
                    gps,
                    observaciones);

            apiarioViewModel.update(apiario);

        }

        else {
            Toast.makeText(getApplicationContext(), R.string.no_guardado, Toast.LENGTH_LONG).show();
        }
    }
}