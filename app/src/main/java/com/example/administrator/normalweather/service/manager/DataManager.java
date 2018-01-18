package com.example.administrator.normalweather.service.manager;

import android.content.Context;

import com.example.administrator.normalweather.service.ImageRetrofitHelper;
import com.example.administrator.normalweather.service.RetrofitHelper;
import com.example.administrator.normalweather.service.RetrofitService;
import com.example.administrator.normalweather.service.entity.ByImage;
import com.example.administrator.normalweather.service.entity.Weather;

import io.reactivex.Observable;

/**
 * 这里进一步简化，可以直接获取到Observable对象
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    private RetrofitService imageRetrofitService;

    public DataManager(Context context){
        mRetrofitService = RetrofitHelper.getInstance(context).getService();
        imageRetrofitService = ImageRetrofitHelper.getInstance(context).getService();
    }
    public Observable<Weather> getWeather(String city , String key ){
        return mRetrofitService.getWeather(city,key );
    }
    public Observable<ByImage> getImageURL(){
        return imageRetrofitService.getImageURL();
    }
}
