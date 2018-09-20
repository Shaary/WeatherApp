package com.shaary.weatherapp.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.HeadlessFragment;
import com.shaary.weatherapp.Weather.Hour;
import com.shaary.weatherapp.ui.fragments.ForecastFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements HeadlessFragment.ForecastListener {

    private static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";
    public static final String TAG = MainActivity.class.getSimpleName();
    ForecastFragment forecastFragment;
    HeadlessFragment headlessFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates a day forecast fragment and sets it in the placeholder
        forecastFragment = new ForecastFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.place_holder, forecastFragment);
        fragmentTransaction.commit();

        //Call headless fragment
        headlessFragment = (HeadlessFragment) fragmentManager.findFragmentByTag(TAG_HEADLESS_FRAGMENT);
        if (headlessFragment == null) {
            Log.d(TAG, "onCreate: new frag");

            headlessFragment = new HeadlessFragment();
            fragmentManager.beginTransaction().add(headlessFragment, TAG_HEADLESS_FRAGMENT).commit();
        }

    }

    @Override
    public void onForecastRetrieved(String forecast) {
        Forecast newForecast = null;
        try {
            newForecast = parseForecastData(forecast);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Current current = newForecast.getCurrent();

                Current displayWeather = new Current(
                        current.getLocationLabel(),
                        current.getIcon(),
                        current.getTime(),
                        current.getTemperature(),
                        current.getHumidity(),
                        current.getPrecipChance(),
                        current.getSummary(),
                        current.getTimezone());

                double temp = current.getTemperature();
                Bundle bundle = new Bundle();
            bundle.putString("icon", current.getIcon());
            bundle.putString("temp", String.valueOf(temp));

            forecastFragment.setArguments(bundle);
            Log.d(TAG, "onPostExecute: executed");

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
}
