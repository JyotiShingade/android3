package com.example.ass3android;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class edituser extends AppCompatActivity
{
    private String TAG = MainActivity.class.getSimpleName();


    private DatePickerDialog.OnDateSetListener datePickerListener;

    String email,fname,lname,addr,zipc,mn,pasw,date1,age;
          //  String rdgval;
    EditText etfm1, etln1, etadd1, etem1, etpas1, etage1, etzip1, etmob1, etdob;
    //RadioButton rb1, rb2;
    Button b1;
   // RadioGroup rdbg;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);


        etfm1 = (EditText) findViewById(R.id.etfne);
        etln1 = (EditText) findViewById(R.id.etlne);
        etadd1 = (EditText) findViewById(R.id.etadde);
        etem1 = (EditText) findViewById(R.id.eteme);
        etpas1 = (EditText) findViewById(R.id.etpase);
      //  rb1 = (RadioButton) findViewById(R.id.rdb1e);
       // rb2 = (RadioButton) findViewById(R.id.rdb2e);
        etdob = (EditText) findViewById(R.id.etdatee);
        etage1 = (EditText) findViewById(R.id.etagee);
        etzip1 = (EditText) findViewById(R.id.etzipe);
        etmob1 = (EditText) findViewById(R.id.etmobe);
      //  rdbg = (RadioGroup) findViewById(R.id.rdbg1e);
        b1 = (Button) findViewById(R.id.buttone);


        etdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), datePickerListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override

            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);
                String format = new SimpleDateFormat("dd MMM YYY").format(c.getTime());
                etdob.setText(format);
            }
        };

        new selectUser().execute();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fname=etfm1.getText().toString();
                lname=etln1.getText().toString();
                addr=etadd1.getText().toString();
                email=etem1.getText().toString();
                pasw=etpas1.getText().toString();
               // rdgval = ((RadioButton)findViewById(rdbg.getCheckedRadioButtonId())).getText().toString();

                date1=etdob.getText().toString();
                age=etage1.getText().toString();
                zipc=etzip1.getText().toString();
                mn=etmob1.getText().toString();

                Updateuser updateuser=new Updateuser(getApplicationContext());
                updateuser.execute(fname,lname,addr,email,pasw,date1,age,zipc,mn);
            }
        });


    }

    class Updateuser extends AsyncTask<String, String, String>
    {
        HttpURLConnection httpURLConnection;
        Context context;

        public Updateuser(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params)
        {

            Bundle bundle=getIntent().getExtras();
            id=bundle.getString("id");
//            String Regurl = "http://192.168.2.7/ass4new/edituser.php";
            String Regurl = "http://192.168.43.223/ass4new/edituser.php";

            try {
                String firstname = params[0];
                String lastname = params[1];
                String address = params[2];
                String email = params[3];
                String password = params[4];
              //  String gender = params[5];
                String dob = params[5];
                String age = params[6];
                String zipcode = params[7];
                String mobileno = params[8];

                URL url = new URL(Regurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setConnectTimeout(20000);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String post_data=URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
                         +URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(firstname,"UTF-8")+"&"
                        +URLEncoder.encode("lastname","UTF-8")+"="+URLEncoder.encode(lastname,"UTF-8")+"&"
                        +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                        +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                      //  +URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                        +URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+"&"
                        +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                        +URLEncoder.encode("zipcode","UTF-8")+"="+URLEncoder.encode(zipcode,"UTF-8")+"&"
                        +URLEncoder.encode("mobileno","UTF-8")+"="+URLEncoder.encode(mobileno,"UTF-8");



              /*  Uri.Builder builder = new Uri.Builder().appendQueryParameter("id", id)
                        .appendQueryParameter("firstanme", fname)
                        .appendQueryParameter("lastanme", lname)
                        .appendQueryParameter("address", addr)
                        .appendQueryParameter("email", email)
                        .appendQueryParameter("password", pasw)
                        .appendQueryParameter("dob", date1)
                        .appendQueryParameter("age", age)
                        .appendQueryParameter("zipcode", zipc)
                        .appendQueryParameter("mobileno", mn);
                String query = builder.build().getEncodedQuery();*/


                bufferedWriter.write(post_data);

                bufferedWriter.flush();
                bufferedWriter.close();
                httpURLConnection.connect();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                StringBuilder result=new StringBuilder();
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    result.append(line);
                }
                bufferedReader.close();
                inputStream.close();


                httpURLConnection.disconnect();
                return (result.toString());
                //  Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
            catch (ProtocolException e) {
                e.printStackTrace();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
              //  Toast.makeText(edituser.this, ""+e, Toast.LENGTH_SHORT).show();
            }
            return null;

            }
            @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            Toast.makeText(context, "update success", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(edituser.this,MainActivity.class);
            startActivity(intent);
            /* AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage("update Successfull.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();*/


        }

    }


    public class selectUser extends AsyncTask<String, String, String>
    {
        String Regurl = "http://192.168.43.223/ass4new/editinsert.php";
        HttpURLConnection httpURLConnection;

        Context context;

        public selectUser() {
            this.context = context;
            this.Regurl = Regurl;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                // URL url = new URL(Regurl );
              //  id = getIntent().getExtras().getString("id");

                Bundle bundle=getIntent().getExtras();
                 id=bundle.getString("id");

                URL url = new URL(Regurl);
                httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setConnectTimeout(20000);

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("id", id);


                String query = builder.build().getEncodedQuery();
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                bufferedWriter.write(query);
                bufferedWriter.flush();
                bufferedWriter.close();
                httpURLConnection.connect();


            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            try {
                int resp = httpURLConnection.getResponseCode();

                if (resp == httpURLConnection.HTTP_OK) {

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null)
                    {
                        result.append(line);
                    }

                    bufferedReader.close();
                    inputStream.close();
                  //  Toast.makeText(context, ""+result, Toast.LENGTH_SHORT).show();
                    return (result.toString());


                }
                else
                    {
                    return "unsuccess";
                    }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {

                    httpURLConnection.disconnect();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try
            {
                JSONArray jr = new JSONArray(result);

                for (int i = 0; i < jr.length(); i++)
                {
                    JSONObject c = jr.getJSONObject(i);

                    etfm1.setText(c.getString("firstname"));
                    etln1.setText(c.getString("lastname"));
                    etadd1.setText(c.getString("address"));
                    etem1.setText(c.getString("email"));
                    etpas1.setText(c.getString("password"));
                 //   rdbg.check(c.getInt("gender"));
                    etdob.setText(c.getString("dob"));
                    etage1.setText(c.getString("age"));
                    etzip1.setText(c.getString("zipcode"));
                    etmob1.setText(c.getString("mobileno"));



                }

            }
            catch (JSONException e) {
               // Toast.makeText(edituser.this,e.toString(), Toast.LENGTH_SHORT).show();
               e.printStackTrace();

            }



            //Toast.makeText(edituser.this, result.toString(), Toast.LENGTH_LONG).show();

        }

    }
}
