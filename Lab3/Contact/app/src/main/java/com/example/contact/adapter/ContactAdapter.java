package com.example.contact.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.contact.R;
import com.example.contact.model.Contact;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private final Context context;
    private final int resource;
    private final List<Contact> contacts;

    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.contacts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.tv_name);
        TextView tvPhone = convertView.findViewById(R.id.tv_phone);

        Contact contact = contacts.get(position);
        if (contact != null) {
            tvName.setText(contact.getName());
            tvPhone.setText(contact.getPhoneNumber());
        }

        return convertView;
    }
}