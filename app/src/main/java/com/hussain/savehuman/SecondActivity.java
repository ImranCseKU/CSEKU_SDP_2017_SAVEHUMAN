package com.hussain.savehuman;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class SecondActivity extends AppCompatActivity {
    String link;
    Button sign_up;
    EditText name,id,mobile_no,email,password,date;
    AutoCompleteTextView bloodGroup,discipline,dist;
   // String link = "http://192.168.0.102/savehuman/insertDonor.php";

    String bloodGroup_suggest[] ={"A+","A-","AB+","AB-","B+","B-","O+","O-"};
    String discipline_suggest[]={"CSE","CHEMISTY","PHYSICS","ARCHITECTURE","URP","MATH","STATISTICS","ECE",
                "Agrotechnology","BGE","Environmental Science","FMRT","FWT","Pharmacy","Soil Science",
                "Business Administration","Human Resource Management","Development Studies","Economics",
                "Journalism","Sociology","History and Civilization","Bangla","English","Law and Justice"};

    String location_suggest[] ={"BARGUNA","BARISAL","BHOLA","BANDARBAN","BRAHMANBARIA","BAGERHAT","BOGRA","CHANDPUR","CHITTAGONG","COMILLA",
            "COX'S BAZAR","CHUADANGA","CHAPAINABABGANJ","DHAKA","DINAJPUR","FENI","FARIDPUR","GAZIPUR","GOPALGANJ","GAIBANDHA","HABIGANJ",
            "JHALOKATI","JAMALPUR","JESSORE","JHENAIDAH","JOYPURHAT","KHAGRACHHARI","KHULNA","KUSHTIA","KURIGRAM","KISHOREGONJ","LAKSHMIPUR",
            "LALMONIRHAT","MADARIPUR","MANIKGANJ","MUNSHIGANJ","MYMENSINGH","MAGURA","MEHERPUR","MAULVIBAZAR","NOAKHALI","NARAYANGANJ",
            "NARSINGDI","NETRAKONA","NARAIL","NAOGAON","NATORE","NILPHAMARI","PATUAKHALI","PIROJPUR","PABNA","PANCHAGARH","RANGAMATI",
            "RAJBARI","RAJSHAHI","RANGPUR","SHARIATPUR","SHERPUR","SATKHIRA","SIRAJGANJ","SUNAMGANJ","SYLHET","TANGAIL","THAKURGAON"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        link=""+getString(R.string.localhost_IP)+"insertDonor.php";
        dist= (AutoCompleteTextView) findViewById(R.id.dist_editAutoText);
        AutoText();
        Adddata();
    }
    public void AutoText()
    {
        discipline= (AutoCompleteTextView) findViewById(R.id.discipline_editText);
        bloodGroup= (AutoCompleteTextView) findViewById(R.id.Complete_editText);
        ArrayAdapter adapter_blood=new ArrayAdapter(SecondActivity.this,android.R.layout.select_dialog_item,bloodGroup_suggest);
        ArrayAdapter adapter_discipline=new ArrayAdapter(SecondActivity.this,android.R.layout.select_dialog_item,discipline_suggest);
        ArrayAdapter adapter_Location=new ArrayAdapter(SecondActivity.this,android.R.layout.select_dialog_item,location_suggest);
        discipline.setThreshold(1);
        bloodGroup.setThreshold(1);
        dist.setThreshold(1);
        discipline.setAdapter(adapter_discipline);
        bloodGroup.setAdapter(adapter_blood);
        dist.setAdapter(adapter_Location);
    }
    public void Adddata()
    {
        sign_up= (Button) findViewById(R.id.signUp_button);
        name= (EditText) findViewById(R.id.uname_editText);
        id= (EditText) findViewById(R.id.id_editText);
        discipline= (AutoCompleteTextView) findViewById(R.id.discipline_editText);
        bloodGroup= (AutoCompleteTextView) findViewById(R.id.Complete_editText);
        mobile_no= (EditText) findViewById(R.id.contact_editText);
        email= (EditText) findViewById(R.id.email_editText);
        password= (EditText) findViewById(R.id.pass_editText);
        date= (EditText) findViewById(R.id.date2);
        //----------insert the details in database--------
       sign_up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() &&  !password.getText().toString().isEmpty() && !bloodGroup.getText().toString().isEmpty()
                       && !discipline.getText().toString().isEmpty() && !mobile_no.getText().toString().isEmpty() && !dist.getText().toString().isEmpty() && !date.getText().toString().isEmpty())
               {
                   new AsyncInsert().execute(id.getText().toString(),
                           name.getText().toString(),
                           email.getText().toString(),
                           password.getText().toString(),
                           bloodGroup.getText().toString(),
                           discipline.getText().toString(),
                           mobile_no.getText().toString(),
                           dist.getText().toString(),
                           date.getText().toString()
                   );

               }
               else{
                   Toast.makeText(SecondActivity.this,"Please Complete All Field!!", Toast.LENGTH_LONG).show();
               }


           }
       });
    }

    private class AsyncInsert extends AsyncTask<String,String,String>
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
                return "malformed exception";
            }

            try
            {
                http = (HttpURLConnection) url.openConnection();
                http.setReadTimeout(15000);
                http.setConnectTimeout(7000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                //             Uri.Builder builder = new Uri.Builder().appendQueryParameter("name", params[1]);


                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("id", params[0])
                        .appendQueryParameter("name", params[1])
                        .appendQueryParameter("email", params[2])
                        .appendQueryParameter("password", params[3])
                        .appendQueryParameter("bg", params[4])
                        .appendQueryParameter("disci", params[5])
                        .appendQueryParameter("mobile", params[6])
                        .appendQueryParameter("dist", params[7])
                        .appendQueryParameter("date", params[8]);
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

                return  e.toString();
                // e.printStackTrace();
                //return "io exception";
            }

            try {

                int response_code = http.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    //return "Successful SignUp!";
                    // Read data sent from server
                    InputStream input = http.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input,"iso-8859-1") );
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    // Pass data to onPostExecute method
                    return(result.toString());

                }else{

                    return("SignUp failed");
                }


            } catch (IOException e) {
                e.printStackTrace();
                return "last io exception";
            } finally {
                http.disconnect();

            }



        }
        @Override
        protected void onPostExecute(String res)
        {
            if(!res.equals(""))
            {
                Toast.makeText(SecondActivity.this,"Join Successfull", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(SecondActivity.this,"Unsuccessful Join!", Toast.LENGTH_LONG).show();
            }

//            if(res.equalsIgnoreCase("exception") || res.equalsIgnoreCase("unsuccessfull"))
//                Toast.makeText(SecondActivity.this, "Unsuccessful Join!", Toast.LENGTH_LONG).show();

        }
    }
}
