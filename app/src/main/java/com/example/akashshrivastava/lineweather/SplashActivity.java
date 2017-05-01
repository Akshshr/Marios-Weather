package com.example.akashshrivastava.lineweather;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;

public class SplashActivity extends AppCompatActivity {

    public static int SPLAST_TIMEOUT = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Download the thing here

//                DownloadTask task = new DownloadTask();
//                task.execute();




            }
        },SPLAST_TIMEOUT);

        Intent signUpIntent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(signUpIntent);
        finish();



    }
}
