package com.shaary.weatherapp.Weather;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Hour {

    private String summary;
    private HourData[] data;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public HourData[] getData() {
        return data;
    }

}
