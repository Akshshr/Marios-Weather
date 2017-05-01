package com.example.akashshrivastava.lineweather;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements DownloadTaskListener {

    DrawView drawView;

    TextView mminTemp;
    TextView mmaxTemp;
    ImageView weatherType;
    private LinearLayout llXAxis;
    private TextView txtFirst;

// TODO: set the draw view after the view has been inflated


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask(this);
        task.execute();

        llXAxis = (LinearLayout) findViewById(R.id.llYAxis);
        drawView = (DrawView) findViewById(R.id.graphView);
        txtFirst = (TextView) findViewById(R.id.txtFirst);


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


            Log.d("MxT" + maxtemp , "MnT" + mintemp);

        }
        Log.d("TESTING", "Weather data: " + weatherData);

        setYAxisData(weatherData.getMinTemperature(), weatherData.getMaxTemperature());

    }


    private void setYAxisData(double min, double max) {
        double diff = max - min;
        double singleValue = diff / 23;
        double addValue = max;
        for (int i = 0; i < 24; i++) {
            if (i == 0) {
                addValue = max;
            } else {
                addValue = addValue - singleValue;
            }
            TextView textView = new TextView(this);
            TableRow.LayoutParams params2 = new TableRow.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0, 1f);
            textView.setLayoutParams(params2);
            textView.setTextSize(9f);
            textView.setText(String.format("%.1f", addValue));
            textView.setTextColor(Color.WHITE);
            llXAxis.addView(textView);
            Log.e("TESTING", "Difference Value : " + String.format("%.1f", addValue));
        }

    }

}
