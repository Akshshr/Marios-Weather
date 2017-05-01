package com.example.akashshrivastava.lineweather;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;

import org.w3c.dom.Text;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements DownloadTaskListener {

    DrawView drawView;
    WeatherData weatherData;

    TextView mminTemp;
    TextView mmaxTemp;
    ImageView weatherType;
// TODO: set the draw view after the view has been inflated


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask(this);
        task.execute();

        // TODO: get from xml
        // Get ID
        // graphView
//        drawView = new DrawView(this);
//        drawView = (DrawView) findViewById(R.id.graphView);

        drawView = (DrawView) findViewById(R.id.graphView);
//
//        drawView = new DrawView(this);
//        drawView.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background));
//        //THis works, when un commentedd
//        setContentView(drawView);








//        LayoutInflater inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View myView = inflater.inflate(R.id.graph_layout);

//
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.mainActivityLinearLayout);
//        layout.addView(drawView);


//        setContentView(new DrawView(this));

//
//        LayoutInflater inflater;
//        inflater = LayoutInflater.from(getApplicationContext());
//        final ViewGroup nullParent = null;
//
//        View view = inflater.inflate(R.layout.graph_layout, nullParent);
//        // then do whatever you want with the 'view' object
//        setContentView(view);




//        LayoutInflater inflater =(LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View myView = inflater.inflate(DrawView);
//
//        View view = inflater.inflate(R.layout.graph_layout,null);
//
//    LinearLayout ll = (LinearLayout)getLayoutInflater().inflate(R.layout.whatever_name_you_gave_it, theParentViewGroup, true);


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onTaskCompleted(WeatherData weatherData) {
        if (drawView != null) {
            // TODO: set data entries
            drawView.setDataEntries(weatherData.getEntries());

            double maxtemp = weatherData.getMaxTemperature();
            double mintemp = weatherData.getMinTemperature();
            
            weatherType = (ImageView) findViewById(R.id.weatherType);
            mmaxTemp = (TextView) findViewById(R.id.MaxTemp);
            mminTemp = (TextView) findViewById(R.id.MinTemp);

            mmaxTemp.setText("Todays maximum temperature :" + maxtemp);
            mminTemp.setText("Today minimum temperature : " + mintemp);



            Log.d("Mx" + maxtemp , "mn" + mintemp);

        }
        Log.d("TESTING", "Weather data: " + weatherData);


    }
}
