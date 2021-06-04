package com.sonakshi.safetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {

    Button btn_report,btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        Intent intent=getIntent();
        String str_email=intent.getStringExtra("emailid");

        btn_report=(Button)findViewById(R.id.btn_report);
        btn_view=(Button)findViewById(R.id.btn_view);

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WelcomePage.this,ReportPage.class);
                it.putExtra("emailid",str_email);
                startActivity(it);
            }
        });

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(WelcomePage.this,ViewPage.class);
                startActivity(it);
            }
        });

    }
}