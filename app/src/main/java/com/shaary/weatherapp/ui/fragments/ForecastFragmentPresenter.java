package com.shaary.weatherapp.ui.fragments;

import android.app.FragmentManager;

import com.shaary.weatherapp.AsyncDataCall;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.HeadlessFragment;

public class ForecastFragmentPresenter implements AsyncDataCall.ForecastListener{

    private ForecastFragmentView view;

    public ForecastFragmentPresenter(ForecastFragmentView view) {
        this.view = view;

    }
    void loadData() {


    }

    @Override
    public void onForecastRetrieved(String forecast) {
        Current current = new Current();
        view.updateDisplay(current);
    }


}
