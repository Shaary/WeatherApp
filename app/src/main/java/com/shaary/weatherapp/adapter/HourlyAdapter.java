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

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {

    private static final String TAG = HourlyAdapter.class.getSimpleName();
    private HourData[] hours;
    private String timezone;
    private Context context;

    public HourlyAdapter(HourData[] hours, String timezone, Context context) {
        this.hours = hours;
        this.timezone = timezone;
        this.context = context;
    }

    @NonNull
    @Override
    public HourlyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hourly_list_item, parent, false);
        return new HourlyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: is called");
        holder.summary.setText(hours[position].getSummary());
        holder.temperature.setText(hours[position].getTemperature());
        holder.timeLabel.setText(getHour(hours[position].getTime()));
        Drawable drawable = ContextCompat.getDrawable(context, hours[position].getIcon());
        holder.icon.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return hours.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView summary;
        TextView temperature;
        TextView timeLabel;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            summary = itemView.findViewById(R.id.summaryLabel);
            temperature = itemView.findViewById(R.id.temperatureLabel);
            timeLabel = itemView.findViewById(R.id.timeLabel);
            icon = itemView.findViewById(R.id.iconImageView);
        }
    }

    private String getHour(int time) {
        SimpleDateFormat formatter = new SimpleDateFormat("h a");
        formatter.setTimeZone(TimeZone.getTimeZone(timezone));
        return formatter.format(new Date(time*1000));
    }
}
