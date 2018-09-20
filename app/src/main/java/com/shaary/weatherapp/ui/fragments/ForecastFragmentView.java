package com.shaary.weatherapp.ui.fragments;

public interface ForecastFragmentView {

    void setHumidityText(double humidity);
    void setPrecipChanceText(int precipChance);
    void setIcon(String icon);
    void setTemperatureText(double temperature);
    void setTime(String time);
    void setSummaryText(String summary);
}
