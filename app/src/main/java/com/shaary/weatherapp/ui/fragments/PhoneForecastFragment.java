package com.shaary.weatherapp.ui.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.SelectionsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneForecastFragment extends BaseForecastFragment {

    @BindView(R.id.rootLayout) LinearLayout linearLayout;
    @BindView(R.id.viewPager) ViewPager pager;
    @BindView(R.id.tabLayout) TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_forecast, container, false);
        ButterKnife.bind(this, view);

        setPagerAdapter(CurrentForecastFragment.newInstance(forecast),
                HourlyForecastFragment.newInstance(forecast),
                DailyForecastFragment.newInstance(forecast));
        setBackgroundColor();

        return view;
    }

    public void setPagerAdapter(Fragment... fragments) {
        SelectionsPagerAdapter pagerAdapter =
                new SelectionsPagerAdapter(getChildFragmentManager(), fragments);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);

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
