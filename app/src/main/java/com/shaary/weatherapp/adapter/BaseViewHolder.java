package com.shaary.weatherapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Locale;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public abstract void bind(T data, int position, String timezone);
    protected abstract void setViews();

    public BaseViewHolder(View itemView) {
        super(itemView);

        setViews();
    }

    String valueFormat(String format, Object value) {

        return String.format(Locale.ENGLISH, format, value);
    }
}
