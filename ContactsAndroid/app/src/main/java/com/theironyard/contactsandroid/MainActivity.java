package com.theironyard.contactsandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener, View.OnClickListener, AdapterView.OnItemClickListener {

    EditText name;
    EditText phone;
    Button addButton;
    ListView list;
    ArrayAdapter<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        addButton = (Button) findViewById(R.id.addButton);
        list = (ListView) findViewById(R.id.list);

        contacts = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(contacts);

        addButton.setOnClickListener(this);
        list.setOnItemLongClickListener(this);
        list.setOnItemClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String person = name.getText().toString();
        String number = phone.getText().toString();
        if (!person.isEmpty() && !number.isEmpty()){
            Contact contact = new Contact(person, number);
            contacts.add(contact);
            name.setText("");
            phone.setText("");

        }
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Contact contact = contacts.getItem(position);
        contacts.remove(contact);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (position == 0){
            Intent myIntent = new Intent(view.getContext(), ClickContact.class);
            myIntent.putExtra("Contact",contacts.getItem(position));
            startActivityForResult(myIntent, 0);
//        }
    }

}
