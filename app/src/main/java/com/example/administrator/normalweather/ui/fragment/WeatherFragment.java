package com.example.administrator.normalweather.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.normalweather.R;
import com.example.administrator.normalweather.service.entity.Weather;
import com.example.administrator.normalweather.service.presenter.WeatherPresenter;
import com.example.administrator.normalweather.service.view.WeatherView;
import com.example.administrator.normalweather.ui.adapter.DailyAdapter;
import com.example.administrator.normalweather.ui.adapter.MyPagerAdapter;
import com.example.administrator.normalweather.ui.view.MyTempLine;
import com.example.administrator.normalweather.utils.Constant;
import com.example.administrator.normalweather.utils.SpUtils;
import com.example.administrator.normalweather.utils.WeatherInfoHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/12.
 */

public class WeatherFragment extends BaseFragment {
    @BindView(R.id.current_temp)
    TextView mCurrentTemp;
    @BindView(R.id.temp_unit)
    TextView mTempUnit;
    @BindView(R.id.now_weather)
    TextView mNowWeather;
    @BindView(R.id.now_weather_icon)
    ImageView mNowWeatherIcon;
    @BindView(R.id.feel_temp)
    TextView mFeelTemp;
    @BindView(R.id.wet)
    TextView mWet;
    @BindView(R.id.wind_dir)
    TextView mWindDir;
    @BindView(R.id.current_weather_lin)
    LinearLayout mCurrentWeatherLin;
    @BindView(R.id.now_date)
    TextView mNowDate;
    @BindView(R.id.aqi)
    TextView mAqi;
    @BindView(R.id.now_linear)
    LinearLayout nowLinear;
    @BindView(R.id.forecast_viewpager)
    ViewPager mForecastViewpager;
    @BindView(R.id.daily_forecast_grid)
    GridView mDailyForecastGrid;
    @BindView(R.id.temp_line_day)
    MyTempLine mTempLineDay;
    @BindView(R.id.temp_line_night)
    MyTempLine mTempLineNight;
    Unbinder unbinder;
    @BindView(R.id.frame_empty)
    FrameLayout mFrameEmpty;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.location_title)
    TextView mLocationTitle;
    private WeatherPresenter mWeatherPresenter = new WeatherPresenter(getActivity());

    private Weather mWeather;

    public void setCityID(int cityID) {
        mCityID = cityID;
    }

    private int mCityID ;

    /**
     * 获取天气信息
     */
    @Override
    protected void init() {
        mWeatherPresenter.onCreate();
        mWeatherPresenter.attachView(mWeatherView, null);
        mWeatherPresenter.getWeatherInfo(SpUtils.getString(getActivity(),Constant.LOCATION+
                        mCityID,null),
                "76af5642581c4eac956561e5a68e4aa6");
    }
    WeatherView mWeatherView = new WeatherView() {
        @Override
        public void onSuccess(Weather weatherInfo) {
            mWeather = weatherInfo;
            initUI();
        }

        @Override
        public void onError(String result) {

        }
    };
    @Override
    protected View setContentView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.weather_fragment, null);
        return view;
    }

    /**
     * 设置天气信息到界面
     */
    public void initUI() {
        WeatherInfoHandler weatherInfoHandler = new WeatherInfoHandler(mWeather);
        mLocationTitle.setText(weatherInfoHandler.getCity());
        mNowDate.setText(weatherInfoHandler.getMinTempList2().get(0)+"°"+"~"+
                weatherInfoHandler.getMaxTempList2().get(0)+"°");
        mCurrentTemp.setText(weatherInfoHandler.getNowTmp());
        mTempUnit.setVisibility(View.VISIBLE);
        mNowWeather.setText(weatherInfoHandler.getNowCond());
//        if (weatherInfoHandler.getNowAqi() != null) {
//            mAqi.setText(weatherInfoHandler.getNowQlty() + "  " + weatherInfoHandler.getNowAqi());
//            mAqi.setVisibility(View.VISIBLE);
//        }
        mFeelTemp.setText("体感温度" + weatherInfoHandler.getNowFl() + "°" + "\n" + "相对湿度"
                + weatherInfoHandler.getNowHum() + "%");
        if (weatherInfoHandler.getNowPcpn() == null || weatherInfoHandler.getNowPcpn().equals("0")) {
            mWet.setVisibility(View.VISIBLE);
            mWet.setText("降雨量" + weatherInfoHandler.getNowPcpn() + "mm");
        } else {
            mWet.setVisibility(View.GONE);
        }
        if (weatherInfoHandler.getNowWindSc().equals("微风")) {
            mWindDir.setText(weatherInfoHandler.getNowWindDir() + "\n" + "风力等级" +
                    weatherInfoHandler.getNowWindSc());
        } else {
            mWindDir.setText(weatherInfoHandler.getNowWindDir() + "\n" + "风力等级" +
                    weatherInfoHandler.getNowWindSc() + "级");
        }
        mWindDir.setText(weatherInfoHandler.getNowWindDir() + "\n" + "风力等级" +
                weatherInfoHandler.getNowWindSc());
        Glide.with(getActivity()).load("https://cdn.heweather.com/cond_icon/" +
                weatherInfoHandler.getNowCondCodde() + ".png").into(mNowWeatherIcon);
        DailyAdapter dailyAdapter = new DailyAdapter(getActivity(), weatherInfoHandler.getDayCondList(),
                weatherInfoHandler.getNightCondList(), weatherInfoHandler.getDayCodeList(),
                weatherInfoHandler.getNightCodeList(), weatherInfoHandler.getDateList());
        mDailyForecastGrid.setAdapter(dailyAdapter);
        mTempLineDay.setT(weatherInfoHandler.getMaxTempList());
        mTempLineNight.setT(weatherInfoHandler.getMinTempList());
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getActivity(), weatherInfoHandler.getMaxTempList2(),
                weatherInfoHandler.getMinTempList2(), weatherInfoHandler.getDayCondList(),
                weatherInfoHandler.getNightCondList(), weatherInfoHandler.getDayCodeList(),
                weatherInfoHandler.getNightCodeList(), weatherInfoHandler.getDateList());
        mForecastViewpager.setAdapter(myPagerAdapter);
        initHeight();
    }

    /**
     * 初始化高度
     */
    public void initHeight() {
        WindowManager windowManager = getActivity().getWindowManager();
        int emptyHeight = windowManager.getDefaultDisplay().getHeight();
        nowLinear.measure(0, 0);
        mWindDir.measure(0, 0);
        int relativeHeight = nowLinear.getMeasuredHeight();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)
                mFrameEmpty.getLayoutParams();
        layoutParams.height = emptyHeight - relativeHeight - mWindDir.getMeasuredHeight() -
                mFeelTemp.getHeight();
        mFrameEmpty.setLayoutParams(layoutParams);
        mScrollView.smoothScrollTo(0, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
