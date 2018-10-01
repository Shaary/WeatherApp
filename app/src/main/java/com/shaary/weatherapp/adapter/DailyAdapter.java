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

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {

    private DayData[] dayData;
    private String timezone;
    private Context context;

    public DailyAdapter(DayData[] dayData, String timezone, Context context) {
        this.dayData = dayData;
        this.timezone = timezone;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_list_item, parent, false);
        return new DailyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.dayLabel.setText(getDayOfTheWeek(dayData[position].getTime()));
        holder.temperatureLabel.setText(dayData[position].getTemperatureHigh());
        Drawable drawable = ContextCompat.getDrawable(context, dayData[position].getIcon());
        holder.icon.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return dayData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayLabel;
        TextView temperatureLabel;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            dayLabel = itemView.findViewById(R.id.dayNameLabel);
            temperatureLabel = itemView.findViewById(R.id.temperatureLabel);
            icon = itemView.findViewById(R.id.iconImageView);
        }
    }
    public String getDayOfTheWeek(int time) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        Date dateTime = new Date(time * 1000);
        return formatter.format(dateTime);
    }
}
