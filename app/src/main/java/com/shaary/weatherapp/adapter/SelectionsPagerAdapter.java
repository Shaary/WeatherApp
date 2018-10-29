package com.shaary.weatherapp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.ui.fragments.DailyForecastFragment;
import com.shaary.weatherapp.ui.fragments.CurrentForecastFragment;
import com.shaary.weatherapp.ui.fragments.HourlyForecastFragment;

public class SelectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = SelectionsPagerAdapter.class.getSimpleName();
    private Fragment[] fragments;

    public SelectionsPagerAdapter(FragmentManager fm, Fragment... fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position " + position);
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
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
