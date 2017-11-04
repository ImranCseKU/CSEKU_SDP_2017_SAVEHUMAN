package com.hussain.savehuman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String sid,token,link,app_server_url;
    Button signInbutton,rButton;
    EditText id_text,pass_text;
    //String link = "http://192.168.0.104/savehuman/signincheck.php";
    //String app_server_url="http://192.168.0.104/savehuman/register.php";

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        link=""+getString(R.string.localhost_IP)+"signincheck.php";
        app_server_url=""+getString(R.string.localhost_IP)+"register.php";
        onButtonClick();

    }
    public void onButtonClick()
    {

        signInbutton= (Button) findViewById(R.id.sign_button);
        rButton= (Button) findViewById(R.id.regester_button);

        id_text= (EditText) findViewById(R.id.Id_editText);
        pass_text= (EditText) findViewById(R.id.pass_editText);
        signInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sid = id_text.getText().toString();
                new AsyncSearch().execute(id_text.getText().toString(),pass_text.getText().toString());
                //Toast.makeText(getApplicationContext(), pushNotification.refreshedToken2,Toast.LENGTH_SHORT).show();

                id_text.setText("");
                pass_text.setText("");
            }
        });
       rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);


            }
        });



    }



    private class AsyncSearch extends AsyncTask<String,String,String>
    {
        HttpURLConnection http;
        URL url;
        @Override
        protected String doInBackground(String... params) {
            try
            {
                url = new URL(link);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Connection Error! 1";
            }
            //for(int i=1;i<=3;++i)
                try
                {
                    http = (HttpURLConnection) url.openConnection();
                    http.setReadTimeout(15000);
                    http.setConnectTimeout(7000);
                    http.setRequestMethod("POST");
                    http.setDoInput(true);
                    http.setDoOutput(true);
                    Uri.Builder builder = new Uri.Builder()
                            .appendQueryParameter("id", params[0])
                            .appendQueryParameter("pass", params[1]);
                    String query = builder.build().getEncodedQuery();

                    OutputStream os = http.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    http.connect();
                    //break;
                }
                catch(IOException e)
                {

                     e.printStackTrace();
                    //return "Connection Error! 2";
                    return  e.toString();

                }
            try {

                int response_code = http.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                   // return "Successfully Created account";
                    // Read data sent from server
                    InputStream input = http.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("Connection Error! 3");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Connection Error! 4";
            } finally {
                http.disconnect();
            }

        }
        @Override
        protected void onPostExecute(String res)
        {
            if(res.equalsIgnoreCase("true"))
            {

                Toast.makeText(MainActivity.this, "Successfull!!", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity.this,ThirdActivity.class);
                i.putExtra("ID",sid);
                startActivity(i);
            }
            else if(res.equalsIgnoreCase("false"))
            {
                Toast.makeText(MainActivity.this,"Invalid UserId or Password",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(MainActivity.this,"Connection Error! 5 "+res,Toast.LENGTH_LONG).show();
            }
        }
    }

}
