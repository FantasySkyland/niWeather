package com.example.administrator.normalweather.service;

import android.content.Context;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/29.
 */

public class RetrofitHelper {
    private Context mContext;

    OkHttpClient mOkHttpClient  = new OkHttpClient();//用于设置http属性，如请求超时
    GsonConverterFactory mFactory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public RetrofitService mRetrofitService;

    /**
     * 获取helper实例
     * @param context
     * @return
     */
    public static RetrofitHelper getInstance(Context context){
        if(instance == null){
            instance = new RetrofitHelper(context);
        }
        return  instance;
    }
    private RetrofitHelper(Context context){
        mContext = context;
        init();
    }

    private void init() {
        resetAPP();
    }

    /**
     * 初始化retrofit
     */
    private void resetAPP() {
        mRetrofit = new Retrofit.Builder().
                baseUrl("https://free-api.heweather.com/v5/").
                client(mOkHttpClient).
                addConverterFactory(mFactory).
                addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                build();
    }

    /**
     *
     * @return
     */
    public RetrofitService getService(){
        mRetrofitService = mRetrofit.create(RetrofitService.class);
        return mRetrofitService;
    }
}
