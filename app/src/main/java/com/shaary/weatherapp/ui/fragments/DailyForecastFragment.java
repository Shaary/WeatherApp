package com.shaary.weatherapp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.DailyAdapter;
import com.shaary.weatherapp.ui.DailyForecastView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DailyForecastFragment extends Fragment implements DailyForecastView{

    private static final String TAG = DailyForecastFragment.class.getSimpleName();
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    DailyAdapter dailyAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast, container, false);
        ButterKnife.bind(this, view);
        Log.d(TAG, "onCreateView: was called");
        return view;
    }

    @Override
    public void updateDisplay(Forecast forecast) {
        dailyAdapter = new DailyAdapter(forecast.getDaily(), forecast.getTimezone(), getContext());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dailyAdapter);

    }

}
