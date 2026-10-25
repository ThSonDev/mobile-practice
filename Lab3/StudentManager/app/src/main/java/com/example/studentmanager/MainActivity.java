package com.example.studentmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvStudents;
    private FloatingActionButton fabAdd;
    private StudentAdapter adapter;
    private StudentDbHelper dbHelper;
    private List<Student> studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new StudentDbHelper(this);
        rvStudents = findViewById(R.id.rv_students);
        fabAdd = findViewById(R.id.fab_add);

        studentList = new ArrayList<>();
        adapter = new StudentAdapter(studentList, student -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("STUDENT_DATA", student);
            startActivity(intent);
        });

        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        rvStudents.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvStudents.setAdapter(adapter);

        fabAdd.setOnClickListener(v -> showAddStudentDialog());
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStudentData();
    }

    private void loadStudentData() {
        studentList = dbHelper.getAllStudents();
        if (studentList.isEmpty()) {
            dbHelper.insertStudent(new Student("Phạm Nguyễn Thanh Sơn", "23521360", "0912345678"));
            studentList = dbHelper.getAllStudents();
        }
        adapter.updateData(studentList);
    }

    private void showAddStudentDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null);
        EditText edtName = dialogView.findViewById(R.id.edt_add_name);
        EditText edtCode = dialogView.findViewById(R.id.edt_add_code);
        EditText edtPhone = dialogView.findViewById(R.id.edt_add_phone);

        new AlertDialog.Builder(this)
                .setTitle("Thêm sinh viên mới")
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String name = edtName.getText().toString().trim();
                    String code = edtCode.getText().toString().trim();
                    String phone = edtPhone.getText().toString().trim();

                    if (!name.isEmpty() && !code.isEmpty()) {
                        Student student = new Student(name, code, phone);
                        dbHelper.insertStudent(student);
                        loadStudentData();
                    } else {
                        Toast.makeText(MainActivity.this, "Không được để trống Tên và MSSV", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}