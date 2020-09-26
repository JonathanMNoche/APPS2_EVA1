package com.example.eva1_2_comunicacion_frag;

import android.os.Bundle;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.zip.Inflater;


public class ListFragment extends Fragment {
    //Datos de la lista

    String[] datos = {
            "Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto",
            "Septiembre",
            "Octubre",
            "Noviembre",
            "Diciembre",
            "Enero1",
            "Febrero1",
            "Marzo1",
            "Abril1",
            "Mayo1"
    };
    MainActivity main;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (MainActivity)getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout frameLayout = (FrameLayout)inflater.inflate(R.layout.fragment_list, container, false);
        ListView lstVwDatos;
        lstVwDatos = frameLayout.findViewById(R.id.lstVwDatos);
        lstVwDatos.setAdapter(new ArrayAdapter<>(
                main,
                android.R.layout.simple_list_item_1,
                datos
        ));
        //Eventp
        lstVwDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                main.onMessageFromFragmentToMain("LISTA", datos[i]);
            }
        });
        return frameLayout;
    }
}
