package com.example.akashshrivastava.lineweather;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by akashshrivastava on 2017-04-27.
 */

public class DownloadTask extends AsyncTask<String, Void, String> {

    public Request request;

    private OkHttpClient okHttpClient;

    private String url = "http://app.tibberdev.com/v2/gql";

    private DownloadTaskListener listener;

    public DownloadTask(DownloadTaskListener listener) {
        this.listener = listener;
    }


    @Override
    protected String doInBackground(String... urls) {


        try {
            okHttpClient = new OkHttpClient();
            OkHttpClient okHttpClient = new OkHttpClient();
            String endpoint = "http://app.tibberdev.com/v2/login.credentials";
            String username = "mario@tibber.com";
            String password = "langbein";
            String credentials = username + ":" + password;

            String json = String.format("{\"email\":\"mario@tibber.com\",\"password\":\"langbein\"}");

            final MediaType mt = MediaType.parse("application/json; charset=utf-8");

            Request request = new Request.Builder()
                    .url(endpoint).addHeader("Content-type", "application/json")
                    .addHeader("Accept-Language", "en-US")
                    .post(RequestBody.create(mt, json))
                    .build();

            Response response = okHttpClient.newCall(request).execute();

            String result = response.body().string();

            Headers headers = response.headers();
            for (String name : headers.names()) {
                System.out.println(name + " " + headers.get(name));
            }


            System.out.println(response.code());
            JSONObject obj = new JSONObject(result);
            String token = obj.getString("token");
            System.out.println(token);

            String dataEndpoint = "http://app.tibberdev.com/v2/gql";

            String dataQuery = "{\n"
                    + "\"query\": \"{me{home(id:\\\"ee7ce98a-a97b-4edc-9f2e-0a9c67a96f27\\\"){weather{minTemperature,maxTemperature,entries{time,temperature,type}}}}}\"\n"
                    + "}";

            Request dataRequest = new Request.Builder()
                    .url(dataEndpoint).addHeader("Content-type", "application/json")
                    .addHeader("Authorization", token)
                    .addHeader("Accept-Language", "en-US")
                    .post(RequestBody.create(mt, dataQuery))
                    .build();

            Response dataResponse = okHttpClient.newCall(dataRequest).execute();


            //Output stream for the json that is fetched.........
//            System.out.println(dataResponse.body().string() + "TESTO");

            result = dataResponse.body().string();
//            String root = dataResponse.body().string();
//            Log.e("Responce code " + response.code(), "Root:" + root);
//            JSONObject jsonObject = new JSONObject(root);
//
//            JSONArray entriesArray = jsonObject.getJSONArray("entries");


            return result;

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {

            JSONObject jsonObject = new JSONObject(result);

            jsonObject =  jsonObject.getJSONObject("data").getJSONObject("me").getJSONObject("home").getJSONObject("weather");

            double minTemperature = jsonObject.getDouble("minTemperature");

            double maxTemperature = jsonObject.getDouble("maxTemperature");

            JSONArray entriesArray = jsonObject.getJSONArray("entries");

            List<DataEntry> entries = new ArrayList<>();

           // String entries = jsonObject.getString("entries");

            for (int i = 0; i < entriesArray.length(); i++) {

                JSONObject entriesArrayJSONObject = entriesArray.getJSONObject(i);

                String type = entriesArrayJSONObject.getString("type");
                String time = entriesArrayJSONObject.getString("time");
                double temperature = entriesArrayJSONObject.getDouble("temperature");
                DataEntry dataEntry = new DataEntry(time, temperature, type);
                entries.add(dataEntry);



                System.out.println("Temperature" + dataEntry.getTime() + "Types" + dataEntry.getType() + "Time" + dataEntry.getTemperature() );
                Log.v("Temperature" + dataEntry.getTime() + "Types" + dataEntry.getType() + "Time" + dataEntry.getTemperature(), "hi");


            }

            WeatherData weatherData = new WeatherData(entries, minTemperature, maxTemperature);


            // TODO: inform the listener about the results
            listener.onTaskCompleted(weatherData);

            Log.d("TESTING", "onTaskCompleted: " + weatherData);
            //Log.i("Entries", entries);

        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
