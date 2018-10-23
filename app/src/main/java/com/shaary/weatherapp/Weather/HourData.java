package com.shaary.weatherapp.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DecimalFormat;

public class HourData extends WeatherData{

    private double temperature;
    private String timezone;

    protected HourData(Parcel in) {
        super(in);
        temperature = in.readDouble();
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

    public String getTemperature() {
        return new DecimalFormat("#").format(temperature);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);

        dest.writeDouble(temperature);
    }
}
