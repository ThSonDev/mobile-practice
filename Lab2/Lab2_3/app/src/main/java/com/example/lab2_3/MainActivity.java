package com.example.lab2_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Button btnSubmit;
    TextView tvSelection;
    ListView lvPerson;
    ArrayList<String> names;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.et_name);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        tvSelection = (TextView) findViewById(R.id.tv_selection);
        lvPerson = (ListView) findViewById(R.id.lv_person);

        names = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, names);
        lvPerson.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                names.add(etName.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });

        lvPerson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                tvSelection.setText("position : " + arg2 + "; value =" + names.get(arg2));
            }
        });

        lvPerson.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                names.remove(arg2);
                lvPerson.clearChoices();
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
