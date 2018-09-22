package com.shaary.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.HeadlessFragment;
import com.shaary.weatherapp.Weather.Hour;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AsyncDataCall extends AsyncTask<Void, Void, Forecast> implements ForecastDataRepo{


    public interface ForecastListener {
        void onForecastRetrieved(Forecast forecast);
    }

    private static final String TAG = AsyncDataCall.class.getSimpleName();
    private static String apiKey = "ab9da84839d065473cc10e0e9f3fc4cc";
    private static double latitude = 37.8267;
    private static double longitude = -122.42330;
    private static String apiCall = "https://api.darksky.net/forecast/"
            + apiKey + "/" + latitude + "," + longitude;

    private Forecast forecast;
    private ForecastListener listener;

    public AsyncDataCall(ForecastListener listener) {
        this.listener = listener;
    }

    public Forecast getForecast() {
        return forecast;
    }

    @Override
    protected Forecast doInBackground(Void... voids) {
        //Calls to weather api and returns json string to onPostExecute
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiCall)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();
            Log.d(TAG, "doInBackground: response " + jsonData);
            Gson gson = new Gson();
            forecast = gson.fromJson(jsonData, Forecast.class);
            //Log.d(TAG, "doInBackground: result " + forecast.getCurrent().getTemperature());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return forecast;
    }

    @Override
    protected void onPostExecute(Forecast forecast) {
        listener.onForecastRetrieved(forecast);
    }

    @Override
    public Forecast sendForecast() {
        return getForecast();
    }

}
