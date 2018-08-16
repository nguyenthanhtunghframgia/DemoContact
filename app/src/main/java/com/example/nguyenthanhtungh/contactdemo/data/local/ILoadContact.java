package com.example.nguyenthanhtungh.contactdemo.data.local;

import com.example.nguyenthanhtungh.contactdemo.data.model.Contact;

import java.util.ArrayList;

public interface ILoadContact {
    void onContactLoadSuccess(ArrayList<Contact> contacts);

    void onContactEmpty();
}
