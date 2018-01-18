package com.example.administrator.normalweather.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/9/11.
 */
public class SpUtils {
    private static SharedPreferences sp;

    public static void putBoolean(Context context, String key, boolean defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,defValue).apply();

    }
    public static boolean getBoolean(Context context, String key, boolean defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }
    public static void putString(Context context, String key, String defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,defValue).apply();
    }
    public static String getString(Context context, String key, String defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue);
    }
    public static void putInt(Context context, String key, int defValue ){
        if (sp == null){
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key,defValue).apply();
    }
    public static int getInt(Context context, String key, int defValue ) {
        if (sp == null) {
            sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }
}
