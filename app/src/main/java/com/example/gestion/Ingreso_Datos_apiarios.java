package com.example.gestion;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Transliterator;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gestion.daos.ApiarioDao;
import com.example.gestion.database.AppDatabaseApi;
import com.example.gestion.entities.Apiario;
import com.example.gestion.models.ApiarioViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class Ingreso_Datos_apiarios extends AppCompatActivity{

    public static final String EXTRA_MSG_ID = "com.example.gestion.MSG_GUARDAR_ID";
    public static final String EXTRA_MSG_ESTABLECIMIENTO = "com.example.gestion.MSG_GUARDAR_ESTABLECIMIENTO";
    public static final String EXTRA_MSG_DIRECCION = "com.example.gestion.MSG_GUARDAR_DIRECCION";
    public static final String EXTRA_MSG_COLMENAS = "com.example.gestion.MSG_GUARDAR_COLMENAS";
    public static final String EXTRA_MSG_FECHA = "com.example.gestion.MSG_GUARDAR_FECHA";
    public static final String EXTRA_MSG_TRABAJO = "com.example.gestion.MSG_GUARDAR_TRABAJO";
    public static final String EXTRA_MSG_PROXIMA = "com.example.gestion.MSG_GUARDAR_PROXIMA";
    public static final String EXTRA_MSG_GPS = "com.example.gestion.MSG_GUARDAR_GPS";
    public static final String EXTRA_MSG_OBSERVACIONES = "com.example.gestion.MSG_GUARDAR_OBSERVACIONES";

    private EditText editTextEstablecimiento;
    private EditText editTextDireccion;
    private EditText editTextColmenas;
    private EditText editTextTrabajo;
    private EditText editTextFechavisita;
    private EditText editTextProximavisita;
    private EditText editTextGps;
    private EditText editTextObservaciones;

    private TextView datepicker1;
    private DatePickerDialog.OnDateSetListener midatesetlistener1;
    private TextView datepicker2;
    private DatePickerDialog.OnDateSetListener midatesetlistener2;
    private static final String TAG = "Ingreso_Datos_Apiarios";



    String[] courses = { "C", "Data structures", "Interview prep", "Algorithms", "DSA with java", "OS" };
    //gps
    private static final int PERMISSIONS_FINE_LOCATION = 99;
    boolean updateOn = false;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    //gps

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso__datos_apiarios);

        editTextEstablecimiento = findViewById(R.id.edittextEstablecimiento);
        editTextDireccion = findViewById(R.id.edittextDireccion);
        editTextColmenas = findViewById(R.id.edittextColmenas);
        editTextFechavisita = findViewById(R.id.edittextFechavisita);
        editTextTrabajo = findViewById(R.id.edittextTrabajo);
        editTextProximavisita = findViewById(R.id.edittextProximavisita);
        editTextGps = findViewById(R.id.edittextGps);
        editTextObservaciones = findViewById(R.id.edittextObservaciones);


        //GPS
        locationRequest = new LocationRequest();
        locationRequest.setInterval(30000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //GPS

        //numberpicker

        NumberPicker numberPicker1 = findViewById(R.id.numberpicker1);
        numberPicker1.setMinValue(0);
        numberPicker1.setMaxValue(50);
        numberPicker1.setValue(25);


        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                editTextColmenas.setText(""+newVal);

            }
        });
        //numberpicker

        //primer datepicker

        datepicker1 = (editTextFechavisita).findViewById(R.id.edittextFechavisita);

        datepicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal1 = Calendar.getInstance();
                int year1 = cal1.get(Calendar.YEAR);
                int mont1 = cal1.get(Calendar.MONTH);
                int day1 = cal1.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog1 = new  DatePickerDialog(Ingreso_Datos_apiarios.this, android.R.style.Theme_Holo_Dialog_MinWidth, midatesetlistener1,
                        year1,mont1,day1);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog1.show();
            }
        });
        midatesetlistener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month1, int day1) {
                month1 = month1+1;
                Log.d(TAG, "OnDateSet: mm/dd/yy: " + month1 + "/"+ day1 + "/"+ year1 );

                String date = month1 + "/"+ day1 + "/"+ year1;
                datepicker1.setText(date);
            }
        };
        //SEGUNDO datepicker

        datepicker2 = (editTextProximavisita).findViewById(R.id.edittextProximavisita);

        datepicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal2 = Calendar.getInstance();
                int year2 = cal2.get(Calendar.YEAR);
                int mont2 = cal2.get(Calendar.MONTH);
                int day2 = cal2.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog2 = new  DatePickerDialog(Ingreso_Datos_apiarios.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth, midatesetlistener2,
                        year2,mont2,day2);
                dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog2.show();
            }
        });
        midatesetlistener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year2, int month2, int day2) {
                month2 = month2+1;
                Log.d(TAG, "OnDateSet: mm/dd/yy: " + month2 + "/"+ day2 + "/"+ year2 );

                String date = month2 + "/"+ day2 + "/"+ year2;
                datepicker2.setText(date);
            }
        };
        //FIN DATEPICKER


        final Button btnAgregar = findViewById(R.id.btnGuardar);

        btnAgregar.setOnClickListener(view -> {
            Intent respuesta = new Intent();
            if(TextUtils.isEmpty(editTextEstablecimiento.getText()) ||
                    TextUtils.isEmpty(editTextDireccion.getText()) ||
                    TextUtils.isEmpty(editTextColmenas.getText()) ||
                    TextUtils.isEmpty(editTextFechavisita.getText()) ||
                    TextUtils.isEmpty(editTextTrabajo.getText()) ||
                    TextUtils.isEmpty(editTextProximavisita.getText()) ||
                    TextUtils.isEmpty(editTextGps.getText()) ||
                    TextUtils.isEmpty(editTextObservaciones.getText()))
            {
                setResult(RESULT_CANCELED, respuesta);
            } else {
                String est_apiario = editTextEstablecimiento.getText().toString();
                String dir_apiario = editTextDireccion.getText().toString();
                String col_apiario = editTextColmenas.getText().toString();
                String fech_apiario = editTextFechavisita.getText().toString();
                String tra_apiario = editTextTrabajo.getText().toString();
                String prox_apiario = editTextProximavisita.getText().toString();
                String gps_apiario = editTextGps.getText().toString();
                String obser_apiario = editTextObservaciones.getText().toString();


                respuesta.putExtra(EXTRA_MSG_ESTABLECIMIENTO, est_apiario);
                respuesta.putExtra(EXTRA_MSG_DIRECCION, dir_apiario);
                respuesta.putExtra(EXTRA_MSG_COLMENAS, col_apiario);
                respuesta.putExtra(EXTRA_MSG_FECHA, fech_apiario);
                respuesta.putExtra(EXTRA_MSG_TRABAJO, tra_apiario);
                respuesta.putExtra(EXTRA_MSG_PROXIMA, prox_apiario);
                respuesta.putExtra(EXTRA_MSG_GPS, gps_apiario);
                respuesta.putExtra(EXTRA_MSG_OBSERVACIONES, obser_apiario);

                int id = getIntent().getIntExtra(EXTRA_MSG_ID,-1);
                if(id != -1){
                    respuesta.putExtra(EXTRA_MSG_ID,id);
                }

                setResult(RESULT_OK, respuesta);
            }
            finish();
        });
        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_MSG_ID)){
            editTextEstablecimiento.setText(intent.getStringExtra(EXTRA_MSG_ESTABLECIMIENTO));
            editTextDireccion.setText(intent.getStringExtra(EXTRA_MSG_DIRECCION));
            editTextColmenas.setText(intent.getStringExtra(EXTRA_MSG_COLMENAS));
            editTextFechavisita.setText(intent.getStringExtra(EXTRA_MSG_FECHA));
            editTextTrabajo.setText(intent.getStringExtra(EXTRA_MSG_TRABAJO));
            editTextProximavisita.setText(intent.getStringExtra(EXTRA_MSG_PROXIMA));
            editTextGps.setText(intent.getStringExtra(EXTRA_MSG_GPS));
            editTextObservaciones.setText(intent.getStringExtra(EXTRA_MSG_OBSERVACIONES));
        }
        updateGPS();
    }
    //GPS
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSIONS_FINE_LOCATION:
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                updateGPS();
            }
            else {
                Toast.makeText(this,"se requieren permisos",Toast.LENGTH_LONG).show();
                finish();
            }
            break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void updateGPS(){
        //permisos de gps
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Ingreso_Datos_apiarios.this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    updateUIValues(location);

                }
            });
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSIONS_FINE_LOCATION);
                }
            }
        }
    }
    private void updateUIValues(Location location){
        //carga edittext
        editTextGps.setText("Lat "+String.valueOf(location.getLatitude())+"  Long "+  String.valueOf(location.getLongitude()));

    }
    //GPS

}