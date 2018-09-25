package com.shaary.weatherapp.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shaary.weatherapp.AsyncDataCall;
import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.HeadlessFragment;
import com.shaary.weatherapp.Weather.Hour;
import com.shaary.weatherapp.adapter.SelectionsPagerAdapter;
import com.shaary.weatherapp.ui.fragments.DailyForecastFragment;
import com.shaary.weatherapp.ui.fragments.ForecastFragment;
import com.shaary.weatherapp.ui.fragments.HourlyForecastFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements HeadlessFragment.ForecastCallBacks{

    public static final String FORECAST = "forecast";
    private static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";
    public static final String TAG = MainActivity.class.getSimpleName();
    private Forecast forecast;
    private ForecastFragment forecastFragment;
    private HourlyForecastFragment hourlyForecastFragment;
    private DailyForecastFragment dailyForecastFragment;
    private HeadlessFragment headlessFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call headless fragment
        FragmentManager fragmentManager = getFragmentManager();
        headlessFragment = (HeadlessFragment) fragmentManager.findFragmentByTag(TAG_HEADLESS_FRAGMENT);
        if (headlessFragment == null) {
            Log.d(TAG, "onCreate: new frag");

            headlessFragment = new HeadlessFragment();
            fragmentManager.beginTransaction().add(headlessFragment, TAG_HEADLESS_FRAGMENT).commit();
        }
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onProgressUpdate(int percent) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(Forecast forecast) {
        this.forecast = forecast;
        forecastFragment = new ForecastFragment();
        hourlyForecastFragment = new HourlyForecastFragment();
        dailyForecastFragment = new DailyForecastFragment();
        SelectionsPagerAdapter pagerAdapter = new SelectionsPagerAdapter(getSupportFragmentManager(), forecastFragment, hourlyForecastFragment, dailyForecastFragment);
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        Log.d(TAG, "onPostExecute: " + forecast.getCurrently().getTemperature());

        forecastFragment.updateDisplay(forecast);
        hourlyForecastFragment.updateDisplay(forecast);
        dailyForecastFragment.updateDisplay(forecast);

    }

}
