package com.example.lab2_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvPerson = (ListView) findViewById(R.id.lv_person);
        final TextView tvSelection = (TextView) findViewById(R.id.tv_selection);

        final String arr[] = {"Tèo", "Tý", "Bin", "Bo"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, arr);

        lvPerson.setAdapter(adapter);

        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                tvSelection.setText("position :" + arg2 + " ; value =" + arr[arg2]);
            }
        });
    }
}
