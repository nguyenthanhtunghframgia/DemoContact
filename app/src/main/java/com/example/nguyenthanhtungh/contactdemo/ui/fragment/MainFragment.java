package com.example.nguyenthanhtungh.contactdemo.ui.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nguyenthanhtungh.contactdemo.R;
import com.example.nguyenthanhtungh.contactdemo.data.local.ContactManager;
import com.example.nguyenthanhtungh.contactdemo.data.local.ILoadContact;
import com.example.nguyenthanhtungh.contactdemo.data.model.Contact;
import com.example.nguyenthanhtungh.contactdemo.ui.adapter.ContactAdapter;

import java.util.ArrayList;

public class MainFragment extends Fragment implements ILoadContact {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        initPermission();
    }

    private void initPermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            initViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initViews();
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void initViews() {
        ContactManager contactManager = new ContactManager(this);
        contactManager.getContacts(getContext());
    }

    @Override
    public void onContactLoadSuccess(ArrayList<Contact> contacts) {
        ContactAdapter humanAdapter = new ContactAdapter(contacts);
        RecyclerView recyclerView = getView().findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(humanAdapter);
    }

    @Override
    public void onContactEmpty() {
        Toast.makeText(getContext(), getContext().getResources().getString(R.string.no_contact_available), Toast.LENGTH_LONG).show();
    }
}
