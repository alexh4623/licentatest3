package com.example.licentatest3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BusAdapter extends ArrayAdapter<SpinnerItem> {

    public BusAdapter(Context context, ArrayList<SpinnerItem>buslist){
        super(context,0,buslist);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position,View convertView,ViewGroup parent){
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(
                    R.layout.bus_spinner_row,parent,false
            );
        }

        ImageView imageViewBus=convertView.findViewById(R.id.image_view_bus);
        TextView textViewBus=convertView.findViewById(R.id.text_view_bus);

        SpinnerItem currentItem=getItem(position);

        if(currentItem!=null) {
            imageViewBus.setImageResource(currentItem.getBusImage());
            textViewBus.setText(currentItem.getBusName());
        }
        return convertView;
    }
}
