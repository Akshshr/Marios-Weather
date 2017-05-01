package com.example.akashshrivastava.lineweather;

/**
 * Created by akashshrivastava on 2017-04-29.
 */

public class DataEntry {

    String time;
    double temperature;
    String type;

    public DataEntry(String time, double temperature, String type){
         this.time = time;
        this.temperature = temperature;
        this.type = type;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getType() {
        return type;
    }
}
