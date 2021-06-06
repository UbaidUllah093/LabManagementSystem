package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewCustomer extends AppCompatActivity {

    private ListView obj;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        mydb = new DBHelper(ViewCustomer.this);

        ArrayList array_list = mydb.getCustomers();
        ArrayAdapter arrayAdapter = new ArrayAdapter(ViewCustomer.this,R.layout.mylist, array_list);

        obj = (ListView) findViewById(R.id.arraylist);
        obj.setAdapter(arrayAdapter);

    }
}