package com.sonakshi.safetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread th=new Thread()
        {
            public void run()
            {
                try {
                    sleep(1000);
                    Intent it=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(it);
                    finish();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                super.run();
            }
        };
        th.start();
    }
}