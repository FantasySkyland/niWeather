package com.example.administrator.normalweather.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
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

public class MyPagerAdapter extends android.support.v4.view.PagerAdapter {
    private Context mContext;
    //当前星期
    private String nowWeek;
    private int weekCode;
    private Week week;
    private Date date;
    private ArrayList<String> maxTempList;
    private ArrayList<String> minTempList;
    private ArrayList<String> dayList;
    private ArrayList<String> nightList;
    private ArrayList<String> dayCodeList;
    private ArrayList<String> nightCodeList;
    private ArrayList<String> dateList;
    public MyPagerAdapter(Context context,ArrayList<String> maxList,ArrayList<String> minList,
                          ArrayList<String> dayList2, ArrayList<String> nightList2,
                          ArrayList<String> dayCodeList2,ArrayList<String> nightCodeList2,ArrayList<String> dateList2){
        mContext = context;
        maxTempList = maxList;
        minTempList = minList;
        dayList = dayList2;
        nightList = nightList2;
        dayCodeList = dayCodeList2 ;
        nightCodeList = nightCodeList2;
        dateList = dateList2;
        init();
    }

    private void init() {
        date = new Date();
        week = new Week();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E");
        nowWeek = dateFormat.format(date);
        weekCode = week.getWeekCode(nowWeek);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.viewpager_item, null);
        TextView pager_tv1 = (TextView) view.findViewById(R.id.pager_tv1);
        TextView pager_tv2 = (TextView) view.findViewById(R.id.pager_tv2);
        TextView pager_tv3 = (TextView) view.findViewById(R.id.pager_tv3);
        ImageView pager_ima1 = (ImageView) view.findViewById(R.id.pager_ima1);
        TextView pager_tv1_2 = (TextView) view.findViewById(R.id.pager_tv1_2);
        TextView pager_tv2_2 = (TextView) view.findViewById(R.id.pager_tv2_2);
        TextView pager_tv3_2 = (TextView) view.findViewById(R.id.pager_tv3_2);
        ImageView pager_ima2 = (ImageView) view.findViewById(R.id.pager_ima2);
        TextView pager_tv1_3 = (TextView) view.findViewById(R.id.pager_tv1_3);
        TextView pager_tv2_3 = (TextView) view.findViewById(R.id.pager_tv2_3);
        TextView pager_tv3_3 = (TextView) view.findViewById(R.id.pager_tv3_3);
        ImageView pager_ima3 = (ImageView) view.findViewById(R.id.pager_ima3);
        if (position == 0) {
            pager_tv1.setText("明天");
            pager_tv1_2.setText(week.setWeek(weekCode + 2));
            pager_tv1_3.setText(week.setWeek(weekCode + 3));
            pager_tv2.setText(minTempList.get(1) + "/" +
                    maxTempList.get(1) + "°");
            pager_tv2_2.setText(minTempList.get(2) + "/" +
                    maxTempList.get(2) + "°");
            pager_tv2_3.setText(minTempList.get(3) + "/" +
                    maxTempList.get(3) + "°");
            if (dayList.get(1).equals(
                    nightList.get(1))) {
                pager_tv3.setText(dayList.get(1));
            } else {
                pager_tv3.setText(dayList.get(1) + "转" +
                        nightList.get(1));
            }
            if (dayList.get(2).equals(
                    nightList.get(2))) {
                pager_tv3_2.setText(dayList.get(2));
            } else {
                pager_tv3_2.setText(dayList.get(3) + "转" +
                        nightList.get(3));
                }
            if (dayList.get(3).equals(
                    nightList.get(3))) {
                pager_tv3_3.setText(dayList.get(3));
            } else {
                pager_tv3_3.setText(dayList.get(3) + "转" +
                        nightList.get(3));
            }
            Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                    dayCodeList.get(1)+".png").into(pager_ima1);
            Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                    dayCodeList.get(2)+".png").into(pager_ima2);
            Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                    dayCodeList.get(3)+".png").into(pager_ima3);
        } else {
            pager_tv1.setText(week.setWeek(weekCode + 4));
            pager_tv1_2.setText(week.setWeek(weekCode + 5));
            pager_tv1_3.setText(week.setWeek(weekCode + 6));
            pager_tv2.setText(minTempList.get(4) + "/" +
                    maxTempList.get(4) + "°");
            pager_tv2_2.setText(minTempList.get(5) + "/" +
                    maxTempList.get(5) + "°");
            pager_tv2_3.setText(minTempList.get(6) + "/" +
                    maxTempList.get(6) + "°");
            if (dayList.get(3).equals(
                    nightList.get(3))) {
                pager_tv3.setText(dayList.get(3));
            } else {
                pager_tv3.setText(dayList.get(3) + "转" +
                        nightList.get(3));
            }
            if (dayList.get(4).equals(
                    nightList.get(4))) {
                pager_tv3_2.setText(dayList.get(4));
            } else {
                pager_tv3_2.setText(dayList.get(4) + "转" +
                        nightList.get(4));
                if (dayList.get(5).equals(
                        nightList.get(5))) {
                    pager_tv3_3.setText(dayList.get(5));
                } else {
                    pager_tv3_3.setText(dayList.get(5) + "转" +
                            nightList.get(5));
                }
            }
            Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                    dayCodeList.get(4)+".png").into(pager_ima1);
            Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                    dayCodeList.get(5)+".png").into(pager_ima2);
            Glide.with(mContext).load("https://cdn.heweather.com/cond_icon/"+
                    dayCodeList.get(6)+".png").into(pager_ima3);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}

