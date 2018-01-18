package com.example.administrator.normalweather.service.view;

import com.example.administrator.normalweather.service.entity.ByImage;

/**
 * Created by Administrator on 2017/7/11.
 */

public interface ImageView extends View {
    void onSuccess(ByImage byImageInfo);
    void onError(String result);
}
