package com.example.ass3android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
import java.util.ArrayList;

import java.util.List;

public class prgmadap extends RecyclerView.Adapter<prgmadap.prgmholder>
{

    Button b1,b2;
    private Context mctx;
    private List<User> userList;
    User user;

    public prgmadap(Context mctx,List<User> userList) {
        this.mctx = mctx;
        this.userList = (ArrayList<User>) userList;
    }



    @Override
    public prgmholder onCreateViewHolder( ViewGroup parent, int viewtype)
    {
        LayoutInflater inflater=LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.list_item_layout,parent,false);
        return  new prgmholder(view);
        }

    @Override
    public void onBindViewHolder(prgmholder holder, final int postion)
    {
      user=userList.get(postion);
     //  String id=userList.get(postion).getId();
      holder.txt1.setText(user.getFirstname());
     holder.txt2.setText(user.getLastname());
     holder.txt3.setText(user.getEmail());
     holder.txt4.setText(user.getDob());


     b1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view)
         {

              Intent intent=new Intent(mctx,edituser.class);
              intent.putExtra("id",userList.get(postion).getId());
              mctx.startActivity(intent);

         }
     });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

              new deluser().execute();

            }
        });


    }
    class deluser extends AsyncTask<String, String, String> {

        String Regurl = "http://192.168.43.223/ass4new/deluser.php";
        HttpURLConnection httpURLConnection;
        String id= user.getId();

        Context context;

        public deluser() {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {

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

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

                bufferedReader.close();
                inputStream.close();
                return (result.toString());
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            AlertDialog.Builder builder1 = new AlertDialog.Builder(mctx);
            builder1.setMessage("delete Successfull.");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int n)
                        {
                             dialog.cancel();
                             Intent intent=new Intent(mctx,MainActivity.class);
                             mctx.startActivity(intent);

                        }
                    }
                    );
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }


    }
    @Override
    public int getItemCount()
    {
        return userList.size();
    }

    public class prgmholder extends RecyclerView.ViewHolder
    {
        TextView txt1,txt2,txt3,txt4;
        public prgmholder(View itemView)
        {
            super(itemView);
            b1=(Button) itemView.findViewById(R.id.btnedit);
            b2=(Button) itemView.findViewById(R.id.btndel);
            txt1=(TextView)itemView.findViewById(R.id.txtf);
            txt2=(TextView)itemView.findViewById(R.id.txtl);
            txt3=(TextView)itemView.findViewById(R.id.txte);
            txt4=(TextView)itemView.findViewById(R.id.txtd);


        }
    }
}
