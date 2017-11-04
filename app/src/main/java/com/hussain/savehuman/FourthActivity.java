package com.hussain.savehuman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Calendar;

public class FourthActivity extends AppCompatActivity {

    private static ListView listView;
    TextView print;

    AutoCompleteTextView location;
    StringBuilder builderx;
    String search="";
    int number, i = 0;
    String link,locationLink;
    //String link = "http://192.168.0.102/savehuman/getList.php";
    String arr[] = new String[1000];
    int arr_id[]=new int[1000];
    String myNumber="";
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list2 = new ArrayList<>();
    ArrayList<String> list3 = new ArrayList<>();
    ArrayList<String> list4 = new ArrayList<>();

    Button search_btn;
    String district_Location[]=new String[100];
    String district_Location2[]=new String[100];

    String location_suggest[] ={"BARGUNA","BARISAL","BHOLA","BANDARBAN","BRAHMANBARIA","BAGERHAT","BOGRA","CHANDPUR","CHITTAGONG","COMILLA",
            "COX'S BAZAR","CHUADANGA","CHAPAINABABGANJ","DHAKA","DINAJPUR","FENI","FARIDPUR","GAZIPUR","GOPALGANJ","GAIBANDHA","HABIGANJ",
            "JHALOKATI","JAMALPUR","JESSORE","JHENAIDAH","JOYPURHAT","KHAGRACHHARI","KHULNA","KUSHTIA","KURIGRAM","KISHOREGONJ","LAKSHMIPUR",
            "LALMONIRHAT","MADARIPUR","MANIKGANJ","MUNSHIGANJ","MYMENSINGH","MAGURA","MEHERPUR","MAULVIBAZAR","NOAKHALI","NARAYANGANJ",
            "NARSINGDI","NETRAKONA","NARAIL","NAOGAON","NATORE","NILPHAMARI","PATUAKHALI","PIROJPUR","PABNA","PANCHAGARH","RANGAMATI",
            "RAJBARI","RAJSHAHI","RANGPUR","SHARIATPUR","SHERPUR","SATKHIRA","SIRAJGANJ","SUNAMGANJ","SYLHET","TANGAIL","THAKURGAON"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        search_btn= (Button) findViewById(R.id.location_btn);
        listView = (ListView) findViewById(R.id.list_view);
        link=""+getString(R.string.localhost_IP)+"getList.php";
        locationLink=""+getString(R.string.localhost_IP)+"getListByLocation.php";
        builderx = new StringBuilder();
        listViewData();
        AutoText();
    }

    public void AutoText()
    {
        location=(AutoCompleteTextView) findViewById(R.id.location_autoTextView);
        ArrayAdapter adapter_location=new ArrayAdapter(FourthActivity.this,android.R.layout.select_dialog_item,location_suggest);
        location.setThreshold(1);
        location.setAdapter(adapter_location);
    }


    public void listViewData() {
        search = getIntent().getStringExtra("GROUP");
        print= (TextView) findViewById(R.id.textView_print);
        print.setText(""+search+" BloodGroup List");
        new AsyncList().execute(search);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample, list2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(FourthActivity.this,FifthActivity.class);
                intent.putExtra("PhoneNumber",arr[position]);
                intent.putExtra("PERSON_DETAILS",list.get(position));
                intent.putExtra("DISTRICT_LOCATION",district_Location[position]);
                startActivity(intent);

            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location_field=location.getText().toString();
                new MyBackgroundWorker().execute(search,location_field);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FourthActivity.this,R.layout.sample,list4);
                listView.setAdapter(adapter);
                location.setText("");

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent=new Intent(FourthActivity.this,FifthActivity.class);
                        intent.putExtra("PhoneNumber",arr[position]);
                        intent.putExtra("PERSON_DETAILS",list3.get(position));
                        intent.putExtra("DISTRICT_LOCATION",district_Location2[position]);
                        startActivity(intent);

                    }
                });


            }
        });





    }


    private class AsyncList extends AsyncTask<String,String,String>
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
                return "Connection Error!";
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
                Uri.Builder builder = new Uri.Builder().appendQueryParameter("bg", params[0]);
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
                return "Connection Error!";
            }
            try {

                    int response_code = http.getResponseCode();

                    // Check if successful connection made
                    if (response_code == HttpURLConnection.HTTP_OK) {
                        // return "Successfully Created account";
                        // Read data sent from server
                        InputStream input = http.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        ArrayList<String> res = new ArrayList<>();
                        StringBuilder result = new StringBuilder();
                        String result2 = "";
                        String line;
                        int cnt = 0,pos=0,location_pos=0;
                        while ((line = reader.readLine()) != null) {
                            cnt++;
                            if(cnt==1)
                            {
                                result.append("ID: "+line+'\n');
                            }
                            else if(cnt==2)
                            {
                                result.append("NAME: "+line+'\n');
                                result2 += "NAME: "+line+'\n';
                            }
                            else if(cnt==3)
                            {
                                result.append("DISCIPLINE: "+line+'\n');
                            }
                            else if(cnt==4)
                            {
                                result.append("MOBILE: "+line+'\n');
                                result2 += "MOBILE: "+line+'\n';
                                arr[pos++] = line;
                            }
                            else if(cnt==5)
                            {
                                result.append("EMAIL: "+line+'\n');
                            }
                            else if(cnt==6)
                            {
                                result.append("DISTRICT: "+line+'\n');
                                result2 += "DISTRICT: "+line+'\n';
                                district_Location[location_pos++]=line;

                            }
                            else if(cnt==7)
                            {
                                result.append("LAST DONATION: "+line+'\n');
                                result2 += "LAST DONATION: "+line+'\n';
                                cnt = 0;
                                list.add(result.toString());
                                list2.add(result2.toString());
                                res.add(result.toString());
                                result = new StringBuilder();
                                result2 = "";
                            }
//                            else if(cnt==8)
//                            {
//                                result.append("IsDonate: "+line+'\n');
//                                result2 += "IsDonate: "+line+'\n';
//                                Log.e("", line);
//                            }
                        }
                        // Pass data to onPostExecute method
                        return  res.toString();

                    }else{

                        return("Connection Error!");
                    }

            } catch (IOException e) {
                e.printStackTrace();
                return "Connection Error!";
            } finally {
                http.disconnect();
            }

        }
        @Override
        protected void onPostExecute(String res)
        {
            if(res.equalsIgnoreCase("Connection Error!"))
            {
                Toast.makeText(FourthActivity.this,"Connection Error!",Toast.LENGTH_LONG).show();
            }
        }
    }

    //---------------------------------------SECOND ASYNCTASK---------------------------------------


    private class MyBackgroundWorker extends AsyncTask<String,String,String>{
        HttpURLConnection http;
        URL url;
        String blood_Group,location;



        @Override
        protected String doInBackground(String... params) {
//            blood_Group=params[0];
//            location=params[1];
//            list5.add("ku ");
//            list5.add(" cse ");
            try
            {
                url = new URL(locationLink);
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Connection Error!";
            }

            try {
                http = (HttpURLConnection) url.openConnection();
                http.setReadTimeout(15000);
                http.setConnectTimeout(7000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("GROUP", params[0])
                        .appendQueryParameter("LOCATION", params[1]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = http.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                http.connect();

            }
            catch(IOException e)
            {

                //return  e.toString();
                e.printStackTrace();
                return "Connection Error!";
            }

            try {

                int response_code = http.getResponseCode();

                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // return "Successfully Created account";
                    // Read data sent from server
                    InputStream input = http.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    ArrayList<String> res = new ArrayList<>();
                    StringBuilder result = new StringBuilder();
                    String result2 = "";
                    String line;
                    int cnt = 0,pos=0,location_pos=0;
                    while ((line = reader.readLine()) != null) {
                        cnt++;
                        if(cnt==1)
                        {
                            result.append("ID: "+line+'\n');
                        }
                        else if(cnt==2)
                        {
                            result.append("NAME: "+line+'\n');
                            result2 += "NAME: "+line+'\n';
                        }
                        else if(cnt==3)
                        {
                            result.append("DISCIPLINE: "+line+'\n');
                        }
                        else if(cnt==4)
                        {
                            result.append("MOBILE: "+line+'\n');
                            result2 += "MOBILE: "+line+'\n';
                            arr[pos++] = line;
                        }
                        else if(cnt==5)
                        {
                            result.append("EMAIL: "+line+'\n');
                        }
                        else if(cnt==6)
                        {
                            result.append("DISTRICT: "+line+'\n');
                            result2 += "DISTRICT: "+line+'\n';
                            district_Location2[location_pos++]=line;
                        }
                        else if(cnt==7)
                        {
                            result.append("LAST DONATION: "+line+'\n');
                            result2 += "LAST DONATION: "+line+'\n';
                            cnt = 0;
                            list3.add(result.toString());
                            list4.add(result2.toString());
                            res.add(result.toString());
                            result = new StringBuilder();
                            result2 = "";
                        }
                    }
                    // Pass data to onPostExecute method
                    return  res.toString();

                }else{

                    return("Connection Error!");
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Connection Error!";
            } finally {
                http.disconnect();
            }
        }
        @Override
        protected void onPostExecute(String res) {
            if(res.equalsIgnoreCase("Connection Error!"))
            {
                Toast.makeText(FourthActivity.this,"Connection Error!",Toast.LENGTH_LONG).show();
            }
        }

    }

}
