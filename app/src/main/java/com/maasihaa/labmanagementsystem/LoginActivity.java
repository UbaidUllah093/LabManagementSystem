package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_signin;
    TextView tv_forgot_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String s_username = "ub123";


        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        btn_signin = findViewById(R.id.btn_signin);
        tv_forgot_pass = findViewById(R.id.tv_forgot_pass);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_s_username = et_username.getText().toString();
                String et_s_password = et_password.getText().toString();

                if (et_s_username.isEmpty())
                {
                    et_username.setError("Field cannot be left blank.");
                }
                else if(et_s_password.isEmpty())
                {
                    et_password.setError("Field cannot be left blank.");
                }

                SharedPreferences sharepref = getSharedPreferences("remember", MODE_PRIVATE);
                String s_password = sharepref.getString("password","");
                if (!et_s_username.equals(s_username) || !et_s_password.equals(s_password)){
                    Toast.makeText(LoginActivity.this, "Password is Incorrect!", Toast.LENGTH_SHORT).show();
                }
                else{
                    finish();
                    startActivity(new Intent(getApplicationContext(),Dashboard.class));
                }
            }
        });
     tv_forgot_pass.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(getApplicationContext(), ForgotActivity.class));
         }
     });
    }
}