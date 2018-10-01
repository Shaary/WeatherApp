package com.shaary.weatherapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.shaary.weatherapp.ui.fragments.DailyForecastFragment;
import com.shaary.weatherapp.ui.fragments.ForecastFragment;
import com.shaary.weatherapp.ui.fragments.HourlyForecastFragment;

public class SelectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = SelectionsPagerAdapter.class.getSimpleName();
    private final ForecastFragment forecastFragment;
    private final HourlyForecastFragment hourlyForecastFragment;
    private final DailyForecastFragment dailyForecastFragment;

    public SelectionsPagerAdapter(FragmentManager fm, ForecastFragment forecastFragment, HourlyForecastFragment hourlyForecastFragment, DailyForecastFragment dailyForecastFragment) {
        super(fm);
        this.forecastFragment = forecastFragment;
        this.hourlyForecastFragment = hourlyForecastFragment;
        this.dailyForecastFragment = dailyForecastFragment;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position " + position);
        switch (position) {
            case 0:
                return forecastFragment;
            case 1:
                return hourlyForecastFragment;
            case 2:
                return dailyForecastFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Current";
            case 1:
                return "Hourly";
            case 2:
                return "Daily";
        }
        return "Forecast";
    }
}
