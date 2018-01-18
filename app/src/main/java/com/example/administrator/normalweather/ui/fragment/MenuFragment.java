package com.example.administrator.normalweather.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.normalweather.R;
import com.example.administrator.normalweather.ui.activity.SelectActivity;
import com.example.administrator.normalweather.utils.Constant;
import com.example.administrator.normalweather.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/7/14.
 */

public class MenuFragment extends BaseFragment {
    @BindView(R.id.button)
    Button mButton;
    Unbinder unbinder;


    @Override
    protected void init() {

    }

    @Override
    protected View setContentView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.menu_fragment, null);
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

    @OnClick(R.id.button)
    public void onViewClicked() {
         int cityNumber = SpUtils.getInt(getActivity(),Constant.CITY_NUMBER,1);
        Intent intent = new Intent(getActivity(), SelectActivity.class);
        cityNumber++;
        intent.putExtra(Constant.CITY_NUMBER,cityNumber);
        startActivity(intent);
    }
}
