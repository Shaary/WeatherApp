package com.shaary.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

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

public class AsyncDataCall extends AsyncTask<Void, Void, String> {

    public interface ForecastListener {
        void onForecastRetrieved(String forecast);
    }

    private static final String TAG = AsyncDataCall.class.getSimpleName();
    static String apiKey = "ab9da84839d065473cc10e0e9f3fc4cc";
    static double latitude = 37.8267;
    static double longitude = -122.42330;

    static String apiCall = "https://api.darksky.net/forecast/"
            + apiKey + "/" + latitude + "," + longitude;
    private String jsonData;
    private ForecastListener listener;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    @Override
    protected void onPostExecute(String jsonData) {
        //super.onPostExecute(jsonData);
        Forecast forecast = null;
//            try {
//                forecast = parseForecastData(jsonData);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            Current current = forecast.getCurrent();


    }

//    private static Forecast parseForecastData(String jsonData) throws JSONException {
//        Forecast forecast = new Forecast();
//        forecast.setCurrent(getCurrentDetails(jsonData));
//        forecast.setHourlyForecast(getHourlyForecast(jsonData));
//        return forecast;
//    }
//
//    private static Current getCurrentDetails(String jsonData) throws JSONException{
//        JSONObject forecast = new JSONObject(jsonData);
//        String timezone = forecast.getString("timezone");
//
//        JSONObject currently = forecast.getJSONObject("currently");
//
//        Current current = new Current();
//
//        current.setHumidity(currently.getDouble("humidity"));
//        current.setTime(currently.getLong("time"));
//        current.setIcon(currently.getString("icon"));
//        current.setLocationLabel("Alcatraz Island, CA");
//        current.setPrecipChance(currently.getDouble("precipProbability"));
//        current.setTemperature(currently.getDouble("temperature"));
//        current.setSummary(currently.getString("summary"));
//        current.setTimezone(timezone);
//
//        Log.i(TAG, "getCurrentDetails: " + timezone);
//        Log.i(TAG, "getCurrentDetails: " + current.getFormattedTime());
//
//        return current;
//    }
//
//    private static Hour[] getHourlyForecast(String jsonData) throws JSONException {
//        JSONObject forecast = new JSONObject(jsonData);
//        String timezone = forecast.getString("timezone");
//
//        JSONObject hourly = forecast.getJSONObject("hourly");
//        JSONArray data = hourly.getJSONArray("data");
//
//        Hour[] hours = new Hour[data.length()];
//
//        for (int i = 0; i < data.length(); i++) {
//            JSONObject jsonHour = data.getJSONObject(i);
//
//            Hour hour = new Hour();
//            hour.setIcon(jsonHour.getString("icon"));
//            hour.setTime(jsonHour.getLong("time"));
//            hour.setTimezone(timezone);
//            hour.setSummary(jsonHour.getString("summary"));
//            hour.setTemperature(jsonHour.getLong("temperature"));
//
//            hours[i] = hour;
//        }
//        return hours;
//    }

}
