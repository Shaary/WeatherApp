package com.shaary.weatherapp.Weather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.shaary.weatherapp.ForecastDataRepo;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HeadlessFragment extends Fragment implements ForecastDataRepo{
    @Override
    public Forecast sendForecast() {
        return null;
    }

    public interface ForecastCallBacks {
        void onPreExecute();
        void onProgressUpdate(int percent);
        void onCancelled();
        void onPostExecute(Forecast forecast);
    }

    private static ForecastCallBacks forecastCallBacks;

    //private ForecastListener listener;
    private static Forecast forecast;
//    private ForecastCall forecastCall;

    private static final String TAG = HeadlessFragment.class.getSimpleName();
    static String apiKey = "ab9da84839d065473cc10e0e9f3fc4cc";
    static double latitude = 51.083738;
    static double longitude = -114.234316;

    static String apiCall = "https://api.darksky.net/forecast/"
            + apiKey + "/" + latitude + "," + longitude;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        forecastCallBacks = (ForecastCallBacks) activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        ForecastCall forecastCall = new ForecastCall();
        forecastCall.execute();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        forecastCallBacks = null;
    }

    public Forecast getForecast() {
        return forecast;
    }

    //Calls for the forecast
    private static class ForecastCall extends AsyncTask<Void, Void, Forecast> {

        @Override
        protected void onPreExecute() {
            forecastCallBacks.onPreExecute();
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
                //Log.d(TAG, "doInBackground: response " + jsonData);
                Gson gson = new Gson();
                forecast = gson.fromJson(jsonData, Forecast.class);
                //Log.d(TAG, "doInBackground: result " + forecast.getCurrent().getTemperature());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return forecast;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Forecast forecast) {
            forecastCallBacks.onPostExecute(forecast);

//       Log.d(TAG, "onPostExecute: executed");
        }
    }
}
