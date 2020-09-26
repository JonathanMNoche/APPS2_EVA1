package com.example.eva1_11_clima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstVwClima;
    List<Weather> lstCiudades = new ArrayList<>();
    /*Weather[] cities = {
            new Weather("Chihuahua",20, "Nublado", R.drawable.cloudy),
            new Weather("Delicias",-5, "Despejado", R.drawable.sunny),
            new Weather("Ciudad Juárez",-3, "Nevadas Intensas", R.drawable.snow),
            new Weather("Jiménez",23, "Lluvias Intensas", R.drawable.thunderstorm),
            new Weather("Camargo",30, "Vientos Intensos", R.drawable.atmospher),
            new Weather("Parral",8, "Lluvias Fuertes", R.drawable.rainy),
            new Weather("Aldama",-2, "Niebla", R.drawable.cloudy),
            new Weather("Batopilas",10, "Lluvia Ligera", R.drawable.rainy),
            new Weather("Creel",11, "Despejado", R.drawable.sunny),
            new Weather("Casas Grandes",23, "Lluvias Intensas", R.drawable.light_rain),


    };*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstVwClima = findViewById(R.id.lstVwClima);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=439d4b804bc8187953eb36d2a8c26a02",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("list");
                            for (int i=0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Weather wCiudad = new Weather();
                                wCiudad.setCity(jsonObject.getString("name"));
                                JSONObject jsMain = jsonObject.getJSONObject("main");
                                wCiudad.setTemp((int)jsMain.getDouble("temp"));

                                JSONArray jaClima = jsonObject.getJSONArray("weather");
                                JSONObject joClimaCiudad = jaClima.getJSONObject(0);
                                wCiudad.setDesc(joClimaCiudad.getString("description"));
                                int iId = joClimaCiudad.getInt("id");

                                if (iId < 300){ //Tormentas
                                    wCiudad.setImage(R.drawable.thunderstorm);
                                }else if (iId <400){//Lluvia ligera
                                    wCiudad.setImage(R.drawable.light_rain);
                                }else if (iId <600){//Lluvia intensa
                                    wCiudad.setImage(R.drawable.rainy);
                                }else if (iId <700){//Nieve
                                    wCiudad.setImage(R.drawable.snow);
                                }else if (iId <800){//Despejado
                                    wCiudad.setImage(R.drawable.sunny);
                                }else if (iId <900){//Nublado
                                    wCiudad.setImage(R.drawable.cloudy);
                                }else {
                                    wCiudad.setImage(R.drawable.tornado);
                                }
                                lstCiudades.add(wCiudad);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //Aqui conectamos el adaptador a nuestros datos
                        lstVwClima.setAdapter(new WeatherAdapter(MainActivity.this,
                                R.layout.mi_layout,lstCiudades));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonObjectRequest);{

        }
        //lstVwClima.setAdapter(new WeatherAdapter(this, R.layout.mi_layout, cities));
    }
}
