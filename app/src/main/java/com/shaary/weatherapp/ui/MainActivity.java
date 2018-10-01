package com.shaary.weatherapp.ui;

import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.HeadlessFragment;
import com.shaary.weatherapp.adapter.SelectionsPagerAdapter;
import com.shaary.weatherapp.ui.fragments.DailyForecastFragment;
import com.shaary.weatherapp.ui.fragments.ForecastFragment;
import com.shaary.weatherapp.ui.fragments.HourlyForecastFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements HeadlessFragment.ForecastCallBacks, ForecastFragment.OnImageClickListener {

    public static final String FORECAST = "forecast";
    private static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";
    public static final String TAG = MainActivity.class.getSimpleName();
    private ForecastFragment forecastFragment;
    private Forecast forecast;
    private HourlyForecastFragment hourlyForecastFragment;
    private DailyForecastFragment dailyForecastFragment;

    private FragmentManager fragmentManager;
    private HeadlessFragment headlessFragment;

    @BindView(R.id.tabs)TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        forecastFragment = new ForecastFragment();
        hourlyForecastFragment = new HourlyForecastFragment();
        dailyForecastFragment = new DailyForecastFragment();

        //Call headless fragment
        fragmentManager = getFragmentManager();
        headlessFragment = (HeadlessFragment) fragmentManager.findFragmentByTag(TAG_HEADLESS_FRAGMENT);
        if (headlessFragment == null) {
            refreshWeather();
        }
    }

    @Override
    public void onPreExecute() {
        SelectionsPagerAdapter pagerAdapter = new SelectionsPagerAdapter(getSupportFragmentManager(), forecastFragment, hourlyForecastFragment, dailyForecastFragment);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onProgressUpdate(int percent) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(Forecast forecast) {
        this.forecast = forecast;

        forecastFragment.updateDisplay(forecast);
        dailyForecastFragment.updateDisplay(forecast);
        hourlyForecastFragment.updateDisplay(forecast);
    }

    @Override
    public void refreshWeather() {
        if (isNetworkAvailable()) {
            headlessFragment = new HeadlessFragment();
            fragmentManager.beginTransaction().add(headlessFragment, TAG_HEADLESS_FRAGMENT).commit();
        } else {
            alertUserAboutError();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        boolean isAvailable = false;

        if (networkInfo != null && networkInfo.isConnected()) {
            isAvailable = true;
        }
        else {

        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertUserDialog alertDialog = new AlertUserDialog();
        alertDialog.show(getFragmentManager(), "error_dialog");

    }
}
