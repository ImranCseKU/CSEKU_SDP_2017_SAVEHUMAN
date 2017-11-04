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

public class FifthActivity extends AppCompatActivity {

    Button call_button, sms_button,email_button,Location_btn;
    TextView finalTextView;
    EditText edit_Sms;
    int number;
    String district_Location="";
    String person_unification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        actionOnPerform();
    }

    public void actionOnPerform() {
        finalTextView= (TextView) findViewById(R.id.identification_txt);
        call_button = (Button) findViewById(R.id.button_call);
        sms_button = (Button) findViewById(R.id.button_sms);
        Location_btn= (Button) findViewById(R.id.userLocation_btn);
        //email_button= (Button) findViewById(R.id.button_email);


        number = Integer.parseInt(getIntent().getStringExtra("PhoneNumber"));
        district_Location=getIntent().getStringExtra("DISTRICT_LOCATION");
        finalTextView.setText(getIntent().getStringExtra("PERSON_DETAILS"));

        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0" + number));
                if (ActivityCompat.checkSelfPermission(FifthActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);
            }
        });

        Location_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(FifthActivity.this,UserLocationActivity.class);
                intent.putExtra("DISTRICT_LOCATION",district_Location);
                startActivity(intent);
            }
        });


        /*email_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));     //mailto is a protocall such as http//

                String[] to={"konokahmedt@gmail.com","imran150240@gmail.com"};

                intent.putExtra(Intent.EXTRA_EMAIL,to);
                intent.putExtra(Intent.EXTRA_SUBJECT,"hi this is from my app");
                intent.putExtra(Intent.EXTRA_TEXT,"hi friend, how are you?");

                intent.setType("message/rfc822");     //TYPE WITH  /rfc822 SHOULD BE SELECTED

                Intent chooser=Intent.createChooser(intent,"send email");
                startActivity(chooser);

            }
        });
        */

        sms_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(FifthActivity.this,SeventhActivity.class);
                i.putExtra("PhoneNumber",String.valueOf(number));
                startActivity(i);

            }
        });



    }
}
