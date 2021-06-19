package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    Button btn_createCustomer, btn_logout;
    GridView gv_dash;
    FloatingActionButton fab_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btn_logout = findViewById(R.id.btn_logout);
        gv_dash = findViewById(R.id.gv_dash);
        fab_book = findViewById(R.id.floatingActionButton);

        int homescreen[] = {
                R.drawable.database,
                R.drawable.search,
                R.drawable.test,
                R.drawable.time
                //R.drawable.schedule
        };

        String image_details[] = {
                "Add Customer",
                "View Customer",
                "View Test",
                "Pending Reports"
                //"Schedule"
        };

        fab_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BookTest.class));
            }
        });

        GridViewAdapter_Dashboard adapter = new GridViewAdapter_Dashboard(this, homescreen, image_details);
        gv_dash.setAdapter(adapter);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        gv_dash.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                               Intent intent;

                                               switch (position) {
                                                   case 0:
                                                       intent = new Intent(getApplicationContext(), newCustomer.class);
                                                       break;
                                                   case 1:
                                                       intent = new Intent(getApplicationContext(), ViewCustomer.class);
                                                       break;
                                                   case 2:
                                                       intent = new Intent(getApplicationContext(), ViewTests.class);
                                                       break;
                                                   case 3:
                                                       intent = new Intent(getApplicationContext(),PendingSchedule.class);
                                                       break;
                                                   default:
                                                       intent = null;
                                                       break;
                                               }
                                               if (intent != null)
                                               startActivity(intent);
                                           }
                                       }
        );

    }
}