package com.maasihaa.labmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookTest extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView sv_customer;
    Spinner sp_test,sp_testnames;
    DBHelper mydb;
    ListViewAdapter adapter;
    ListViewTestAdapter testAdapter;
    ArrayList<String> animalNameList,testNames;

    Button btn_booknow;

    ListView lv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_test);

        sv_customer = findViewById(R.id.sv_customer);
        sp_test = findViewById(R.id.sp_test);
        sp_testnames = findViewById(R.id.sp_testnames);
        lv_test = findViewById(R.id.listtest);
        btn_booknow = findViewById(R.id.btn_booknow);

        mydb = new DBHelper(getApplicationContext());

        animalNameList = mydb.getCustomers();

        adapter = new ListViewAdapter(this, animalNameList);
        sp_test.setAdapter(adapter);

        sv_customer.setOnQueryTextListener(this);

        testNames = mydb.getTestNames();
        testAdapter = new ListViewTestAdapter(this,testNames);
        sp_testnames.setAdapter(testAdapter);

        ArrayList<String> mData = new ArrayList<String>();
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.textview_customer,mData);
        lv_test.setAdapter(itemsAdapter);


        sp_testnames.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mData.add(String.valueOf(sp_testnames.getItemAtPosition(position)));
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        lv_test.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mData.remove(position);
                itemsAdapter.notifyDataSetChanged();
            }
        });

        btn_booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(BookTest.this, "spinner" + (CharSequence) sp_test.getSelectedItem(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(BookTest.this, "listview" + mData.size(), Toast.LENGTH_SHORT).show();
                if (sp_test.getSelectedItem() == "No Record Found" || mData.size() == 0)
                    Toast.makeText(BookTest.this, "Required Input is Missing!", Toast.LENGTH_SHORT).show();
                else {
                    mydb.addNewOrdre(String.valueOf(sp_test.getSelectedItem()), mData);
                    finish();
                    startActivity(new Intent(getApplicationContext(), Dashboard.class));
                }

            }
        });

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}