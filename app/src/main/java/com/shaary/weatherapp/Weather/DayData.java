package com.shaary.weatherapp.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DayData extends WeatherData{

    private double precipProbability;
    private double temperatureHigh;
    private double temperatureLow;

    protected DayData(Parcel in) {
        super(in);
        precipProbability = in.readDouble();
        temperatureHigh = in.readDouble();
        temperatureLow = in.readDouble();
    }

    public static final Creator<DayData> CREATOR = new Creator<DayData>() {
        @Override
        public DayData createFromParcel(Parcel in) {
            return new DayData(in);
        }

        @Override
        public DayData[] newArray(int size) {
            return new DayData[size];
        }
    };

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public String getTemperatureHigh() {
        return new DecimalFormat("#").format(temperatureHigh);
    }

    public void setTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public String getTemperatureLow() {
        return new DecimalFormat("#").format(temperatureLow);
    }

    public void setTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeDouble(precipProbability);
        dest.writeDouble(temperatureHigh);
        dest.writeDouble(temperatureLow);
    }
}
