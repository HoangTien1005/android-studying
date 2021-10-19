package com.example.fragmenthomework;

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

public class MemberAdapter extends ArrayAdapter<String> {
    Context context;
    String[] ids;
    Integer[] avatars;
    int resource;

    public MemberAdapter(@NonNull Context context, int resource, @NonNull String[] ids, Integer[] avatars) {
        super(context, resource,ids);
        this.context = context;
        this.ids = ids;
        this.avatars = avatars;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(resource, null);
        ImageView imgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);
        TextView txtId = (TextView) view.findViewById(R.id.txtId);
        txtId.setText(ids[position]);
        imgAvatar.setImageResource(avatars[position]);
        return view;
    }
}
