package com.tpfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent loginIntent = new Intent(this, Login.class);

        new CountDownTimer(2000, 1000) {
            public void onFinish() {
               startActivity(loginIntent);
               MainActivity.this.finish();
            }

            public void onTick(long millisUntilFinished) {}
        }.start();
    }
}