package com.shaary.weatherapp.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Forecast;

/**
 * A simple {@link Fragment} subclass.
 */

//Abstract class to get data from Main Activity and send it to forecast fragments
public abstract class BaseForecastFragment extends Fragment {

    protected FragmentActivity activity;
    protected Forecast forecast;

    public static BaseForecastFragment newInstance(Class<?> fType, Forecast forecast) {

        BaseForecastFragment fragment = null;
        Bundle args = new Bundle();
        args.putParcelable("forecast", forecast);

        if (fType.equals(PhoneForecastFragment.class)) {
            fragment = new PhoneForecastFragment();
        } else if (fType.equals(TabletForecastFragment.class)){
            fragment = new TabletForecastFragment();
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        forecast = getArguments().getParcelable("forecast");
    }
}
