package com.example.ass3android;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcv1;
    private String TAG = MainActivity.class.getSimpleName();
    Button btadd;

    private static String url = "http://192.168.43.223/ass4new/showuser.php";
    prgmadap adap;
     private  ArrayList<User> userList;
     String ID;
    //List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btadd=(Button)findViewById(R.id.btnadd);
          userList = new ArrayList<User>();

         rcv1 = (RecyclerView) findViewById(R.id.rcv);
        rcv1.setLayoutManager(new LinearLayoutManager(this));
        rcv1.setHasFixedSize(true);
        new getUser().execute();

    }
    private class getUser extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
          adap=new prgmadap(MainActivity.this,userList);
            rcv1.setAdapter((RecyclerView.Adapter)adap);

            }

        @Override
        protected Void doInBackground(Void... voids) {

            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

           if (jsonStr != null)
            {
                try {
                   // JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray users = new JSONArray(jsonStr);

                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);

                        ID=c.getString("id");
                        String firsname = c.getString("firstname");
                        String lastname = c.getString("lastname");
                        String email = c.getString("email");
                        String dob = c.getString("dob");

                        User user1=new User(ID,firsname,lastname,email,dob);
                        userList.add(user1);

                    }

                }
                catch (final JSONException e)
                {
                    Log.e(TAG,"json parsing error:"+e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "json parsing error:" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
            else
            {
                Log.e(TAG,"could not get json server");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server.",Toast.LENGTH_LONG).show();

                    }
                });
            }
            return null;
           }
    }
    public void openReg(View view)
    {
        Intent intent=new Intent(MainActivity.this,adduser.class);
        startActivity(intent);

    }

}