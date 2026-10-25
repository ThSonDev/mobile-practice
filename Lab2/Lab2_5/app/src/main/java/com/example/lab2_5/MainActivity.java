package com.example.lab2_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etId = (EditText) findViewById(R.id.et_id);
        final EditText etFullName = (EditText) findViewById(R.id.et_full_name);
        final CheckBox cbManager = (CheckBox) findViewById(R.id.cb_manager);
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        ListView lvEmployee = (ListView) findViewById(R.id.lv_employee);

        final ArrayList<Employee> employees = new ArrayList<Employee>();
        final EmployeeAdapter adapter = new EmployeeAdapter(this, R.layout.item_employee, employees);
        lvEmployee.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                employees.add(new Employee(etId.getText().toString(),
                        etFullName.getText().toString(), cbManager.isChecked()));
                adapter.notifyDataSetChanged();
            }
        });
    }
}
