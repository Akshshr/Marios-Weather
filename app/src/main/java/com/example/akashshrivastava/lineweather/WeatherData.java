package com.example.akashshrivastava.lineweather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akashshrivastava on 2017-04-30.
 */

public class WeatherData {

    List<DataEntry> entries = new ArrayList<>();
    double minTemperature;
    double maxTemperature;

    public void setEntries(List<DataEntry> entries) {
        this.entries = entries;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public List<DataEntry> getEntries() {
        return entries;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public WeatherData(List <DataEntry> entries, double minTemperature, double maxTemperature) {
        this.entries = entries;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

}
