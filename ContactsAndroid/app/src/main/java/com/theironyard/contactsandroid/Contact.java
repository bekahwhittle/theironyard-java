package com.theironyard.contactsandroid;

import java.io.Serializable;

/**
 * Created by rdw1995 on 9/28/16.
 */

public class Contact implements Serializable {
    String name;
    String phone;

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, phone);
    }
}
