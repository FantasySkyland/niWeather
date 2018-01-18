package com.example.administrator.normalweather.service.presenter;

import android.content.Intent;

import com.example.administrator.normalweather.service.view.ImageView;
import com.example.administrator.normalweather.service.view.WeatherView;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface Presenter {
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(WeatherView weatherView, ImageView imageView);

    void attachIncomingIntent(Intent intent);//暂时没用到
}
