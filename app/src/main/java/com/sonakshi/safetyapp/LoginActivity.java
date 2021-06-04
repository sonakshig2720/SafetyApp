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
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {

    EditText et_email,et_pwd;
    Button btn_signin;
    TextView tv_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email=(EditText)findViewById(R.id.editTextTextEmailAddress);
        et_pwd=(EditText)findViewById(R.id.editTextTextPassword);
        btn_signin=(Button)findViewById(R.id.bt_signin);
        tv_signup=(TextView)findViewById(R.id.tv_signup);

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent it=new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(it);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email=et_email.getText().toString();
                String str_pwd=et_pwd.getText().toString();

                //Start ProgressBar first (Set visibility VISIBLE)
                if(!str_email.equals("") && !str_pwd.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "emailid";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = str_email;
                            data[1] = str_pwd;
                            PutData putData = new PutData("http://192.168.1.5/SafetyApp/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success"))
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                        Intent it=new Intent(LoginActivity.this,WelcomePage.class);
                                        it.putExtra("emailid",str_email);
                                        startActivity(it);
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
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