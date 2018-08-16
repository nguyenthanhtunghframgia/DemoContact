package com.example.nguyenthanhtungh.contactdemo.data.local;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.nguyenthanhtungh.contactdemo.data.model.Contact;

import java.util.ArrayList;

public class ContactManager {
    private ILoadContact iLoadContact;

    public ContactManager(ILoadContact iLoadContact) {
        this.iLoadContact = iLoadContact;
    }

    public void getContacts(Context context) {
        ArrayList<Contact> contacts = new ArrayList<>();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};
        Cursor cursor = context.getContentResolver().query(uri, projection,
                null, null, null);
        if (cursor == null) {
            iLoadContact.onContactEmpty();
        } else {
            int indexColumnName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            int indexColumnPhoneNumber = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            while (cursor.moveToNext()) {
                String name;
                String phoneNumber;
                name = cursor.getString(indexColumnName);
                phoneNumber = cursor.getString(indexColumnPhoneNumber);
                contacts.add(new Contact(name, phoneNumber));
            }
            cursor.close();
            iLoadContact.onContactLoadSuccess(contacts);
        }
    }
}
