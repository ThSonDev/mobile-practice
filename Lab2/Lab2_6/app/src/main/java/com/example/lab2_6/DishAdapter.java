package com.example.lab2_6;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DishAdapter extends ArrayAdapter<Dish> {
    private Activity context;

    public DishAdapter(Activity context, List<Dish> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_dish, parent, false);
        }

        Dish dish = getItem(position);

        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.item_dish_iv_thumbnail);
        TextView tvName = (TextView) convertView.findViewById(R.id.item_dish_tv_name);
        ImageView ivStar = (ImageView) convertView.findViewById(R.id.item_dish_iv_star);

        ivThumbnail.setImageResource(dish.getThumbnail().getImg());
        tvName.setText(dish.getName());
        tvName.setSelected(true);

        if (dish.isPromotion()) {
            ivStar.setVisibility(View.VISIBLE);
        } else {
            ivStar.setVisibility(View.GONE);
        }

        return convertView;
    }
}
