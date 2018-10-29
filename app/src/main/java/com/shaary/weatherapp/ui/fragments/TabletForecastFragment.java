package com.shaary.weatherapp.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabletForecastFragment extends BaseForecastFragment {

    public static final String FRAGMENT_CURRENT_TAG = "FRAGMENT_CURRENT";
    public static final String FRAGMENT_HOURLY_TAG = "FRAGMENT_HOURLY";
    public static final String FRAGMENT_DAILY_TAG = "FRAGMENT_DAILY";

    private FragmentManager fragmentManager;

    @BindView(R.id.rootLayout)LinearLayout linearLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tablet_forecast, container, false);
        ButterKnife.bind(this, view);

        fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.currentFragmentContainer,
                CurrentForecastFragment.newInstance(forecast), FRAGMENT_CURRENT_TAG);

        fragmentTransaction.replace(R.id.hourFragmentContainer,
                HourlyForecastFragment.newInstance(forecast), FRAGMENT_HOURLY_TAG);

        fragmentTransaction.replace(R.id.dayFragmentContainer,
                DailyForecastFragment.newInstance(forecast), FRAGMENT_DAILY_TAG);

        fragmentTransaction.commit();
        setBackgroundColor();
        return view;
    }

    private void setBackgroundColor() {
        Current current = forecast.getCurrently();
        switch (current.getWeather()) {
            case "cold":
                linearLayout.setBackgroundResource(R.drawable.cold_weather_gradient);
                break;
            case "hot":
                linearLayout.setBackgroundResource(R.drawable.hot_weather_gradient);
                break;
            case "mild":
                linearLayout.setBackgroundResource(R.drawable.mild_weather_gradient);
                break;
        }
    }

}
