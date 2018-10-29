package com.shaary.weatherapp.Weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.shaary.weatherapp.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Current implements Parcelable{

    private int time;
    private String summary;
    private String icon;
    private double precipProbability;
    private double temperature;
    private double humidity;

    protected Current(Parcel in) {
        time = in.readInt();
        summary = in.readString();
        icon = in.readString();
        precipProbability = in.readInt();
        temperature = in.readDouble();
        humidity = in.readDouble();
    }

    public static final Creator<Current> CREATOR = new Creator<Current>() {
        @Override
        public Current createFromParcel(Parcel in) {
            return new Current(in);
        }

        @Override
        public Current[] newArray(int size) {
            return new Current[size];
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

    public void setPrecipProbability(int precipProbability) {
        this.precipProbability = precipProbability;
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

    public String getFormattedTime(String timezone) {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm a", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        Date dateTime = new Date();
        String timeString = formatter.format(dateTime);

        return timeString;
    }

    public String getWeather() {
        if (temperature < 50) {
            return "cold";
        }
        if (temperature > 68) {
            return "hot";
        }
        else {
            return "mild";
        }
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
        dest.writeDouble(temperature);
        dest.writeDouble(humidity);
    }
}
