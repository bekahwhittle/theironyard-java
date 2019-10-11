package com.theironyard.todoandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    ListView list;
    EditText text;
    Button addButton;

    ArrayAdapter<ToDoItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);
        text = (EditText) findViewById(R.id.editText);
        addButton = (Button) findViewById(R.id.Button);

        items = new ArrayAdapter<ToDoItem>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(items);

        addButton.setOnClickListener(this);
        list.setOnItemClickListener(this);
        list.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
       String t = text.getText().toString();
        if (!t.isEmpty()){
            ToDoItem item = new ToDoItem(t, false);
            items.add(item);
            text.setText("");
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ToDoItem item = items.getItem(position);
        items.remove(item);
        return true; // have to set to true SINCE we are using item long click
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToDoItem item = items.getItem(position);
        item.isDone = !item.isDone;
        items.notifyDataSetChanged(); // allows for refresh
    }
}
