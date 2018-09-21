package com.shaary.weatherapp.Weather;

public class Day {
    private String summary;
    private DayData[] data;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public DayData[] getData() {
        return data;
    }

}
