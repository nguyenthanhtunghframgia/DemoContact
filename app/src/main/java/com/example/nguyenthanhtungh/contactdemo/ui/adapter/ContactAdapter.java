package com.example.nguyenthanhtungh.contactdemo.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenthanhtungh.contactdemo.R;
import com.example.nguyenthanhtungh.contactdemo.data.model.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private ArrayList<Contact> mContacts;

    public ContactAdapter(ArrayList<Contact> contacts) {
        mContacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_contact_items, viewGroup, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.bindData(mContacts.get(i));
    }

    @Override
    public int getItemCount() {
        return mContacts.size() != 0 ? mContacts.size() : 0;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextName;
        private TextView mTextPhoneNumber;

        private ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_people_name);
            mTextPhoneNumber = itemView.findViewById(R.id.text_people_phone_number);
        }

        private void bindData(Contact contact) {
            mTextName.setText(contact.getName());
            mTextPhoneNumber.setText(contact.getPhoneNumber());
        }
    }
}
