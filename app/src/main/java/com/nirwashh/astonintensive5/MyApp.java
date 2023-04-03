package com.nirwashh.astonintensive5;

import android.app.Application;

public class MyApp extends Application {
    public static Contact contact1 = new Contact(1);
    public static Contact contact2 = new Contact(2);
    public static Contact contact3 = new Contact(3);
    public static Contact contact4 = new Contact(4);

    @Override
    public void onCreate() {
        super.onCreate();
        initContacts();
    }

    private void initContacts() {
        contact1.setName(getString(R.string.contact_name_1));
        contact1.setLastname(getString(R.string.contact_lastname_1));
        contact1.setTelNumber(getString(R.string.contact_tel_1));

        contact2.setName(getString(R.string.contact_name_2));
        contact2.setLastname(getString(R.string.contact_lastname_2));
        contact2.setTelNumber(getString(R.string.contact_tel_2));

        contact3.setName(getString(R.string.contact_name_3));
        contact3.setLastname(getString(R.string.contact_lastname_3));
        contact3.setTelNumber(getString(R.string.contact_tel_3));

        contact4.setName(getString(R.string.contact_name_4));
        contact4.setLastname(getString(R.string.contact_lastname_4));
        contact4.setTelNumber(getString(R.string.contact_tel_4));
    }
}
