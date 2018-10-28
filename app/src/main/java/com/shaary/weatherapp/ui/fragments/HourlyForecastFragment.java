package com.shaary.weatherapp.ui.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.BaseAdapter;
import com.shaary.weatherapp.adapter.HourlyAdapter;
import com.shaary.weatherapp.ui.FragmentWithRecyclerBase;
import com.shaary.weatherapp.ui.HourlyForecastView;

import butterknife.BindView;
import butterknife.ButterKnife;

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
