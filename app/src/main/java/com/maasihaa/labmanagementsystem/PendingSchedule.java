package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PendingSchedule extends AppCompatActivity implements SearchView.OnQueryTextListener, View.OnClickListener {

    SearchView sv_customer2;
    Spinner sp_customer2;
    DBHelper mydb;
    ArrayList<String> customerNames;
    ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_schedule);

        sv_customer2 = findViewById(R.id.sv_customer2);
        sp_customer2 = findViewById(R.id.sp_test2);
        final TableLayout tableLayout = (TableLayout)findViewById(R.id.table_layout2);
        mydb = new DBHelper(getApplicationContext());

        customerNames = mydb.getCustomers();
        adapter = new ListViewAdapter(this, customerNames);
        sp_customer2.setAdapter(adapter);

        Cursor res = mydb.getPendingScheduleComplete();
        res.moveToFirst();
        int count = 1;

        while(res.isAfterLast() == false){

            int height = RelativeLayout.LayoutParams.WRAP_CONTENT;
            int width = RelativeLayout.LayoutParams.WRAP_CONTENT;
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(width,height);

            TableRow tableRow = new TableRow(PendingSchedule.this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.CENTER;
            tableRow.setLayoutParams(layoutParams);
            tableRow.setClickable(true);
            tableRow.setBackgroundColor(Color.parseColor("#5582cf"));

            TextView textView = new TextView(PendingSchedule.this);
            String CustomerId = res.getString(res.getColumnIndex("CustomerID"));
            ArrayList<String> temp = mydb.getCustomerName(CustomerId);
            String CustomerName = temp.get(0);
            textView.setText(CustomerName);
            textView.setTextColor(Color.WHITE);
            textView.setGravity(Gravity.CENTER);
            //textView.setLayoutParams(layoutParams2);
            tableRow.addView(textView, 0);

            ListView textView2 = new ListView(PendingSchedule.this);
            String TestId = res.getString(res.getColumnIndex("TestID"));
            ArrayList<String> temp2 = mydb.getTestName(TestId);
            //String TestName = temp2.get(0);

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.textview_customer,temp2);
            textView2.setAdapter(itemsAdapter);
            textView2.setBackgroundColor(Color.TRANSPARENT);

            //textView2.setText(TestName);
            //textView2.setGravity(Gravity.CENTER);
            //textView2.setLayoutParams(layoutParams);
            tableRow.addView(textView2, 1);

            Button textView3 = new Button(PendingSchedule.this);
            textView3.setText("Delete");
            textView3.setTextColor(Color.WHITE);
            textView3.setGravity(Gravity.CENTER);
            textView3.setId(count);

            textView3.setHint(res.getString(res.getColumnIndex("TestID")));
            textView3.setOnClickListener(this);
            //textView3.setLayoutParams(layoutParams);
            tableRow.addView(textView3, 2);

            tableLayout.addView(tableRow);
            res.moveToNext();
            count++;
        }



        sv_customer2.setOnQueryTextListener(this);

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
                    .setMessage("Are you sure you want to remove this Report?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            mydb.deletePending(tv.getHint());
                            Intent i= new Intent(PendingSchedule.this, PendingSchedule.class);
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
            Toast.makeText(PendingSchedule.this, "Clicked on row :: " + id + ", Text :: " + tv.getText(), Toast.LENGTH_SHORT).show();
            //mydb.deleteBooks();

        }
    }
}