package com.example.administrator.normalweather.service.view;

import com.example.administrator.normalweather.service.entity.Weather;

/**
 * Created by Administrator on 2017/6/29.
 */

public interface WeatherView extends View {
    void onSuccess(Weather weatherInfo);
    void onError(String result);
}
