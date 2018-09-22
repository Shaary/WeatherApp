package com.shaary.weatherapp;

import com.shaary.weatherapp.Weather.Forecast;

public interface ForecastDataRepo {
    Forecast sendForecast();
}
