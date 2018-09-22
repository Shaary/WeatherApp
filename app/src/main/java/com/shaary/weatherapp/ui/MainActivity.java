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


public class MainActivity extends AppCompatActivity implements HeadlessFragment.ForecastCallBacks{

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

//        //Call headless fragment
//        headlessFragment = (HeadlessFragment) fragmentManager.findFragmentByTag(TAG_HEADLESS_FRAGMENT);
//        if (headlessFragment == null) {
//            Log.d(TAG, "onCreate: new frag");
//
//            headlessFragment = new HeadlessFragment();
//            fragmentManager.beginTransaction().add(headlessFragment, TAG_HEADLESS_FRAGMENT).commit();
//        }
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
        Log.d(TAG, "onPostExecute: from main activity " + forecast.getCurrently().getTemperature());
        forecastFragment.setTemperatureText(forecast.getCurrently().getTemperature());

    }
}
