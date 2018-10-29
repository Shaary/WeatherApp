package com.shaary.weatherapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.DayData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.Inflater;

public class DailyAdapter extends BaseAdapter<DayData, DailyAdapter.ViewHolder> {

    public DailyAdapter(DayData[] dayData, String timezone) {
        super(DailyAdapter.ViewHolder.class, dayData, timezone);
    }

    @Override
    public int getLayoutId() {
        return R.layout.daily_list_item;
    }

    public static class ViewHolder extends BaseViewHolder<DayData> {
        TextView dayLabel;
        TextView temperatureLabel;
        ImageView icon;

        @Override
        public void bind(DayData data, int position, String timezone) {
            dayLabel.setText(getDayOfTheWeek(data.getTime(), timezone));
            temperatureLabel.setText(data.getTemperatureHigh());
            icon.setImageResource(data.getIcon());
        }

        @Override
        protected void setViews() {
            dayLabel = itemView.findViewById(R.id.dayNameLabel);
            temperatureLabel = itemView.findViewById(R.id.temperatureLabel);
            icon = itemView.findViewById(R.id.iconImageView);
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static String getDayOfTheWeek(int time, String timezone) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        Date dateTime = new Date();
        return formatter.format(dateTime);
    }
}
