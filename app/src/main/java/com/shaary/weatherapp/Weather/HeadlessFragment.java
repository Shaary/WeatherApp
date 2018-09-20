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
import com.shaary.weatherapp.ui.fragments.ForecastFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HeadlessFragment extends Fragment implements ForecastDataRepo{

   public interface ForecastListener {
        void onForecastRetrieved(String forecast);
    }

    private ForecastListener listener;
    static String jsonData;

    private static final String TAG = HeadlessFragment.class.getSimpleName();
    static String apiKey = "ab9da84839d065473cc10e0e9f3fc4cc";
    static double latitude = 37.8267;
    static double longitude = -122.42330;

    static String apiCall = "https://api.darksky.net/forecast/"
            + apiKey + "/" + latitude + "," + longitude;

    static Current displayWeather;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (ForecastListener) activity;
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
    }

    private static Forecast parseForecastData(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));
        return forecast;
    }

    private static Current getCurrentDetails(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject currently = forecast.getJSONObject("currently");

        Current current = new Current();

        current.setHumidity(currently.getDouble("humidity"));
        current.setTime(currently.getLong("time"));
        current.setIcon(currently.getString("icon"));
        current.setLocationLabel("Alcatraz Island, CA");
        current.setPrecipChance(currently.getDouble("precipProbability"));
        current.setTemperature(currently.getDouble("temperature"));
        current.setSummary(currently.getString("summary"));
        current.setTimezone(timezone);

        Log.i(TAG, "getCurrentDetails: " + timezone);
        Log.i(TAG, "getCurrentDetails: " + current.getFormattedTime());

        return current;
    }

    private static Hour[] getHourlyForecast(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        String timezone = forecast.getString("timezone");

        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        Hour[] hours = new Hour[data.length()];

        for (int i = 0; i < data.length(); i++) {
            JSONObject jsonHour = data.getJSONObject(i);

            Hour hour = new Hour();
            hour.setIcon(jsonHour.getString("icon"));
            hour.setTime(jsonHour.getLong("time"));
            hour.setTimezone(timezone);
            hour.setSummary(jsonHour.getString("summary"));
            hour.setTemperature(jsonHour.getLong("temperature"));

            hours[i] = hour;
        }
        return hours;
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

    @Override
    public Forecast getForecast() {
        return null;
    }

    //Calls for the forecast
    private class ForecastCall extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            //Calls to weather api and returns json string to onPostExecute
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(apiCall)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                jsonData = response.body().string();
                Log.d(TAG, "doInBackground: response " + jsonData);
//                Gson gson = new Gson();
//                Forecast forecast = gson.fromJson(jsonData, Forecast.class);
                //Log.d(TAG, "doInBackground: result " + forecast.getCurrent().getTemperature());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonData;
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
        protected void onPostExecute(String jsonData) {

            listener.onForecastRetrieved(jsonData);

//            //Parses through json data and sends bundle to ForecastFragment
//            Forecast forecast = null;
//            try {
//                forecast = parseForecastData(jsonData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Current current = forecast.getCurrent();
//
//                displayWeather = new Current(
//                        current.getLocationLabel(),
//                        current.getIcon(),
//                        current.getTime(),
//                        current.getTemperature(),
//                        current.getHumidity(),
//                        current.getPrecipChance(),
//                        current.getSummary(),
//                        current.getTimezone());
//
//                Bundle bundle = new Bundle();
//            bundle.putString("icon", current.getIcon());
//            bundle.putDouble("temp", current.getTemperature());
//
//            ForecastFragment forecastFragment = new ForecastFragment();
//            forecastFragment.setArguments(bundle);
//            Log.d(TAG, "onPostExecute: executed");
        }
    }

}
