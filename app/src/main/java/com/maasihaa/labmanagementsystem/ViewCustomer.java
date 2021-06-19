package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        final Dialog dialog = new Dialog(ViewCustomer.this);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
        dialog.setContentView(R.layout.popup_profile);
        dialog.setCanceledOnTouchOutside(true);

        final TextView tv_title = dialog.findViewById(R.id.tv_title);
        final TextView tv_desc = dialog.findViewById(R.id.tv_numb);
        final TextView tv_ag = dialog.findViewById(R.id.tv_ag);
        final TextView tv_gend = dialog.findViewById(R.id.tv_gend);

        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ArrayList<String> temp = mydb.getCustomerDetail((String) obj.getItemAtPosition(position));

                tv_title.setText(temp.get(0));
                tv_desc.setText(temp.get(1));
                tv_ag.setText(temp.get(2));
                tv_gend.setText(temp.get(3));

                //Snackbar.make(v, "Click detected on item " + position, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            }
        });





    }
}