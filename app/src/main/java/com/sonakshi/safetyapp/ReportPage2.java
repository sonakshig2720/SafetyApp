package com.sonakshi.safetyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class ReportPage2 extends AppCompatActivity {

    CheckBox c1,c2,c3,c4,c5,c6;
    EditText et_other;
    RadioGroup rg_gen,rg_age,rg_time;
    RadioButton m,f,o,a1,a2,a3,a4,t1,t2,t3,t4,t5,t6;
    Button btn_report;
    String gender,age,time;
    boolean check;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_page2);

        Intent intent=getIntent();
        String str_email=intent.getStringExtra("emailid");
        String str_country=intent.getStringExtra("country");
        String str_state=intent.getStringExtra("state");
        String str_city=intent.getStringExtra("city");
        String str_area=intent.getStringExtra("area");


        c1=(CheckBox)findViewById(R.id.c1);
        c2=(CheckBox)findViewById(R.id.c2);
        c3=(CheckBox)findViewById(R.id.c3);
        c4=(CheckBox)findViewById(R.id.c4);
        c5=(CheckBox)findViewById(R.id.c5);
        c6=(CheckBox)findViewById(R.id.c6);
        rg_gen=(RadioGroup)findViewById(R.id.rg_gen);
        rg_age=(RadioGroup)findViewById(R.id.rg_age);
        rg_time=(RadioGroup)findViewById(R.id.rg_time);
        a1=(RadioButton) findViewById(R.id.a1);
        a2=(RadioButton)findViewById(R.id.a2);
        a3=(RadioButton)findViewById(R.id.a3);
        a4=(RadioButton)findViewById(R.id.a4);
        t1=(RadioButton)findViewById(R.id.t1);
        t2=(RadioButton)findViewById(R.id.t2);
        t3=(RadioButton)findViewById(R.id.t3);
        t4=(RadioButton)findViewById(R.id.t4);
        t5=(RadioButton)findViewById(R.id.t5);
        t6=(RadioButton)findViewById(R.id.t6);
        et_other=(EditText)findViewById(R.id.et_other);
        m=(RadioButton)findViewById(R.id.m);
        f=(RadioButton)findViewById(R.id.f);
        o=(RadioButton)findViewById(R.id.o);
        btn_report=(Button)findViewById(R.id.btn_report);

        et_other.setVisibility(View.INVISIBLE);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!c6.isChecked())
                    et_other.setVisibility(View.INVISIBLE);
                else
                    et_other.setVisibility(View.VISIBLE);
            }
        });

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check=false;
                String incidents[]=new String[6];
                int i=0,a=0,t=0;

                if(!c1.isChecked() && !c2.isChecked() && !c3.isChecked() && !c4.isChecked() && !c5.isChecked() && !c6.isChecked())
                {
                    check=true;
                    Toast.makeText(getApplicationContext(),"Please select on of the incident",Toast.LENGTH_SHORT).show();
                }
                else {

                    if (c1.isChecked()) {
                        incidents[i] = c1.getText().toString();
                        i++;
                    }
                    if (c2.isChecked()) {
                        incidents[i] = c2.getText().toString();
                        i++;
                    }
                    if (c3.isChecked()) {
                        incidents[i] = c3.getText().toString();
                        i++;
                    }
                    if (c4.isChecked()) {
                        incidents[i] = c4.getText().toString();
                        i++;
                    }
                    if (c5.isChecked()) {
                        incidents[i] = c5.getText().toString();
                        i++;
                    }
                    if (c6.isChecked()) {
                        et_other.setEnabled(true);
                        incidents[i] = et_other.getText().toString();
                        i++;
                    }
                }

                if(m.isChecked())
                {
                    gender="m";
                }
                else if(f.isChecked())
                {
                    gender="f";
                }
                else if(o.isChecked())
                {
                    gender="o";
                }
                else
                {
                    check=true;
                    Toast.makeText(getApplicationContext(),"Please select gender",Toast.LENGTH_SHORT).show();
                }

                if(a1.isChecked())
                {
                    age=a1.getText().toString();
                }
                else if(a2.isChecked())
                {
                    age=a2.getText().toString();
                }
                else if(a3.isChecked())
                {
                    age=a3.getText().toString();
                }
                else if(a4.isChecked())
                {
                    age=a4.getText().toString();
                }
                else
                {
                    check=true;
                    Toast.makeText(getApplicationContext(),"Please select age",Toast.LENGTH_SHORT).show();
                }

                if(t1.isChecked())
                {
                    time=t1.getText().toString();
                }
                else if(t2.isChecked())
                {
                    time=t2.getText().toString();
                }
                else if(t3.isChecked())
                {
                    time=t3.getText().toString();
                }
                else if(t4.isChecked())
                {
                    time=t4.getText().toString();
                }
                else if(t5.isChecked())
                {
                    time=t5.getText().toString();
                }
                else if(t6.isChecked())
                {
                    time=t6.getText().toString();
                }
                else
                {
                    check=true;
                    Toast.makeText(getApplicationContext(),"Please select time",Toast.LENGTH_SHORT).show();
                }


                /*for(int k=0;k<i;k++)
                    System.out.println(incidents[k]);
                System.out.println(gender);
                System.out.println(age);
                System.out.println(time);
                System.out.println(str_email);
                System.out.println(str_country);
                System.out.println(str_state);
                System.out.println(str_city);
                System.out.println(str_area);*/

                if(check==false)
                    insert(incidents,str_email,str_country,str_state,str_city,str_area,i);

            }
        });
    }

    public void insert(String[] incidents,String str_email, String str_country,String str_state, String str_city, String str_area,int i)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                //Starting Write and Read data with URL
                //Creating array for parameters
                String[] field = new String[8];
                field[0] = "emailid";
                field[1] = "country";
                field[2] = "state";
                field[3] = "city";
                field[4] = "area";
                field[5] = "gender";
                field[6] = "age";
                field[7] = "incident_time";
                //Creating array for data
                String[] data = new String[8];
                data[0] = str_email;
                data[1] = str_country;
                data[2] = str_state;
                data[3] = str_city;
                data[4] = str_area;
                data[5] = gender;
                data[6] = age;
                data[7] = time;
                PutData putData = new PutData("http://192.168.1.5/SafetyApp/Insert_report.php", "POST", field, data);
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        String result = putData.getResult();
                        /*if(result.equals("Inserted into all tables"))
                        {
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                        }*/
                    }
                }
                for(int j=0;j<i;j++)
                {
                    String[] field2=new String[2];
                    field2[0]="emailid";
                    field2[1]="incidents";
                    String[] data2=new String[2];

                    data2[0]=str_email;
                    data2[1]= incidents[j];
                    PutData putData2 = new PutData("http://192.168.1.5/SafetyApp/Insert_incident.php", "POST", field2, data2);
                    if (putData2.startPut()) {
                        if (putData2.onComplete()) {
                            String result = putData2.getResult();
                            /*if (result.equals("Inserted into incidents")) {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
                            }*/
                        }
                    }
                }

            }

        });
        Toast.makeText(getApplicationContext(), "Thank You for reporting", Toast.LENGTH_LONG).show();
        Intent it=new Intent(ReportPage2.this,WelcomePage.class);
        startActivity(it);
    }
}