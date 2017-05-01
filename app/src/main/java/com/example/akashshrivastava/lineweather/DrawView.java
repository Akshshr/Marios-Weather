package com.example.akashshrivastava.lineweather;


import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akashshrivastava on 2017-04-26.
 */

public class DrawView extends View {


    private List <DataEntryPoint> values = new ArrayList<>();


//    float[] xValues = new float[]{0, 1, 21, 3, 3, 5, 19, 1, 21, 4, 22};
//    float[] yValues = new float[]{0, 2, 4, 6, 8, 10, 12, 14, 16, 5, 22};

    Paint paint = new Paint();
    Paint paint2 = new Paint();
    Paint paint3 = new Paint();


    @Override
    public void setBackground(Drawable background) {
        super.setBackground(getContext().getResources().getDrawable(R.drawable.background));
    }


    public DrawView(Context context) {

        super(context);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        paint3.setColor(Color.argb(6, 234, 234, 234));
        paint3.setStrokeWidth(4f);
        paint3.setAntiAlias(true);

        paint3.setStrokeJoin(Paint.Join.ROUND);
        paint3.setStrokeCap(Paint.Cap.ROUND);



        Log.e("draw view", " draw view");


    }

    public DrawView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }


    public DrawView(Context context, AttributeSet attributeSet, int defStyle){
        super(context, attributeSet, defStyle);
    }


    public void setDataEntries(List<DataEntry> entries) {
        for (DataEntry dataEntry : entries) {
            String time = dataEntry.getTime();

            int hour = -1;

             hour = Integer.parseInt(time.substring(11, 13));


            // TODO: find a way to get the hour from JSON time

        //    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DDThh:mm:ssTZD", Locale.ENGLISH);
////
//            try {
//                Date date = format.parse(time);
//                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
//                hour = calendar.get(Calendar.HOUR_OF_DAY);        // gets hour in 12h format
//
//                date.getTime();
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            double temperature = dataEntry.getTemperature();
            if (hour != -1) {
                DataEntryPoint dataEntryPoint = new DataEntryPoint(hour, temperature);
                values.add(dataEntryPoint);
            }
        }

        // Invalidate the view
        invalidate();

    }


    public void drawLine(Canvas canvas, DataEntryPoint entryPoint1, DataEntryPoint entryPoint2) {
        canvas.drawLine(entryPoint1.getHour(), (float)entryPoint1.getTemperature(), entryPoint2.getHour(), (float)entryPoint2.getTemperature(), paint);

        Log.e("line draw", "line draw");


    }





    private float getMaxX() {
        float largest = Integer.MIN_VALUE;
        for (DataEntryPoint dataEntryPoint : values) {
            // get max x
            if (dataEntryPoint.getHour() > largest) {
                largest = dataEntryPoint.getHour();
            }
        }
//        for (int i = 0; i < xValues.length; i++)
//            if (xValues[i] > largest)
//                largest = xValues[i];
        return largest;
    }

    private float getMinX() {
        float smallest = Integer.MAX_VALUE;
        for (DataEntryPoint dataEntryPoint : values) {
            // get max x
            if (dataEntryPoint.getHour() < smallest) {
                smallest = dataEntryPoint.getHour();
            }
        }

        return smallest;
    }


    private float getMaxY() {
        float largest = Integer.MIN_VALUE;
        for (DataEntryPoint dataEntryPoint : values) {
            // get max x
            if (dataEntryPoint.getTemperature() > largest) {
                largest = (float)dataEntryPoint.getTemperature();
            }
        }
//
//        for (int i = 0; i < yValues.length; i++)
//            if (yValues[i] > largest)
//                largest = yValues[i];
        return largest;
    }

    private float getMinY() {
        float smallest = Integer.MAX_VALUE;
        for (DataEntryPoint dataEntryPoint : values) {
            // get max x
            if (dataEntryPoint.getTemperature() < smallest) {
                smallest = (float)dataEntryPoint.getTemperature();
            }
        }
//
//        for (int i = 0; i < yValues.length; i++)
//            if (yValues[i] < smallest)
//                smallest = yValues[i];
        return smallest;
    }


    @Override
    public void onDraw(Canvas canvas) {



        super.onDraw(canvas);
        DisplayMetrics metrics = new DisplayMetrics();

        ((WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);


        Log.e("On draw", "On draw");


        if (values == null || values.size() == 0) {
            return;
        }
//        drawGrid(canvas, 50, 1400, xValues.length, yValues.length);

        // Get the last point of
        DataEntryPoint last = new DataEntryPoint(0, 0);
        if (values.size() > 0) {
            last = values.get(0);
        }

        //last.y = 1320;


//        assert yValues.length >= xValues.length;

        float xmin = getMinX();
        float xmax = getMaxX();

        float ymin = getMinY();
        float ymax = getMaxY();

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        int xstep = (int) ( width/(xmax - xmin)) ;
        int ystep = (int) (height/(ymax - ymin));

        int step = 0;

        //Drawing the grid...
        while (step < width) {
            canvas.drawLine(step, 0, step,height, paint3);
            Log.e("On draw", "line draw called");
            step += xstep;
        }

        for (DataEntryPoint dataEntryPoint : values) {
            int hour = dataEntryPoint.getHour();
            double temperature = dataEntryPoint.getTemperature();
            Log.e("On draw", "data value found");

            hour *= 40;
            temperature *= 40;

            temperature *= -1;
            temperature += 1320;
            hour += 50;

            // TODO: resize based on scale.
            // Change the values of
//            Vec.y *= 40;
//            Vec.x *= 40;
//
//            Vec.y *= -1;
//            Vec.y += 1320;
//            Vec.x += 50;
//
            canvas.drawLine(50, 50, 50, 1320, paint3); //y axis
            canvas.drawLine(50, 1320, 1550, 1320, paint3); //x Axis

            for (int i = 100; i <= 1000; i += 100) {


                //x grid can be the max value / 5, to give you the totadl number of lines
                canvas.drawLine(50 + i, 50, 50 + i, 1320, paint3); //y axis

                //Y grid can be the max value / 5, to give you the totadl number of lines
                canvas.drawLine(50, 1320 - i, 1550, 1320 - i, paint3); //x Axis


            }

            paint2.setColor(Color.GREEN);
            paint2.setAntiAlias(true);

            canvas.drawCircle(hour, (float) temperature, 15, paint2);

            DataEntryPoint convertedPoint = new DataEntryPoint(hour, temperature);
            drawLine(canvas, last, convertedPoint);
            last = convertedPoint;


        }
//
//        //Drawing the points..
//        for (DataEntryPoint Vec : Vectors) {
//
//            // TODO: add a static constant for variables that are being reused
//            Vec.y *= 40;
//            Vec.x *= 40;
//
//            Vec.y *= -1;
//            Vec.y += 1320;
//            Vec.x += 50;
//
//
//            canvas.drawLine(50, 50, 50, 1320, paint3); //y axis
//            canvas.drawLine(50, 1320, 1550, 1320, paint3); //x Axis
//
//
//                for(int i = 100 ; i <= 1000 ; i+=100){
//
//
//                    //x grid can be the max value / 5, to give you the totadl number of lines
//                    canvas.drawLine( 50 + i, 50 , 50 + i, 1320, paint3); //y axis
//
//                    //Y grid can be the max value / 5, to give you the totadl number of lines
//                    canvas.drawLine( 50, 1320 - i, 1550, 1320 - i, paint3); //x Axis
//
//
//                }
//
//
//            paint2.setColor(Color.GREEN);
//            paint2.setAntiAlias(true);
//
//            canvas.drawCircle(Vec.x, Vec.y, 20, paint2);
//
//
//            drawLine(canvas, last, Vec);
//
//            last = Vec;
//
//
//        }
       // invalidate();

    }


}
