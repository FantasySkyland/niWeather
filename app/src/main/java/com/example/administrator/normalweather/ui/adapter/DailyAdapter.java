package com.example.administrator.normalweather.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.normalweather.R;
import com.example.administrator.normalweather.utils.Week;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017/7/12.
 */

public class DailyAdapter extends BaseAdapter {
    private Date date;//当前时间
    private Week mWeek;
    private Context mContext;
    private ArrayList<String> dayList;
    private ArrayList<String> nightList;
    private ArrayList<String> dayCodeList;
    private ArrayList<String> nightCodeList;
    private ArrayList<String> dateList;
    private String nowWeek;
    private int weekCode;
    public DailyAdapter(Context context , ArrayList<String> dayList2, ArrayList<String> nightList2,
                        ArrayList<String> dayCodeList2,ArrayList<String> nightCodeList2,ArrayList<String> dateList2){
        mContext = context;
        dayList = dayList2;
        nightList = nightList2;
        dayCodeList = dayCodeList2 ;
        nightCodeList = nightCodeList2;
        dateList = dateList2;
        init();
    }

    private void init() {
        date = new Date();
        mWeek = new Week();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E");
        nowWeek = dateFormat.format(date);
        weekCode = mWeek.getWeekCode(nowWeek);
    }

    @Override
    public int getCount() {
        return dayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.gridview_item,null);
        TextView week_forecast = (TextView) view.findViewById(R.id.week_forecast);
        TextView forecast_date = (TextView) view.findViewById(R.id.forecast_date);
        TextView wind_forecast = (TextView) view.findViewById(R.id.wind_forecast);
        TextView wind_sp_forecast = (TextView) view.findViewById(R.id.wind_sp_forecast);
        ImageView day_weather_map = (ImageView) view.findViewById(R.id.day_weather_map);
        ImageView night_weather_map = (ImageView) view.findViewById(R.id.night_weather_map);


        //日期信息
        if (position == 0) {
            week_forecast.setText("今天");
        } else if (position == 1) {
            week_forecast.setText("明天");
        } else {
            int forecastWeekCode = weekCode + position;
            if (forecastWeekCode > 6) {
                week_forecast.setText(mWeek.setWeek(forecastWeekCode));
            } else {
                week_forecast.setText(mWeek.setWeek(forecastWeekCode));
            }
        }
        forecast_date.setText(dateList.get(position).replaceFirst("-", "/"));
        Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                dayCodeList.get(position)+".png").into(day_weather_map);
        Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                nightCodeList.get(position)+".png").into(night_weather_map);
        return view;
    }
}
