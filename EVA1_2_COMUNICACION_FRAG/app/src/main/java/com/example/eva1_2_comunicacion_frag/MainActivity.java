package com.example.eva1_2_comunicacion_frag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Recuperar Frag
    ListFragment lista;
    DataFragment datos;

    //Acceder

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment.getClass() == ListFragment.class){
            lista = (ListFragment)fragment;
        }else if(fragment.getClass() == DataFragment.class){
            datos = (DataFragment)fragment;
        }
    }

    public void onMessageFromFragmentToMain(String sender, String param){
        if (sender.equals("LISTA")){
            datos.onMessageFromMainToFragment(param);
        }else if(sender.equals("DATA")){
            Toast.makeText(this, param, Toast.LENGTH_SHORT).show();
        }
    }
}
