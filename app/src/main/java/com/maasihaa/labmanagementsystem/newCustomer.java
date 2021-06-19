package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class newCustomer extends AppCompatActivity {

    EditText et_name,et_address,et_cnic,et_number,et_age;
    Button btn_saveCustomer;
    ToggleButton sw_gender;
    DBHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);

        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        et_cnic = findViewById(R.id.et_cnic);
        et_address = findViewById(R.id.et_address);
        et_age = findViewById(R.id.et_age);
        sw_gender = findViewById(R.id.switch_gender);
        btn_saveCustomer = findViewById(R.id.btn_saveCustomer);
        mydb = new DBHelper(this);

        btn_saveCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String et_s_name = et_name.getText().toString();
                String et_s_number = et_number.getText().toString();
                String et_s_cnic = et_cnic.getText().toString();
                String et_s_address = et_address.getText().toString();
                String et_s_age = et_age.getText().toString();
                String sw_s_gender;

                if(sw_gender.isChecked())
                {
                    sw_s_gender = String.valueOf(sw_gender.getTextOn());
                }
                else{
                    sw_s_gender = String.valueOf(sw_gender.getTextOff());
                }

                if(et_s_name.isEmpty()){
                    et_name.setError("Field cannot be left blank.");
                }
                else if(et_s_number.isEmpty()){
                    et_number.setError("Field cannot be left blank.");
                }
                else if(et_s_cnic.isEmpty()){
                    et_cnic.setError("Field cannot be left blank.");
                }
                else if(et_s_address.isEmpty()){
                    et_address.setError("Field cannot be left blank.");
                }
                else if(et_s_age.isEmpty()){
                    et_age.setError("Field cannot be left blank.");
                }
                else {
                    if(mydb.Addnewcustomer(et_s_name,et_s_number,et_s_cnic,et_s_address,et_s_age,sw_s_gender)){
                        finish();
                        Toast.makeText(newCustomer.this, "Customer Saved Successfully!", Toast.LENGTH_SHORT).show();
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