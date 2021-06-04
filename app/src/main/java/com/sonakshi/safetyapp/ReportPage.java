package com.sonakshi.safetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportPage extends AppCompatActivity {

    EditText et_city,et_area,et_coun,et_state;
    Button btn_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page);
        Intent intent=getIntent();
        String str_email=intent.getStringExtra("emailid");

        et_city=(EditText)findViewById(R.id.et_city);
        et_area=(EditText)findViewById(R.id.et_area);
        et_coun=(EditText)findViewById(R.id.et_coun);
        et_state=(EditText)findViewById(R.id.et_state) ;
        btn_next=(Button)findViewById(R.id.btn_next);



        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String country=et_coun.getText().toString();
                String city=et_city.getText().toString();
                String area=et_area.getText().toString();
                String state=et_state.getText().toString();
                if(!city.equals("") && !area.equals(""))
                {
                    Intent it=new Intent(ReportPage.this,ReportPage2.class);
                    it.putExtra("emailid",str_email);
                    it.putExtra("country",country);
                    it.putExtra("state",state);
                    it.putExtra("city",city);
                    it.putExtra("area",area);
                    startActivity(it);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}