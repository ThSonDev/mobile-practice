package com.example.lab2_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etName = (EditText) findViewById(R.id.et_name);
        final Spinner spThumbnail = (Spinner) findViewById(R.id.sp_thumbnail);
        final CheckBox cbPromotion = (CheckBox) findViewById(R.id.cb_promotion);
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        GridView gvDish = (GridView) findViewById(R.id.gv_dish);

        spThumbnail.setAdapter(new ThumbnailAdapter(this, Thumbnail.values()));

        final ArrayList<Dish> dishes = new ArrayList<Dish>();
        final DishAdapter adapter = new DishAdapter(this, dishes);
        gvDish.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                dishes.add(new Dish(etName.getText().toString(),
                        (Thumbnail) spThumbnail.getSelectedItem(), cbPromotion.isChecked()));
                adapter.notifyDataSetChanged();

                etName.setText("");
                spThumbnail.setSelection(0);
                cbPromotion.setChecked(false);
                Toast.makeText(MainActivity.this, R.string.added, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
