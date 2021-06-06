package com.maasihaa.labmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start();

    }

    private void start() {

        new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.d("easd","test");
            }

            @Override
            public void onFinish() {
                finish();
                startActivity(new Intent(getApplicationContext(),Dashboard.class));
            }
        }.start();

    }
}