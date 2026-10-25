package com.example.lab2_6;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ThumbnailAdapter extends ArrayAdapter<Thumbnail> {
    private Activity context;

    public ThumbnailAdapter(Activity context, Thumbnail[] objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_selected_thumbnail, parent, false);
        TextView tvName = (TextView) view.findViewById(R.id.item_selected_thumbnail_tv_name);
        tvName.setText(getItem(position).getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_thumbnail, parent, false);
        TextView tvName = (TextView) view.findViewById(R.id.item_thumbnail_tv_name);
        ImageView ivThumbnail = (ImageView) view.findViewById(R.id.item_thumbnail_iv_thumbnail);
        tvName.setText(getItem(position).getName());
        ivThumbnail.setImageResource(getItem(position).getImg());
        return view;
    }
}
