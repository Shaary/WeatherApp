package com.shaary.weatherapp.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.Weather.HeadlessFragment;
import com.shaary.weatherapp.ui.fragments.BaseForecastFragment;
import com.shaary.weatherapp.ui.fragments.CurrentForecastFragment;
import com.shaary.weatherapp.ui.fragments.PhoneForecastFragment;
import com.shaary.weatherapp.ui.fragments.TabletForecastFragment;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements HeadlessFragment.ForecastCallBacks, CurrentForecastFragment.OnImageClickListener {

    public static final String FORECAST = "forecast";
    private static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";
    public static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private HeadlessFragment headlessFragment;

    private boolean isTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        isTablet = getResources().getBoolean(R.bool.isTab);

        //Call headless fragment
        fragmentManager = getSupportFragmentManager();
        headlessFragment = (HeadlessFragment) fragmentManager
                .findFragmentByTag(TAG_HEADLESS_FRAGMENT);
        if (headlessFragment == null) {
            refreshWeather();
        } else {
            Forecast forecast = headlessFragment.getForecast();
            setActivityFragment(forecast);
        }
    }

    //Loads the data
    @Override
    public void onPreExecute() {
    }

    //TODO: show jumping dots
    @Override
    public void onProgressUpdate(int percent) {

    }

    @Override
    public void onCancelled() {

    }

    @Override
    public void onPostExecute(Forecast forecast) {
        //Sets adapter with 3 fragments after the data was loaded
        setActivityFragment(forecast);
        Log.d(TAG, "onPostExecute: was called");

    }

    private void setActivityFragment(Forecast forecast) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // If the current device is a tablet, then insert a Tablet Fragment
        Log.d(TAG, "setActivityFragment: isTablet" + isTablet);
        if(isTablet) {
            fragment = BaseForecastFragment.newInstance(
                    TabletForecastFragment.class,
                    forecast
            );
        }
        // If the current device is a phone, then insert a Phone Fragment
        else {
            fragment = BaseForecastFragment.newInstance(
                    PhoneForecastFragment.class,
                    forecast
            );
        }

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    //TODO: Check if refresh button updates the screen

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
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertUserDialog alertDialog = new AlertUserDialog();
        alertDialog.show(getFragmentManager(), "error_dialog");
    }
}
