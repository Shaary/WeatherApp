package com.shaary.weatherapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Hour;
import com.shaary.weatherapp.Weather.HourData;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class HourlyAdapter extends BaseAdapter<HourData, HourlyAdapter.ViewHolder> {

    private static final String TAG = HourlyAdapter.class.getSimpleName();

    public HourlyAdapter(HourData[] hours, String timezone) {
        super(HourlyAdapter.ViewHolder.class, hours, timezone);
    }

    @Override
    public int getLayoutId() {
        return R.layout.hourly_list_item;
    }

    public static class ViewHolder extends BaseViewHolder<HourData>{
        TextView summary;
        TextView temperature;
        TextView timeLabel;
        ImageView icon;

        @Override
        public void bind(HourData data, int position, String timezone) {
            temperature.setText(data.getTemperature());
            summary.setText(data.getSummary());
            timeLabel.setText(getHour(data.getTime(), timezone));
            icon.setImageResource(data.getIcon());
        }

        @Override
        protected void setViews() {
            summary = itemView.findViewById(R.id.summaryLabel);
            temperature = itemView.findViewById(R.id.temperatureLabel);
            timeLabel = itemView.findViewById(R.id.timeLabel);
            icon = itemView.findViewById(R.id.iconImageView);
        }

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static String getHour(int time, String timezone) {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        return formatter.format(new Date(time*1000));
    }
}
