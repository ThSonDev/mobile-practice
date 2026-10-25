package com.example.lab3_1;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.lab3_1.adapter.DbAdapter;

public class MainActivity extends AppCompatActivity {

    private DbAdapter dbAdapter;
    private Cursor cursor;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("23521360_PNThSon_Lab3_1");

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();

        dbAdapter.deleteAllUsers();

        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Nguyễn Văn An " + i);
        }

        users = getData();

        showData();
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        cursor = dbAdapter.getAllUsers();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int nameColumnIndex = cursor.getColumnIndex(DbAdapter.KEY_NAME);
                if (nameColumnIndex != -1) {
                    list.add(cursor.getString(nameColumnIndex));
                }
            }
            cursor.close();
        }
        return list;
    }

    private void showData() {
        ListView lvUser = findViewById(R.id.lv_user);

        ArrayAdapter<String> userAdapter = new ArrayAdapter<>(
                MainActivity.this,
                R.layout.item_user,
                R.id.tv_user,
                users
        );
        lvUser.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbAdapter != null) {
            dbAdapter.close();
        }
    }
}