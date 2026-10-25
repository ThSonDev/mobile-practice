package com.example.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.example.contact.adapter.ContactAdapter;
import com.example.contact.database.DatabaseHandler;
import com.example.contact.model.Contact;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private List<Contact> contactList;
    private ContactAdapter adapter;
    private ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContact = findViewById(R.id.lv_contact);
        db = new DatabaseHandler(this);

        if (db.getContactsCount() == 0) {
            Log.d("Insert: ", "Inserting..");
            db.addContact(new Contact("Ravi", "9100000000"));
            db.addContact(new Contact("Srinivas", "9199999999"));
            db.addContact(new Contact("Tommy", "9522222222"));
            db.addContact(new Contact("Karthik", "9533333333"));
        }

        contactList = db.getAllContacts();

        adapter = new ContactAdapter(this, R.layout.item_contact, contactList);
        lvContact.setAdapter(adapter);

        lvContact.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = contactList.get(position);
                showDeleteConfirmDialog(selectedContact, position);
                return true;
            }
        });
    }

    private void showDeleteConfirmDialog(final Contact contact, final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa liên hệ: " + contact.getName() + " không?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    db.deleteContact(contact);

                    contactList.remove(position);

                    adapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, "Đã xóa thành công!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }
}