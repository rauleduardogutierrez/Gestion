package com.example.gestion.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gestion.Datos_Apiario;
import com.example.gestion.Graficas3;
import com.example.gestion.Graficas5;
import com.example.gestion.Ingreso_Datos_apiarios;
import com.example.gestion.Photos;
import com.example.gestion.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Apiarios#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Apiarios extends Fragment {

    View vista;

    Button ver_apiarios;
    Button ingresar_apiarios;
    Button ver_graficas_apiarios;
    Button ver_fotos_apiarios;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Apiarios() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Apiarios.
     */
    // TODO: Rename and change types and number of parameters
    public static Apiarios newInstance(String param1, String param2) {
        Apiarios fragment = new Apiarios();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_apiarios, container, false);

        ver_apiarios = vista.findViewById(R.id.ver_apiarios);

        ver_apiarios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Datos_Apiario.class);
                startActivity(intent);
            }
        });
        ingresar_apiarios = vista.findViewById(R.id.ingresar_apiarios);

        ingresar_apiarios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Ingreso_Datos_apiarios.class);
                startActivity(intent);
            }
        });

        ver_graficas_apiarios = vista.findViewById(R.id.ver_graficas_apiarios);

        ver_graficas_apiarios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Graficas5.class);
                startActivity(intent);
            }
        });
        ver_fotos_apiarios = vista.findViewById(R.id.ver_fotos_apiarios);

        ver_fotos_apiarios.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Photos.class);
                startActivity(intent);
            }
        });

        return vista;
    }

}