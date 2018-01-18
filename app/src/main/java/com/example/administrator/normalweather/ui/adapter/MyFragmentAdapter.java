package com.example.administrator.normalweather.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.normalweather.ui.fragment.WeatherFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/14.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    public void setWeatherFragments(ArrayList<WeatherFragment> weatherFragments) {
        mWeatherFragments = weatherFragments;
    }



    private ArrayList<WeatherFragment> mWeatherFragments;
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mWeatherFragments.get(position);
    }

    @Override
    public int getCount() {
        return mWeatherFragments.size();
    }

}
