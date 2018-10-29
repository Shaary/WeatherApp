package com.shaary.weatherapp.ui;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaary.weatherapp.R;
import com.shaary.weatherapp.Weather.Forecast;
import com.shaary.weatherapp.adapter.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentWithRecyclerBase extends Fragment {

    public abstract BaseAdapter getAdapter();

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    protected Forecast forecast;
    protected FragmentActivity mFragmentActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mFragmentActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        forecast = getArguments().getParcelable("forecast");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_with_recycler_base, container, false);
        ButterKnife.bind(this, view);
        setLayoutManager();
        BaseAdapter adapter = getAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void setLayoutManager() {
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            // In portrait
            layoutManager = new LinearLayoutManager(getActivity());
        }
        recyclerView.setLayoutManager(layoutManager);
    }

}
