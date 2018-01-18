package com.example.administrator.normalweather.utils;

import com.example.administrator.normalweather.service.entity.Weather;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/30.
 */

public class WeatherInfoHandler {
    private Weather mWeather;

   public WeatherInfoHandler(Weather weatherInfo ){
       mWeather = weatherInfo;

   }


    /**
     * @return
     * 获取当前天气状况
     */
   public String getNowCond(){
       return mWeather.getHeWeather5().get(0).getNow().getCond().getTxt();
   }

    /**
     * @return
     * 获取当前天气代码
     */
    public String getNowCondCodde(){
        return mWeather.getHeWeather5().get(0).getNow().getCond().getCode();
    }

    /**
     * @return
     * 获取当前体感温度
     */
    public String getNowFl(){
        return mWeather.getHeWeather5().get(0).getNow().getFl();
    }

    /**
     * @return
     * 获取相对湿度
     */
    public String getNowHum(){
        return mWeather.getHeWeather5().get(0).getNow().getFl();
    }

    /**
     * @return
     * 获取降水量
     */
    public String getNowPcpn(){
        return mWeather.getHeWeather5().get(0).getNow().getPcpn();
    }

    /**
     * @return
     * 获取当前温度
     */
    public String getNowTmp(){
        return mWeather.getHeWeather5().get(0).getNow().getTmp();
    }

    /**
     * @return
     * 获取风向
     */
    public String getNowWindDir(){
        return mWeather.getHeWeather5().get(0).getNow().getWind().getDir();
    }

    /**
     * @return
     * 获取风力等级
     */
    public String getNowWindSc(){
        return mWeather.getHeWeather5().get(0).getNow().getWind().getSc();
    }
    /**
     * @return
     * 获取aqi指数
     */
    public String getNowAqi(){
        return mWeather.getHeWeather5().get(0).getAqi().getCity().getAqi();
    }
    /**
     * @return
     * 获取当前天气质量状况
     */
    public String getNowQlty(){
        return mWeather.getHeWeather5().get(0).getAqi().getCity().getQlty();
    }

    /**
     * @return
     * 获取7天天气预报白天天气
     */
    public ArrayList<String> getDayCondList(){
        ArrayList<String> dayCondList = new ArrayList<>();

        for (int i = 0 ; i<7 ;i++){
            dayCondList.add(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getCond().getTxt_d());
        }
        return dayCondList;
    }
    /**
     * @return
     * 获取7天天气预报白天天气代码
     */
    public ArrayList<String> getDayCodeList(){
        ArrayList<String> dayCodeList = new ArrayList<>();

        for (int i = 0 ; i<7 ;i++){
            dayCodeList.add(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getCond().getCode_d());
        }
        return dayCodeList;
    }

    /**
     * @return
     * 获取7天天气预报夜晚天气
     */
    public ArrayList<String> getNightCondList(){
        ArrayList<String> nightCondList = new ArrayList<>();

        for (int i = 0 ; i<7 ;i++){
            nightCondList.add(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getCond().getTxt_n());
        }
        return nightCondList;
    }
    /**
     * @return
     * 获取7天天气预报夜晚天气代码
     */
    public ArrayList<String> getNightCodeList(){
        ArrayList<String> nightCodeList = new ArrayList<>();

        for (int i = 0 ; i<7;i++){
            nightCodeList.add(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getCond().getCode_n());
        }
        return nightCodeList;
    }
    /**
     * @return
     * 获取7天天气预报最高温度
     */
    public int[] getMaxTempList(){
        int[] maxTempList = new int[7];

        for (int i = 0 ; i<7 ;i++){
            maxTempList[i] = Integer.parseInt(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getTmp().getMax());

        }
        return maxTempList;
    }
    /**
     * @return
     * 获取7天天气预报最低温度
     */
    public ArrayList<String> getMaxTempList2(){
        ArrayList<String> maxTempList = new ArrayList<>();

        for (int i = 0 ; i<7 ;i++){
            maxTempList.add(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getTmp().getMax());

        }
        return maxTempList;
    }
    /**
     * @return
     * 获取7天天气预报最低温度
     */
    public int[] getMinTempList(){
        int[] minTempList = new int[7];

        for (int i = 0 ; i<7 ;i++){
            minTempList[i] = Integer.parseInt(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getTmp().getMin());
        }
        return minTempList;
    }
    /**
     * @return
     * 获取7天天气预报最低温度
     */
    public ArrayList<String> getMinTempList2(){
        ArrayList<String> minTempList = new ArrayList<>();

        for (int i = 0 ; i<7 ;i++){
            minTempList.add(mWeather.getHeWeather5().
                    get(0).getDaily_forecast().get(i).getTmp().getMin());

        }
        return minTempList;
    }
    /**
     * @return
     * 获取7天天气预报日期
     */
    public ArrayList<String> getDateList(){
        ArrayList<String> dateList = new ArrayList<>();

        for (int i = 0 ; i<7 ;i++){
            String date = mWeather.getHeWeather5().get(0).getDaily_forecast().get(i).getDate();
            String date2 = date.substring(date.length() - 5, date.length());
            dateList.add(date2);
        }
        return dateList;
    }

    /**
     * @return
     * 获取城市
     */
    public String getCity(){
        return mWeather.getHeWeather5().get(0).getBasic().getCity();
    }
}
