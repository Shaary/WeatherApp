package com.shaary.weatherapp.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.BaseAdapter;
import com.shaary.weatherapp.adapter.HourlyAdapter;
import com.shaary.weatherapp.ui.FragmentWithRecyclerBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourlyForecastFragment extends FragmentWithRecyclerBase{

    private static final String TAG = HourlyForecastFragment.class.getSimpleName();

    public static HourlyForecastFragment newInstance(Forecast forecast) {

        Bundle args = new Bundle();
        args.putParcelable("forecast", forecast);
        HourlyForecastFragment fragment = new HourlyForecastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BaseAdapter getAdapter() {
        return new HourlyAdapter(forecast.getHourly(), forecast.getTimezone());
    }
}
