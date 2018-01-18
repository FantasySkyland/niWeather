package com.example.administrator.normalweather.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.normalweather.R;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */

public class SelectLocationAdapter extends BaseAdapter {
    private Context mContext;


    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    private List<String> dataList;

    public SelectLocationAdapter(Context context , List list){
        mContext = context;
        dataList = list;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext,R.layout.select_list_view_item,null);
        TextView textView = (TextView) view.findViewById(R.id.select_text);
        textView.setText(dataList.get(position));
        return view;
    }
}
