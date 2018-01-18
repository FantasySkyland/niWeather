package com.example.administrator.normalweather.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.normalweather.service.entity.ByImage;
import com.example.administrator.normalweather.service.entity.Weather;
import com.example.administrator.normalweather.service.manager.DataManager;
import com.example.administrator.normalweather.service.view.ImageView;
import com.example.administrator.normalweather.service.view.WeatherView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/29.
 */

public class WeatherPresenter implements Presenter {
    Context mContext;
    private DataManager mDataManager;
    private Weather mWeather;
    private ByImage mByImage;
    private ImageView mImageView;
    private WeatherView mWeatherView;

    public WeatherPresenter(Context context){
        mContext = context;
    }
    @Override
    public void onCreate() {
        mDataManager = new DataManager(mContext);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(WeatherView weatherView, ImageView imageView) {
        mWeatherView = weatherView;
        mImageView = imageView;
    }


    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    public void getWeatherInfo(String city , String key){
        mDataManager.getWeather(city,key).
                subscribeOn(Schedulers.io()).
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Weather weather) {
                        mWeather = weather;
                        if (mWeather != null ){
                            mWeatherView.onSuccess(weather);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        mWeatherView.onError("网络连接错误");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getImageInfo(){
        mDataManager.getImageURL().
                subscribeOn(Schedulers.io()).
                unsubscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<ByImage>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ByImage byImage) {
                mByImage = byImage;
                if (mByImage!= null){
                    mImageView.onSuccess(mByImage);
                }
            }

            @Override
            public void onError(Throwable e) {
                mImageView.onError("请求失败");
            }

            @Override
            public void onComplete() {

            }
        });

    }

}
