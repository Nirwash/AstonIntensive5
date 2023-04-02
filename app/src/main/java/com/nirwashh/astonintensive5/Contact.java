package com.nirwashh.astonintensive5;


import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    String name;
    String lastname;
    String telNumber;
    int id;

    public Contact(String name, String lastname, String telNumber, int id) {
        this.name = name;
        this.lastname = lastname;
        this.telNumber = telNumber;
        this.id = id;
    }

    protected Contact(Parcel in) {
        name = in.readString();
        lastname = in.readString();
        telNumber = in.readString();
        id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(lastname);
        dest.writeString(telNumber);
        dest.writeInt(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
