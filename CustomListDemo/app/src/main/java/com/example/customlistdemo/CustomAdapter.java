package com.example.customlistdemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomAdapter extends ArrayAdapter<String> {
    Context context;
    String[] names, ids;
    Integer[] avatars;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull String[] names, String[] ids, Integer[] avatars) {
        super(context, R.layout.custom_list_item, names);
        this.context = context;
        this.names = names;
        this.ids = ids;
        this.avatars = avatars;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_list_item, null);
        ImageView imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        TextView txtName = (TextView) view.findViewById(R.id.txtName);
        TextView txtId = (TextView) view.findViewById(R.id.txtId);
        txtName.setText(names[position]);
        txtId.setText(ids[position]);
        imgAvatar.setImageResource(avatars[position]);
        return view;
    }
}
