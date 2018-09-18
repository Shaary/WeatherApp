package com.shaary.weatherapp.ui;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Current;
import com.shaary.weatherapp.Weather.HeadlessFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    //Adds headless fragment to fetch and store data
    HeadlessFragment headlessFragment;

    private static final String TAG_HEADLESS_FRAGMENT = "headless_fragment";
    public static final String TAG = ForecastFragment.class.getSimpleName();

    TextView temperature;

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
        temperature = view.findViewById(R.id.temperatureView);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getFragmentManager();

        //Checks if headless fragment is empty. And if it is, creates a new one
        headlessFragment = (HeadlessFragment) fragmentManager.findFragmentByTag(TAG_HEADLESS_FRAGMENT);
        if (headlessFragment == null) {
            Log.d(TAG, "onCreate: new frag");

            headlessFragment = new HeadlessFragment();
            fragmentManager.beginTransaction().add(headlessFragment, TAG_HEADLESS_FRAGMENT).commit();
        }

        Bundle bundle = this.getArguments();
        //Creates an error
        Log.d(TAG, "onCreate: the bundle is checked");
        if (bundle != null) {
            temperature.setText(bundle.getString("temp"));
        }

        //TODO: 1) use headless fragment in activity.
        //TODO: 2) if 1) doesn't work, load data in activity
    }


}
