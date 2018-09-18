package com.shaary.weatherapp.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.HeadlessFragment;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    ForecastFragment forecastFragment;

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

    }
}
