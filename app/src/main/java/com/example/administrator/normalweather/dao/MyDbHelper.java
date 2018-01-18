package com.example.administrator.normalweather.dao;

import android.content.Context;

import com.example.administrator.normalweather.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/6/29.
 */

public class MyDbHelper {
    private Context mContext;
    public MyDbHelper(Context context){
        mContext = context;
    }


    /**
     * 数据库地址
     */
    private String file;


    /**
     * 复制数据库到本地
     */
    public void writeDb() {
        file = mContext.getFilesDir() + "\\databases\\" + "a1.db";
        FileOutputStream fout = null;
        InputStream inputStream = null;
        try {
            inputStream = mContext.getResources().openRawResource(R.raw.db_weather);
            fout = new FileOutputStream(new File(file));
            byte[] buffer = new byte[128];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                fout.write(buffer, 0, len);
            }
            buffer = null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
