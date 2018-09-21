package com.shaary.weatherapp.Weather;

import com.shaary.weatherapp.R;

public class Forecast {

    private double latitude;
    private double longitude;
    private String timezone;
    private Current currently;
    private Hour hourly;
    private Day daily;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Current getCurrently() {
        return currently;
    }

    public HourData[] getHourly() {
        return hourly.getData();
    }

    public DayData[] getDaily() {
        return daily.getData();
    }

    public static int getIconId(String iconString) {

        int iconId = R.drawable.clear_day;

        switch (iconString) {
            case "clear_day":
                iconId = R.drawable.clear_day;
                break;
            case "clear_night":
                iconId = R.drawable.clear_night;
                break;
            case "rain":
                iconId = R.drawable.rain;
                break;
            case "snow":
                iconId = R.drawable.snow;
                break;
            case "sleet":
                iconId = R.drawable.sleet;
                break;
            case "wind":
                iconId = R.drawable.wind;
                break;
            case "fog":
                iconId = R.drawable.fog;
                break;
            case "cloudy":
                iconId = R.drawable.cloudy;
                break;
            case "partly-cloudy-day":
                iconId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconId = R.drawable.cloudy_night;
                break;
        }
        return iconId;
    }


}
