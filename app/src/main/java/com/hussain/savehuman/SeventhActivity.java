package com.hussain.savehuman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SeventhActivity extends AppCompatActivity {
    Button smsSend_button;
    EditText edit_smsSend;
    TextView massage_textView;
    int number;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);
        actionOnPerform();
    }

    public void actionOnPerform() {
        smsSend_button= (Button) findViewById(R.id.button_smsSend);
        edit_smsSend= (EditText) findViewById(R.id.editText_smsSend);
        massage_textView= (TextView) findViewById(R.id.textView_seven);
        number = Integer.parseInt(getIntent().getStringExtra("PhoneNumber"));

       smsSend_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String message=edit_smsSend.getText().toString();
                try
                {

                    if(message.length()>0)
                    {
                        SmsManager sms_manager=SmsManager.getDefault();
                        sms_manager.sendTextMessage(String.valueOf("0"+number),null,message,null,null);
                        Toast.makeText(SeventhActivity.this,"Sms Sent",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getBaseContext(),"Please Write Some Text.",Toast.LENGTH_LONG).show();
                    }


                }catch(Exception e)
                {
                    Toast.makeText(SeventhActivity.this,"Sms Fail.please try again",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });



    }
}
