package com.example.studentmanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private EditText edtName, edtCode, edtPhone;
    private Button btnUpdate, btnDelete;
    private Student currentStudent;
    private StudentDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dbHelper = new StudentDbHelper(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Chi tiết sinh viên");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        edtName = findViewById(R.id.edt_detail_name);
        edtCode = findViewById(R.id.edt_detail_code);
        edtPhone = findViewById(R.id.edt_detail_phone);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        currentStudent = (Student) getIntent().getSerializableExtra("STUDENT_DATA");

        if (currentStudent != null) {
            edtName.setText(currentStudent.getName());
            edtCode.setText(currentStudent.getStudentCode());
            edtPhone.setText(currentStudent.getPhone());
        }

        btnUpdate.setOnClickListener(v -> handleUpdate());
        btnDelete.setOnClickListener(v -> confirmDelete());
    }

    private void handleUpdate() {
        String name = edtName.getText().toString().trim();
        String code = edtCode.getText().toString().trim();
        String phone = edtPhone.getText().toString().trim();

        if (name.isEmpty() || code.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên và MSSV", Toast.LENGTH_SHORT).show();
            return;
        }

        currentStudent.setName(name);
        currentStudent.setStudentCode(code);
        currentStudent.setPhone(phone);

        dbHelper.updateStudent(currentStudent);
        Toast.makeText(this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận")
                .setMessage("Bạn có chắc chắn muốn xóa sinh viên này?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    dbHelper.deleteStudent(currentStudent.getId());
                    Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}