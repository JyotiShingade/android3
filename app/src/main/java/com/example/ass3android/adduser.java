package com.example.ass3android;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class adduser extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener datePickerListener;
    String email,fname,lname,addr,zipc,mn,pasw,date1,age;
    EditText etfm1,etln1,etadd1,etem1,etpas1,etage1,etzip1,etmob1,etdob;
    RadioButton rb1,rb2;
    Button b1;
    RadioGroup rdbg;
    String rdgval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);


        etfm1=(EditText)findViewById(R.id.etfn);
        etln1=(EditText)findViewById(R.id.etln);
        etadd1=(EditText)findViewById(R.id.etadd);
        etem1=(EditText)findViewById(R.id.etem);
        etpas1=(EditText)findViewById(R.id.etpas);
        rb1=(RadioButton)findViewById(R.id.rdb1);
        rb2=(RadioButton)findViewById(R.id.rdb2);
        etdob=(EditText)findViewById(R.id.etdate);
        etage1=(EditText)findViewById(R.id.etage);
        etzip1=(EditText)findViewById(R.id.etzip);
        etmob1=(EditText)findViewById(R.id.etmob);
        rdbg=(RadioGroup)findViewById(R.id.rdbg1);
        b1=(Button)findViewById(R.id.button);



        etdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(view.getContext(),datePickerListener,year,month,day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        datePickerListener=new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                Calendar c=Calendar.getInstance();
                c.set(Calendar.YEAR,year);
                c.set(Calendar.MONTH,month);
                c.set(Calendar.DAY_OF_MONTH,day);
                String format=new SimpleDateFormat("dd MMM YYY").format(c.getTime());
                etdob.setText(format);
            }
        };


    }
        public void onReg(View view)
        {
            fname=etfm1.getText().toString();
            lname=etln1.getText().toString();
            addr=etadd1.getText().toString();
            email=etem1.getText().toString();
            pasw=etpas1.getText().toString();
            rdgval = ((RadioButton)findViewById(rdbg.getCheckedRadioButtonId())).getText().toString();

            date1=etdob.getText().toString();
            age=etage1.getText().toString();
            zipc=etzip1.getText().toString();
            mn=etmob1.getText().toString();

            BackgroundWorker backgroundWorker=new BackgroundWorker(this);
            backgroundWorker.execute(fname,lname,addr,email,pasw,rdgval,date1,age,zipc,mn);

        }

}
