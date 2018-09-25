package com.shaary.weatherapp.ui.fragments;

import com.shaary.weatherapp.Weather.Forecast;

public interface ForecastFragmentView {

    void setHumidityText(double humidity);
    void setPrecipChanceText(int precipChance);
    void setIcon(int icon);
    void setTemperatureText(String temperature);
    void setTime(String time);
    void setSummaryText(String summary);
    void updateDisplay(Forecast forecast);
}
