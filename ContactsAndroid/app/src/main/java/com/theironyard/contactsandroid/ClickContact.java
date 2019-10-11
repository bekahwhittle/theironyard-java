package com.theironyard.contactsandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ClickContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_contact);
        Contact contact = (Contact) getIntent().getSerializableExtra("Contact");
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(contact.name);
    }
}
