package com.shaary.weatherapp.Weather;


import android.os.Parcel;
import android.os.Parcelable;

public abstract class WeatherData implements Parcelable{
    private int time;
    private String summary;
    private String icon;
    private double humidity;

    WeatherData(Parcel in) {
        time = in.readInt();
        summary = in.readString();
        icon = in.readString();
        humidity = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(time);
        parcel.writeString(summary);
        parcel.writeString(icon);
        parcel.writeDouble(humidity);

    }

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

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
}
