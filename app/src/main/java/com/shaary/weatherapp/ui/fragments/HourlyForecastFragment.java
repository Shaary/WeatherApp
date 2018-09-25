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
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.HourlyAdapter;
import com.shaary.weatherapp.ui.HourlyForecastView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HourlyForecastFragment extends Fragment implements HourlyForecastView{

    private static final String TAG = HourlyForecastFragment.class.getSimpleName();
    @BindView(R.id.hourly_recycler_view) RecyclerView recyclerView;

    private HourlyAdapter hourlyAdapter;
    private RecyclerView.LayoutManager layoutManager;



    public HourlyForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hourly_forecast, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void updateDisplay(Forecast forecast) {
        hourlyAdapter = new HourlyAdapter(forecast.getHourly(), forecast.getTimezone());
        layoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hourlyAdapter);

    }

    @Override
    public void setHumidityText(double humidity) {

    }

    @Override
    public void setIcon(int icon) {

    }

    @Override
    public void setTemperatureText(double temperature) {

    }

    @Override
    public void setTime(String time) {

    }

    @Override
    public void setSummaryText(String summary) {

    }
}
