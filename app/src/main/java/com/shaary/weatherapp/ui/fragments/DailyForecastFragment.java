package com.shaary.weatherapp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.BaseAdapter;
import com.shaary.weatherapp.adapter.DailyAdapter;
import com.shaary.weatherapp.ui.FragmentWithRecyclerBase;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyForecastFragment extends FragmentWithRecyclerBase {

    private static final String TAG = DailyForecastFragment.class.getSimpleName();

    public static DailyForecastFragment newInstance(Forecast forecast) {
        Bundle args = new Bundle();
        args.putParcelable("forecast", forecast);
        DailyForecastFragment fragment = new DailyForecastFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public BaseAdapter getAdapter() {
        Log.d(TAG, "getAdapter: timezone " + forecast.getTimezone());
        return new DailyAdapter(forecast.getDaily(), forecast.getTimezone());
    }
}
