package com.hussain.savehuman;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static android.content.ContentValues.TAG;


/**
 * Created by imran on 08-Oct-17.
 */

public class pushNotification extends FirebaseInstanceIdService {
    public String refreshedToken;
    public static String refreshedToken2 ;
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
//        Toast.makeText(this, "Inside ", Toast.LENGTH_SHORT).show();
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // TODO: Implement this method to send any registration to your app's servers.
       registerToken(refreshedToken);
        refreshedToken2 = refreshedToken;
    }
    private void registerToken(String token)
    {
        OkHttpClient client=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("fcm_token",token)
                .build();
        Request request=new Request.Builder()
                .url("http://192.168.43.34/savehuman/register.php")
                .post(body)
                .build();
        try {
            client.newCall(request).execute();
            Log.d(TAG, "Gone!");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Errrororororo!");
        }
    }
}
