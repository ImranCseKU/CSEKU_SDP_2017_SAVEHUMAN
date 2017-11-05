package com.hussain.savehuman;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity {

    RadioGroup radio_group;
    RadioButton radio_button;
    Button search_button,list_button,update_button,hospital_btn;
    //DatabaseHelper myDb;
    String search = "";
    String link;
    //String link = "http://192.168.0.102/savehuman/signincheck.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        link=""+getString(R.string.localhost_IP)+"signincheck.php";
        hospital_btn= (Button) findViewById(R.id.nearbyHospital_btn);
        viewAllData();
    }
    public void viewAllData() {

        radio_group = (RadioGroup) findViewById(R.id.radio_g);
        //search_button = (Button) findViewById(R.id.search_btn);
        list_button = (Button) findViewById(R.id.listButton_txt);
        update_button= (Button) findViewById(R.id.Update_button);

        final String updateId=getIntent().getStringExtra("ID");


        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_id=radio_group.getCheckedRadioButtonId();
                radio_button= (RadioButton) findViewById(selected_id);
                if( selected_id >=0)
                {
                    //String search="";
                    if(radio_button.getText().toString().equals("A+ BLOOD"))
                    {
                        search="A+";
                    }
                    else if(radio_button.getText().toString().equals("A- BLOOD"))
                    {
                        search="A-";
                    }
                    else if(radio_button.getText().toString().equals("AB+ BLOOD"))
                    {
                        search="AB+";
                    }
                    else if(radio_button.getText().toString().equals("AB- BLOOD"))
                    {
                        search="AB-";
                    }
                    else if(radio_button.getText().toString().equals("B+ BLOOD"))
                    {
                        search="B+";
                    }
                    else if(radio_button.getText().toString().equals("B- BLOOD"))
                    {
                        search="B-";
                    }
                    else if(radio_button.getText().toString().equals("O+ BLOOD"))
                    {
                        search="O+";
                    }
                    else if(radio_button.getText().toString().equals("O- BLOOD"))
                    {
                        search="O-";
                    }


                    Intent intent=new Intent(ThirdActivity.this,FourthActivity.class);
                    intent.putExtra("GROUP",search);
                    startActivity(intent);
                    radio_group.clearCheck();
                }
                else
                {
                    Toast.makeText(ThirdActivity.this,"First Select The Blood Group",Toast.LENGTH_LONG).show();
                }


            }

        });


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThirdActivity.this,SixthActivity.class);
                intent.putExtra("ID",updateId);
                startActivity(intent);
                radio_group.clearCheck();
            }
        });


        hospital_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ThirdActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

    }



}
