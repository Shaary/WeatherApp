package com.shaary.weatherapp.ui;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.Hour;
import com.shaary.weatherapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    Forecast forecast;

    String apiKey = "ab9da84839d065473cc10e0e9f3fc4cc";
    double latitude = 37.8267;
    double longitude = -122.42330;

    String apiCall = "https://api.darksky.net/forecast/"
            + apiKey + "/" + latitude + "," + longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getForecast(latitude, longitude);

        ForecastFragment forecastFragment = new ForecastFragment();
        forecastFragment.setRetainInstance(true);

    }

    private void getForecast(double latitude, double longitude) {
        final ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this,
                R.layout.activity_main);

        final ImageView icon = findViewById(R.id.iconImageView);
        TextView darkSky = findViewById(R.id.darkSkyAttribution);
        darkSky.setMovementMethod(LinkMovementMethod.getInstance());

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiCall)
                .build();

        Call call = client.newCall(request);

        if (isNetworkAvailable()) {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String jsonData = response.body().string();
                    try {
                        Log.v(TAG, jsonData);
                        if (response.isSuccessful()) {
                            forecast = parseForecastData(jsonData);

                            Current current = forecast.getCurrent();

                            final Current displayWeather = new Current(
                                    current.getLocationLabel(),
                                    current.getIcon(),
                                    current.getTime(),
                                    current.getTemperature(),
                                    current.getHumidity(),
                                    current.getPrecipChance(),
                                    current.getSummary(),
                                    current.getTimezone()
                            );
                            binding.setWeather(displayWeather);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Drawable drawable = getResources().getDrawable(displayWeather.getIconId());
                                    icon.setImageDrawable(drawable);
                                }
                            });



                        } else {
                            alertUserAboutError();
                        }
                    } catch (JSONException e) {
                        Log.d(TAG, "onResponse: JSON exceptions is caught " + e);
                    }
                }
            });
        }
    }

    private Forecast parseForecastData(String jsonData) throws JSONException {
        Forecast forecast = new Forecast();
        forecast.setCurrent(getCurrentDetails(jsonData));
        forecast.setHourlyForecast(getHourlyForecast(jsonData));
        return forecast;
    }

    private Hour[] getHourlyForecast(String jsonData) throws JSONException {
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

    private Current getCurrentDetails(String jsonData) throws JSONException{
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

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        else {
            Toast.makeText(this, R.string.network_error_message, Toast.LENGTH_SHORT).show();
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertUserDialog alertDialog = new AlertUserDialog();
        alertDialog.show(getFragmentManager(), "error_dialog");

    }

    public void refreshOnClick(View view) {
        getForecast(latitude, longitude);
    }

    public void hourlyOnClick(View view) {
        List<Hour> hours = Arrays.asList(forecast.getHourlyForecast());

        Intent intent  = new Intent(this, HourlyForecastActivity.class);
        intent.putExtra("HourlyList", (Serializable) hours);
        startActivity(intent);

    }



}
