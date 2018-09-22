package com.shaary.weatherapp.ui.fragments;

import android.app.FragmentManager;
import android.util.Log;

import com.shaary.weatherapp.AsyncDataCall;
import com.shaary.weatherapp.ForecastDataRepo;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.HeadlessFragment;

public class ForecastFragmentPresenter implements AsyncDataCall.ForecastListener{

    private static final String TAG = ForecastFragmentPresenter.class.getSimpleName();
    private ForecastFragmentView view;
    private AsyncDataCall dataCall = new AsyncDataCall(this);

    public ForecastFragmentPresenter(ForecastFragmentView view) {
        this.view = view;
    }

    //Might use this method for refresh button
    void loadData() {
        //Forecast forecast = dataRepo.sendForecast();
        dataCall.execute();

    }

    @Override
    public void onForecastRetrieved(Forecast forecast) {
        Current current = forecast.getCurrently();
        Log.d(TAG, "onForecastRetrieved: is called");
        view.updateDisplay(forecast);
    }


}
