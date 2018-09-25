package com.shaary.weatherapp.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DayData implements Parcelable{
    private int time;
    private String summary;
    private String icon;
    private double precipProbability;
    private double temperatureHigh;
    private double temperatureLow;
    private double humidity;

    protected DayData(Parcel in) {
        time = in.readInt();
        summary = in.readString();
        icon = in.readString();
        precipProbability = in.readDouble();
        temperatureHigh = in.readDouble();
        temperatureLow = in.readDouble();
        humidity = in.readDouble();
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getIcon() {
        return Forecast.getIconId(icon);
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(time);
        dest.writeString(summary);
        dest.writeString(icon);
        dest.writeDouble(precipProbability);
        dest.writeDouble(temperatureHigh);
        dest.writeDouble(temperatureLow);
        dest.writeDouble(humidity);
    }
}
