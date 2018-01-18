package com.example.administrator.normalweather.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/30.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * 根布局
     */
    protected View rootView;
    Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        避免重复加载UI
        if (rootView == null) {
            rootView = setContentView(inflater);
        }
//        缓存的rootView需要判断是否已经被加入过parent, 如果有则需要从parent删除，否则会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    protected abstract void init();

    protected abstract View setContentView(LayoutInflater inflater);
}
