package com.shaary.weatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shaary.weatherapp.Weather.Hour;
import com.shaary.weatherapp.adapter.HourlyAdapter;

import java.util.ArrayList;
import java.util.List;

public class HourlyForecastActivity extends AppCompatActivity {

    private HourlyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        List<Hour> hoursList =
                (ArrayList<Hour>) intent.getSerializableExtra("HourlyList");



        adapter = new HourlyAdapter();


    }

}
