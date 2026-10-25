package com.example.lab2_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etId;
    EditText etName;
    RadioGroup rgType;
    ArrayList<Employee> employees;
    ArrayAdapter<Employee> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etId = (EditText) findViewById(R.id.et_id);
        etName = (EditText) findViewById(R.id.et_name);
        rgType = (RadioGroup) findViewById(R.id.rg_type);
        Button btnNhap = (Button) findViewById(R.id.btn_nhap);
        ListView lvEmployee = (ListView) findViewById(R.id.lv_employee);

        employees = new ArrayList<Employee>();
        adapter = new ArrayAdapter<Employee>(this,
                android.R.layout.simple_list_item_1, employees);
        lvEmployee.setAdapter(adapter);

        btnNhap.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                addNewEmployee();
            }
        });
    }

    public void addNewEmployee() {
        int radId = rgType.getCheckedRadioButtonId();
        String id = etId.getText().toString();
        String name = etName.getText().toString();
        Employee employee;
        if (radId == R.id.rd_chinhthuc) {
            employee = new EmployeeFulltime();
        } else {
            employee = new EmployeeParttime();
        }
        employee.setId(id);
        employee.setName(name);
        employees.add(employee);
        adapter.notifyDataSetChanged();
    }
}
