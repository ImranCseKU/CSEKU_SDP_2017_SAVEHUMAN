package com.hussain.savehuman;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import static com.hussain.savehuman.R.id.textView;

public class SixthActivity extends AppCompatActivity {

    //DatabaseHelper myDb;
    Button update_btn;
    String my_id;
    String link;
    AutoCompleteTextView location_update;
    String location_suggest[] ={"BARGUNA","BARISAL","BHOLA","BANDARBAN","BRAHMANBARIA","BAGERHAT","BOGRA","CHANDPUR","CHITTAGONG","COMILLA",
            "COX'S BAZAR","CHUADANGA","CHAPAINABABGANJ","DHAKA","DINAJPUR","FENI","FARIDPUR","GAZIPUR","GOPALGANJ","GAIBANDHA","HABIGANJ",
            "JHALOKATI","JAMALPUR","JESSORE","JHENAIDAH","JOYPURHAT","KHAGRACHHARI","KHULNA","KUSHTIA","KURIGRAM","KISHOREGONJ","LAKSHMIPUR",
            "LALMONIRHAT","MADARIPUR","MANIKGANJ","MUNSHIGANJ","MYMENSINGH","MAGURA","MEHERPUR","MAULVIBAZAR","NOAKHALI","NARAYANGANJ",
            "NARSINGDI","NETRAKONA","NARAIL","NAOGAON","NATORE","NILPHAMARI","PATUAKHALI","PIROJPUR","PABNA","PANCHAGARH","RANGAMATI",
            "RAJBARI","RAJSHAHI","RANGPUR","SHARIATPUR","SHERPUR","SATKHIRA","SIRAJGANJ","SUNAMGANJ","SYLHET","TANGAIL","THAKURGAON"};
    //String link = "http://192.168.0.102/savehuman/update.php";

    EditText update_name, update_mobile_no, update_email, update_password, update_ConfirmPassword, datel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        link=""+getString(R.string.localhost_IP)+"update.php";
        location_update= (AutoCompleteTextView) findViewById(R.id.update_Location_autoText);
        onButtonClickUpdate();
        AutoText();
    }

    public void AutoText()
    {
        ArrayAdapter adapter_location=new ArrayAdapter(SixthActivity.this,android.R.layout.select_dialog_item,location_suggest);
        location_update.setThreshold(1);
        location_update.setAdapter(adapter_location);
    }

    public void onButtonClickUpdate() {
        update_btn = (Button) findViewById(R.id.Update_button);
        update_name = (EditText) findViewById(R.id.update_name_editText);
        update_mobile_no = (EditText) findViewById(R.id.update_contact_editText);
        update_email = (EditText) findViewById(R.id.update_email_editText);
        update_password = (EditText) findViewById(R.id.update_pass_editText);
        update_ConfirmPassword = (EditText) findViewById(R.id.update_confirm_pass_editText);
        datel = (EditText) findViewById(R.id.date_editText);
        my_id = getIntent().getStringExtra("ID");

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = update_name.getText().toString();
                String mbl = update_mobile_no.getText().toString();
                String eml = update_email.getText().toString();
                String pass1 = update_password.getText().toString();
                String pass2 = update_ConfirmPassword.getText().toString();
                String date = datel.getText().toString();
                String location=location_update.getText().toString();
                //String dated = last_date.getText().toString();

                if (update_password.getText().toString().equals(update_ConfirmPassword.getText().toString())) {
                    new AsyncTask<String, String, String>() {
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
                                return "Connection Error 1!";
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
                                        .appendQueryParameter("name", params[1])
                                        .appendQueryParameter("mobile", params[2])
                                        .appendQueryParameter("email", params[3])
                                        .appendQueryParameter("pass", params[4])
                                        .appendQueryParameter("dated", params[5])
                                        .appendQueryParameter("LOCATION", params[6]);
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

                                //return  e.toString();
                                e.printStackTrace();
                                return "Connection Error 2!";
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

                                    return("Connection Error 3!");
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
                            if(res.equalsIgnoreCase("true  "))
                            {

                                Toast.makeText(SixthActivity.this, "Update Successfull!!", Toast.LENGTH_LONG).show();
                               // Intent i=new Intent(MainActivity.this,ThirdActivity.class);
                                //i.putExtra("ID",sid);
                                //startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(SixthActivity.this,"Connection Error! 5:"+res+"END",Toast.LENGTH_LONG).show();
                            }
                        }
                    }.execute(my_id,name,mbl,eml,pass1,date,location);
                } else {
                    Toast.makeText(SixthActivity.this, "Password not match", Toast.LENGTH_LONG).show();
                }

                update_name.setText("");
                update_mobile_no.setText("");
                update_email.setText("");
                update_password.setText("");
                update_ConfirmPassword.setText("");
                datel.setText("");
                location_update.setText("");
            }
        });


    }

}
