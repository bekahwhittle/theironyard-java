package com.theironyard;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    TextField textName;

    @FXML
    TextField textPhone;

    @FXML
    TextField textEmail;

    @FXML
    ListView contactList;

    ObservableList<Contact> contacts = FXCollections.observableArrayList();

    public void onAdd () throws IOException {
        String name = textName.getText();
        String phone = textPhone.getText();
        String email = textEmail.getText();
        Contact contact = new Contact(name, phone, email);
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()){
            contacts.add(contact);
            saveContacts();
        }
    }
    public void onRemove () throws IOException {
        Contact contact = (Contact) contactList.getSelectionModel().getSelectedItem();
        contacts.remove(contact);
        saveContacts();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //contactList.setItems(contacts);
        try {
            loadContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveContacts () throws IOException {
        File f = new File("contacts.json");
        JsonSerializer serializer = new JsonSerializer();
        ArrayList<Contact> arr = new ArrayList<>();
        arr.addAll(contacts);
        ContactsWrapper cw = new ContactsWrapper(arr);
        String json = serializer.deep(true).serialize(cw);
        FileWriter fw = new FileWriter(f);
        fw.write(json);
        fw.close();
    }

    public void loadContacts () throws IOException {
        //Deal with the File
        File f = new File("contacts.json");
        FileReader fr = new FileReader(f);
        int fileSize = (int) f.length();
        char[] contents = new char[fileSize];
        fr.read(contents, 0, fileSize);

        //Parse the json file
        JsonParser parser = new JsonParser();
        ContactsWrapper cW = parser.parse(contents, ContactsWrapper.class);

        //Setup the contacts variable of the Controller
        contacts = FXCollections.observableArrayList();
        contacts.addAll(cW.getContacts());
        System.out.println(cW.contacts);

        //Set the UI to know about this list we just loaded
        contactList.setItems(contacts);
    }
}
