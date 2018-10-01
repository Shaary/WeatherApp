package com.shaary.weatherapp.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class HourData implements Parcelable{

    private int time;
    private String summary;
    private String icon;
    private double temperature;
    private double humidity;
    private String timezone;

    protected HourData(Parcel in) {
        time = in.readInt();
        summary = in.readString();
        icon = in.readString();
        temperature = in.readDouble();
        humidity = in.readDouble();
    }

    public static final Creator<HourData> CREATOR = new Creator<HourData>() {
        @Override
        public HourData createFromParcel(Parcel in) {
            return new HourData(in);
        }

        @Override
        public HourData[] newArray(int size) {
            return new HourData[size];
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

    public String getTemperature() {
        return new DecimalFormat("#").format(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
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
        dest.writeDouble(temperature);
        dest.writeDouble(humidity);
    }
}
