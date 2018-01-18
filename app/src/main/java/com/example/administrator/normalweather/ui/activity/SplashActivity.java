package com.example.administrator.normalweather.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.normalweather.R;
import com.example.administrator.normalweather.utils.Constant;
import com.example.administrator.normalweather.utils.SpUtils;

public class SplashActivity extends AppCompatActivity {
    public LocationClient mLocationClient = null;
    BDLocationListener myListener = new MyLocationListener();
    private BDLocation mBdLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >=23){
            if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(SplashActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.READ_PHONE_STATE},
                        0);
            }else{
                init();
            }
        }else {
            init();
        }

    }

    private void init() {
            mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
            mLocationClient.registerLocationListener(myListener);
            initLocation();
            mLocationClient.start();

    }
    /**
     * 进入选择城市
     */
    private void enterHome() {
        Intent intent = new Intent(this,SelectActivity.class);
        startActivity(intent);
        finish();
    }
    /**
     * 进入天气
     */
    private void enterWeather(){
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        intent.putExtra(Constant.LOCATION+1,SpUtils.getString(SplashActivity.this,Constant.LOCATION+1,null));
        intent.putExtra(Constant.CITY_NUMBER,SpUtils.getInt(SplashActivity.this,Constant.CITY_NUMBER,1));
        startActivity(intent);
        finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode){
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    init();
                }else {
                    ActivityCompat.requestPermissions(SplashActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.READ_PHONE_STATE},
                            0);
                    init();
                }
                break;
        }
    }
    /**
     * 定位初始化设置
     */
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=1000;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }
    class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            int cityNumber = 1;
            int locType = bdLocation.getLocType();
            mBdLocation = bdLocation;
            String location = mBdLocation.getCity();

            String locationDet = mBdLocation.getStreet();
//            Log.i(TAG, "onReceiveLocation: "+mBdLocation.getAddrStr());
//            Log.i(TAG, "onReceiveLocation: "+mBdLocation.getCity()+mBdLocation.getCityCode());
//            Log.i(TAG, "onReceiveLocation: "+mBdLocation.getCountry()+mBdLocation.getCountryCode());
//            Log.i(TAG, "onReceiveLocation: "+mBdLocation.getStreet());
//            Log.i(TAG, "onReceiveLocation: "+mBdLocation.getDistrict());
            SpUtils.putString(SplashActivity.this, Constant.LOCATION+1, location);
            SpUtils.putString(SplashActivity.this,Constant.LOCATION_DET+1,locationDet);
            SpUtils.putInt(SplashActivity.this,Constant.CITY_NUMBER,cityNumber);
            enterWeather();
        }
    }

    private static final String TAG = "位置信息";
}
