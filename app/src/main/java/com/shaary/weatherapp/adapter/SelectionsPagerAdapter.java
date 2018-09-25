package com.shaary.weatherapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shaary.weatherapp.ui.fragments.DailyForecastFragment;
import com.shaary.weatherapp.ui.fragments.ForecastFragment;
import com.shaary.weatherapp.ui.fragments.HourlyForecastFragment;

public class SelectionsPagerAdapter extends FragmentPagerAdapter {

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
        return 2;
    }
}
