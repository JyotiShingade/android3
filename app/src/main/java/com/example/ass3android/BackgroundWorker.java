package com.example.ass3android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String,Void,String>
{
    Context context;
    AlertDialog alertDialog;

    public BackgroundWorker(Context context) {

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
        String Regurl="http://192.168.43.223/ass4new/insertu.php";
                try {
                    String firstname = params[0];
                    String lastname = params[1];
                    String address = params[2];
                    String email = params[3];
                    String password = params[4];
                    String gender = params[5];
                    String dob = params[6];
                    String age = params[7];
                    String zipcode = params[8];
                    String mobileno = params[9];

                    URL url = new URL(Regurl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setConnectTimeout(20000);

                    /*    Uri.Builder builder = new Uri.Builder().appendQueryParameter("firstanme", params[0])
                            .appendQueryParameter("lastanme", params[1])
                            .appendQueryParameter("address", params[2])
                            .appendQueryParameter("email", params[3])
                            .appendQueryParameter("password", params[4])
                            .appendQueryParameter("gender", params[5])
                            .appendQueryParameter("dob", params[6])
                            .appendQueryParameter("age", params[7])
                            .appendQueryParameter("zipcode", params[8])
                            .appendQueryParameter("mobileno", params[9]);


                //  String query = builder.build().getEncodedQuery();*/

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String post_data= URLEncoder.encode("firstname","UTF-8")+"="+URLEncoder.encode(firstname,"UTF-8")+"&"
                    +URLEncoder.encode("lastname","UTF-8")+"="+URLEncoder.encode(lastname,"UTF-8")+"&"
                            +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                            +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                            +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                            +URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                            +URLEncoder.encode("dob","UTF-8")+"="+URLEncoder.encode(dob,"UTF-8")+"&"
                            +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                            +URLEncoder.encode("zipcode","UTF-8")+"="+URLEncoder.encode(zipcode,"UTF-8")+"&"
                            +URLEncoder.encode("mobileno","UTF-8")+"="+URLEncoder.encode(mobileno,"UTF-8");

                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    httpURLConnection.connect();

                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                    String result="";
                    String line="";
                    while ((line = bufferedReader.readLine())!=null)
                    {
                        result+=line;
                    }
                    bufferedReader.close();
                    inputStream.close();


                    httpURLConnection.disconnect();
                    return result;


                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
        //}


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Insert Successfull.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        Intent intent=new Intent(context,MainActivity.class);
                        context.startActivity(intent);


                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

}
