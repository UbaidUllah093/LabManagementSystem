package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class new_test extends AppCompatActivity {

    EditText et_testname,et_testprice,et_testtime;
    Button btn_addnewtest;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_test);
        et_testname = findViewById(R.id.et_testname);
        et_testprice=findViewById(R.id.et_testprice);
        et_testtime=findViewById(R.id.et_testtime);
        btn_addnewtest=findViewById(R.id.btn_addnewtest);
        mydb = new DBHelper(this);

        btn_addnewtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_t_testname= et_testname.getText().toString();
                String et_t_testprice= et_testprice.getText().toString();
                String et_t_testtime= et_testtime.getText().toString();

                if (et_t_testname.isEmpty() )
                    et_testname.setError("Field cannot be left blank.");
               else if (et_t_testprice.isEmpty() )
                    et_testprice.setError("Field cannot be left blank.");
                else if (et_t_testtime.isEmpty() )
                    et_testtime.setError("Field cannot be left blank.");
                else
                {
                    if(mydb.addNewTest(et_t_testname,et_t_testprice,et_t_testtime))
                    {
                        finish();
                        Toast.makeText(new_test.this, "Test Saved Successfully!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}