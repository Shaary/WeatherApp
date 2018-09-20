package com.shaary.weatherapp.ui.fragments;

public class ForecastFragmentPresenter {

    private ForecastFragmentView view;

    public ForecastFragmentPresenter(ForecastFragmentView view) {
        this.view = view;
    }
    void setHumidityText(double humidity) {
        view.setHumidityText(humidity);
    }
    void setPrecipChanceText(int precipChance) {
        view.setPrecipChanceText(precipChance);
    }
    void setIcon(String icon) {
        view.setIcon(icon);
    }
    void setTemperatureText(double temperature) {
        view.setTemperatureText(temperature);
    }
    void setTime(String time) {
        view.setTime(time);
    }
    void setSummaryText(String summary) {
        view.setSummaryText(summary);
    }

    void loadData() {

    }
}
