package com.shaary.weatherapp.ui.fragments;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.Forecast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastFragment extends Fragment implements ForecastFragmentView{

    @BindView(R.id.temperatureView) TextView temperature;
    @BindView(R.id.iconImageView) ImageView icon;
    @BindView(R.id.summaryValue) TextView summary;
    @BindView(R.id.humidityValue) TextView humidity;
    @BindView(R.id.precipValue) TextView precipChance;
    @BindView(R.id.timeValue) TextView time;

    public static final String TAG = ForecastFragment.class.getSimpleName();

    ForecastFragmentPresenter presenter;

    public ForecastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new ForecastFragmentPresenter(this);
        //Loads data and sets updateDisplay
        presenter.loadData();
    }

    //TODO: complete the method
    @Override
    public void updateDisplay(Forecast forecast) {
        Current current = forecast.getCurrently();
        setTemperatureText(current.getTemperature());
        setHumidityText(current.getHumidity());
        setPrecipChanceText(current.getPrecipProbability());
        setIcon(current.getIcon());
        setSummaryText(current.getSummary());
        setTime(current.getFormattedTime(forecast.getTimezone()));
    }

    @Override
    public void setHumidityText(double humidity) {
        String value = getFormattedValue("%.2f", humidity);
        this.humidity.setText(value);
    }

    @Override
    public void setPrecipChanceText(int precipChance) {
        String value = getFormattedValue("%d %%", precipChance);
        this.precipChance.setText(value);
    }

    @Override
    public void setIcon(int icon) {
        Drawable drawable = ContextCompat.getDrawable(getActivity(), icon);
        this.icon.setImageDrawable(drawable);
    }

    @Override
    public void setTemperatureText(double temperature) {
        String value = getFormattedValue("%.1f", temperature);
        this.temperature.setText(value);
    }

    @Override
    public void setTime(String time) {
        String value = getFormattedValue("At %s it will be", time);
        this.time.setText(value);

    }

    @Override
    public void setSummaryText(String summary) {
        this.summary.setText(summary);
    }

    private String getFormattedValue(String format, Object... args) {
        return String.format(format, args);
    }

}
