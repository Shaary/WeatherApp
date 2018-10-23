package com.shaary.weatherapp.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter<T, H extends BaseViewHolder<T>> extends RecyclerView.Adapter<H>  {

    private final Class<H> myAdapter;

    public abstract int getLayoutId();

    private T[] data;
    private String timezone;

    public BaseAdapter(Class<H> myAdapter, T[] data, String timezone) {
        this.myAdapter = myAdapter;
        this.data = data;
        this.timezone = timezone;
    }

    @NonNull
    @Override
    public H onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false);

        if (myAdapter.equals(DailyAdapter.ViewHolder.class)) {
            return (H) new DailyAdapter.ViewHolder(view);
        } else if (myAdapter.equals(HourlyAdapter.ViewHolder.class)) {
            return (H) new HourlyAdapter.ViewHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(data[position], position, timezone);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
