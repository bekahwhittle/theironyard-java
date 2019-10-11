package com.theironyard;

import java.util.ArrayList;

/**
 * Created by rdw1995 on 9/27/16.
 */
public class ContactsWrapper {
    ArrayList<Contact> contacts;

    public ContactsWrapper (){
    }

    public ContactsWrapper(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
