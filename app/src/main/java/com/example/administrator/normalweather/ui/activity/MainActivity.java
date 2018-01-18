package com.example.administrator.normalweather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.example.administrator.normalweather.R;
import com.example.administrator.normalweather.service.entity.ByImage;
import com.example.administrator.normalweather.service.presenter.WeatherPresenter;
import com.example.administrator.normalweather.service.view.ImageView;
import com.example.administrator.normalweather.ui.adapter.MyFragmentAdapter;
import com.example.administrator.normalweather.ui.fragment.WeatherFragment;
import com.example.administrator.normalweather.utils.Constant;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.back_image)
    android.widget.ImageView mBackImage;
    @BindView(R.id.weather_vp)
    ViewPager mWeatherVp;
    @BindView(R.id.menu_container)
    FrameLayout mMenuContainer;
    private WeatherPresenter mWeatherPresenter = new WeatherPresenter(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        Intent intent = getIntent();
        //cityNumber 目前添加总城市数量
        int cityNumber = intent.getIntExtra(Constant.CITY_NUMBER
                , 1);
        FragmentManager fm = getSupportFragmentManager();
        //FragmentTransaction ft = fm.beginTransaction();
        mWeatherPresenter.onCreate();
        mWeatherPresenter.attachView(null, mImageView);
        mWeatherPresenter.getImageInfo();
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fm);
        ArrayList<WeatherFragment> weatherFragments = new ArrayList<>();
        for (int i = 1; i <= cityNumber; i++) {
            WeatherFragment mWeatherFragment = new WeatherFragment();
            mWeatherFragment.setCityID(i);
            weatherFragments.add(mWeatherFragment);
        }
        myFragmentAdapter.setWeatherFragments(weatherFragments);
        myFragmentAdapter.notifyDataSetChanged();
        mWeatherVp.setAdapter(myFragmentAdapter);
//        ft.replace(R.id.menu_container,new MenuFragment());
//        ft.commit();

    }

    ImageView mImageView = new ImageView() {
        @Override
        public void onSuccess(ByImage byImageInfo) {
            Glide.with(MainActivity.this).load("http://s.cn.bing.net/" + byImageInfo.getImages().get(0).getUrl()).
                    into(mBackImage);
        }

        @Override
        public void onError(String result) {

        }
    };


}
