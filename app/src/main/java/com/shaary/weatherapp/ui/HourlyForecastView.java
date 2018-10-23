package com.shaary.weatherapp.ui;

import com.shaary.weatherapp.Weather.Forecast;

public interface HourlyForecastView {
    void updateDisplay(Forecast forecast);
    void setIcon(int icon);
    void setTime(String time);

}
