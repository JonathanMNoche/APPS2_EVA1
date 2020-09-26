package com.example.eva1_11_clima;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class WeatherAdapter extends ArrayAdapter<Weather> {
    //private Weather[] objects;
    private List<Weather> objects;
    private Context context;
    private int resource;
    public WeatherAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){//Primera Vez, hay que crear el layout
            convertView = ((Activity)context).getLayoutInflater().inflate(resource, parent, false);
        }
        //HAY QUE RECUPERAR LOS COMPONENTES
        ImageView imgVwWeather;
        TextView txtVwCity, txtVwTemp, txtVwDesc;
        imgVwWeather = convertView.findViewById(R.id.imgVwWeather);
        txtVwCity = convertView.findViewById(R.id.txtVwCity);
        txtVwTemp = convertView.findViewById(R.id.txtVwTemp);
        txtVwDesc = convertView.findViewById(R.id.txtVwDesc);
        //Conexión
        Weather weather = objects.get(position);
        imgVwWeather.setImageResource(weather.getImage());
        txtVwCity.setText(weather.getCity());
        txtVwTemp.setText("" + weather.getTemp());
        txtVwDesc.setText(weather.getDesc());
        return convertView;
    }
}
