package com.shaary.weatherapp.Weather;

import android.os.Parcel;
import android.os.Parcelable;

public class Day implements Parcelable{
    private String summary;
    private DayData[] data;

    protected Day(Parcel in) {
        summary = in.readString();
        data = in.createTypedArray(DayData.CREATOR);
    }

    public static final Creator<Day> CREATOR = new Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public DayData[] getData() {
        return data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(summary);
        dest.writeTypedArray(data, flags);
    }
}
