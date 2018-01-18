package com.example.administrator.normalweather.service;

import com.example.administrator.normalweather.service.entity.ByImage;
import com.example.administrator.normalweather.service.entity.Weather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/6/29.
 */

public interface RetrofitService {
    @GET("weather?")
    Observable<Weather> getWeather(@Query("city") String city, @Query("key") String key );
    @GET("HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<ByImage> getImageURL();
}
