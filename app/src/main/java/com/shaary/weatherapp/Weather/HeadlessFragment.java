package com.shaary.weatherapp.Weather;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.shaary.weatherapp.ForecastDataRepo;
import com.shaary.weatherapp.ui.AlertUserDialog;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HeadlessFragment extends Fragment implements ForecastDataRepo{
    @Override
    public Forecast sendForecast() {
        return null;
    }

//   public interface ForecastListener {
//        void onForecastRetrieved(String forecast);
//    }

    public interface ForecastCallBacks {
        void onPreExecute();
        void onProgressUpdate(int percent);
        void onCancelled();
        void onPostExecute(Forecast forecast);
    }

    private ForecastCallBacks forecastCallBacks;

    //private ForecastListener listener;
    private Forecast forecast;
//    private ForecastCall forecastCall;

    private static final String TAG = HeadlessFragment.class.getSimpleName();
    static String apiKey = "ab9da84839d065473cc10e0e9f3fc4cc";
    static double latitude = 37.8267;
    static double longitude = -122.42330;

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

    //Calls for the forecast
    private class ForecastCall extends AsyncTask<Void, Void, Forecast> {

        @Override
        protected void onPreExecute() {
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


    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        else {

        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertUserDialog alertDialog = new AlertUserDialog();
        alertDialog.show(getFragmentManager(), "error_dialog");

    }

}
