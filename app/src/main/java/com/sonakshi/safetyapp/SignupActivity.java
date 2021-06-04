package com.sonakshi.safetyapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignupActivity extends AppCompatActivity {

    EditText et_name, et_pno, et_email;
    EditText et_pwd;
    Button btn_signup;

    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        et_name=(EditText)findViewById(R.id.et_name);
        et_email=(EditText)findViewById(R.id.et_emailid);
        et_pno=(EditText)findViewById(R.id.et_phonenumber);
        et_pwd=(EditText) findViewById(R.id.et_pwd);
        btn_signup=(Button) findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name=et_name.getText().toString();
                String str_email=et_email.getText().toString();
                String str_phno=et_pno.getText().toString();
                String str_pwd=et_pwd.getText().toString();

                //Start ProgressBar first (Set visibility VISIBLE)
                if(!str_name.equals("") && !str_email.equals("") && !str_phno.equals("") && !str_pwd.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[4];
                            field[0] = "name";
                            field[1] = "phonenumber";
                            field[2] = "emailid";
                            field[3] = "password";
                            //Creating array for data
                            String[] data = new String[4];
                            data[0] = str_name;
                            data[1] = str_phno;
                            data[2] = str_email;
                            data[3] = str_pwd;
                            PutData putData = new PutData("http://192.168.1.5/SafetyApp/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success"))
                                    {

                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                        Intent it=new Intent(SignupActivity.this,LoginActivity.class);
                                        startActivity(it);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}