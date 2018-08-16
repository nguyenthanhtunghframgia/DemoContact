package com.example.nguyenthanhtungh.contactdemo.data.model;

public class Contact {
    private String mName;
    private String mPhoneNumber;

    public Contact(String name, String phoneNumber) {
        mName = name;
        mPhoneNumber = phoneNumber;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }
}
