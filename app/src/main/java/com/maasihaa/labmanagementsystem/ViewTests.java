package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ViewTests extends AppCompatActivity {

    FloatingActionButton btn_newtest;
    DBHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tests);
        btn_newtest= findViewById(R.id.btn_newtest);
        mydb = new DBHelper(getApplicationContext());

        final TableLayout tableLayout = (TableLayout)findViewById(R.id.table_layout);

        Cursor res = mydb.getTests();

        res.moveToFirst();

        while(res.isAfterLast() == false){

            int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            int width = 75;
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(width,height);

            TableRow tableRow = new TableRow(ViewTests.this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(layoutParams);

            TextView textView = new TextView(ViewTests.this);
            textView.setText(res.getString(res.getColumnIndex("TestName")));
            textView.setGravity(Gravity.CENTER);
            //textView.setLayoutParams(layoutParams);
            tableRow.addView(textView, 0);

            TextView textView2 = new TextView(ViewTests.this);
            textView2.setText(res.getString(res.getColumnIndex("Price")));
            textView2.setGravity(Gravity.CENTER);
            //textView2.setLayoutParams(layoutParams);
            tableRow.addView(textView2, 1);

            TextView textView3 = new TextView(ViewTests.this);
            textView3.setText(res.getString(res.getColumnIndex("Time")));
            textView3.setGravity(Gravity.CENTER);
            //textView3.setLayoutParams(layoutParams);
            tableRow.addView(textView3, 2);

            Button btn = new Button(ViewTests.this);
            btn.setText("Delete");
            tableRow.addView(btn, 3);

            tableLayout.addView(tableRow);
            res.moveToNext();
        }

        btn_newtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),new_test.class));
            }
        });


    }

}