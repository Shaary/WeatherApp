package com.shaary.weatherapp.ui;

import com.shaary.weatherapp.Weather.Forecast;

public interface HourlyForecastView {
    void updateDisplay(Forecast forecast);
    void setHumidityText(double humidity);
    void setIcon(int icon);
    void setTemperatureText(double temperature);
    void setTime(String time);
    void setSummaryText(String summary);

}
