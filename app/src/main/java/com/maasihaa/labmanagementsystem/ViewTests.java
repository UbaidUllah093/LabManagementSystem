package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.util.Log;
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

public class ViewTests extends AppCompatActivity implements View.OnClickListener {

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
        int count = 1;
        int tv_count = 1001;
        while(res.isAfterLast() == false){

            int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            int width = 75;
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(width,height);

            TableRow tableRow = new TableRow(ViewTests.this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            tableRow.setLayoutParams(layoutParams);
            tableRow.setClickable(true);
            tableRow.setBackgroundColor(Color.parseColor("#5582cf"));

            TextView textView = new TextView(ViewTests.this);
            textView.setText(res.getString(res.getColumnIndex("TestName")));
            textView.setHint("testing");
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.WHITE);

            //textView.setId(count);
            //textView.setLayoutParams(layoutParams);
            tableRow.addView(textView, 0);

            TextView textView2 = new TextView(ViewTests.this);
            textView2.setText(res.getString(res.getColumnIndex("Price")));
            textView2.setGravity(Gravity.CENTER);
            textView2.setTextColor(Color.WHITE);
            //textView2.setLayoutParams(layoutParams);
            tableRow.addView(textView2, 1);

            TextView textView3 = new TextView(ViewTests.this);
            textView3.setText(res.getString(res.getColumnIndex("Time")));
            textView3.setGravity(Gravity.CENTER);
            textView3.setTextColor(Color.WHITE);

            //textView3.setLayoutParams(layoutParams);
            tableRow.addView(textView3, 2);

            Button btn = new Button(ViewTests.this);
            btn.setText("Delete");
            btn.setHint(res.getString(res.getColumnIndex("TestName")));
            btn.setId(count);
            btn.setOnClickListener(this);
            btn.setTextColor(Color.WHITE);

            tableRow.addView(btn, 3);

            tableLayout.addView(tableRow);
            res.moveToNext();
            count++;
            tv_count++;
        }

        btn_newtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),new_test.class));
            }
        });


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        //int count = 1000 + id;
        //Button tv = findViewById(id);
        Button tv = findViewById(id);
        if (null != tv) {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Confirm")
                    .setMessage("Are you sure you want to Delete: " + tv.getHint() + "?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            mydb.deleteBooks(tv.getHint());
                            Intent i= new Intent(ViewTests.this, ViewTests.class);
                            finish();
                            startActivity(i);
                        }

                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();

            Log.i("onClick", "Clicked on row :: " + id + " check kr " + tv.getHint()) ;
            Toast.makeText(ViewTests.this, "Clicked on row :: " + id + ", Text :: " + tv.getText(), Toast.LENGTH_SHORT).show();
            //mydb.deleteBooks();

        }
    }
}