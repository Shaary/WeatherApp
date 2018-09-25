package com.shaary.weatherapp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    @BindView(R.id.daily_recycler_view) RecyclerView recyclerView;
    DailyAdapter dailyAdapter;
    RecyclerView.LayoutManager layoutManager;


    public DailyForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_forecast, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    //TODO: find what causes the error
    @Override
    public void updateDisplay(Forecast forecast) {
        dailyAdapter = new DailyAdapter(forecast.getDaily(), forecast.getTimezone());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dailyAdapter);

    }

}
