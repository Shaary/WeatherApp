package com.shaary.weatherapp.adapter;

import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.ui.fragments.DailyForecastFragment;
import com.shaary.weatherapp.ui.fragments.ForecastFragment;
import com.shaary.weatherapp.ui.fragments.HourlyForecastFragment;

import butterknife.BindView;

public class SelectionsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = SelectionsPagerAdapter.class.getSimpleName();
    private final Forecast forecast;

    public SelectionsPagerAdapter(FragmentManager fm, Forecast forecast) {
        super(fm);
        this.forecast = forecast;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position " + position);
        switch (position) {
            case 0:
                return ForecastFragment.newInstance(forecast);
            case 1:
                return HourlyForecastFragment.newInstance(forecast);
            case 2:
                return DailyForecastFragment.newInstance(forecast);
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
