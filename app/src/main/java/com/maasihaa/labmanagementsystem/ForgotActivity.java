package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity {

    EditText et_newpass, et_confrimpass;
    Button btn_updatepass;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        et_newpass = findViewById(R.id.pass_old);
        et_confrimpass = findViewById(R.id.pass_new);
        btn_updatepass = findViewById(R.id.btn_updatepass);

        btn_updatepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String st_newpass = et_newpass.getText().toString();
                String st_confirmpass = et_confrimpass.getText().toString();

                if (st_newpass.isEmpty())
                    et_newpass.setError("Field cannot be left blank.");

                if (st_confirmpass.isEmpty())
                    et_confrimpass.setError("Field cannot be left blank.");

                if (!st_newpass.equals(st_confirmpass))
                    et_confrimpass.setError("Password doesn't match!");
                else
                {
                    SharedPreferences sharepref = getSharedPreferences("remember", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharepref.edit();
                    editor.putString("password", st_confirmpass);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    finish();
                    Toast.makeText(ForgotActivity.this, "Please Login with new Password!", Toast.LENGTH_SHORT).show();}

            }
        });



    }


}