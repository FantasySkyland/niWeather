package com.example.administrator.normalweather.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.normalweather.R;
import com.example.administrator.normalweather.dao.MyDbHelper;
import com.example.administrator.normalweather.service.entity.City;
import com.example.administrator.normalweather.service.entity.County;
import com.example.administrator.normalweather.service.entity.Province;
import com.example.administrator.normalweather.ui.adapter.SelectLocationAdapter;
import com.example.administrator.normalweather.utils.Constant;
import com.example.administrator.normalweather.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity {
    private ListView mSelectListView;
    private int PROVINCE = 0;
    private int currentLevel = 0;
    private int CITY = 1;
    private int COUNTY = 2;
    private MyDbHelper mMyDbHelper;
    /**
     * 省份、城市、县列表
     */
    private List<String> provinceIdList = new ArrayList<>();
    private List<String> cityIdList = new ArrayList<>();
    private List<String> weatherIdList = new ArrayList<>();
    private List<String> dataList = new ArrayList<>();
    private String location;
    /**
     * 数据库地址
     */
    private String file;
    /**
     * 省、市、县ID、查询天气ID
     */
    private String weatherId;
    private String selectedProvinceId;
    private String selectedCityId;
    private SelectLocationAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_fragment);
        mSelectListView = (ListView) findViewById(R.id.select_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("选择城市");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }


    /**
     * 初始化一些操作
     */
    protected void init() {
        file = getFilesDir() + "\\databases\\" + "a1.db";
        mMyDbHelper = new MyDbHelper(this);
        mMyDbHelper.writeDb();
//        SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(file,null);
//        Cursor cursor = sqLiteDatabase.query("Province",null,null,null,null,null,null,null);
//        if (!cursor.moveToFirst()){
//            MyDbHelper myDbHelper = new MyDbHelper(this);
//            myDbHelper.writeDb();
//        }
        selectProvince();
        mMyAdapter = new SelectLocationAdapter(SelectActivity.this,dataList);
        mSelectListView.setAdapter(mMyAdapter);
        mSelectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == PROVINCE){
                    selectedProvinceId = provinceIdList.get(position);
                    currentLevel = CITY;
                    selectCity();
                }else if (currentLevel == CITY ){
                    selectedCityId = cityIdList.get(position);
                    selectCounty();
                    currentLevel = COUNTY;
                }else if (currentLevel == COUNTY){
                    weatherId = weatherIdList.get(position);
                    location = dataList.get(position);
                    Intent intent1 = getIntent();
                    //cityNumber 目前添加总城市数量
                    int cityNumber = intent1.getIntExtra(Constant.CITY_NUMBER
                            ,0);
                    Intent intent = new Intent(SelectActivity.this,MainActivity.class);
                    //intent.putExtra(Constant.WEATHER_ID,weatherId);
                    intent.putExtra(Constant.LOCATION+cityNumber ,location);
                    intent.putExtra(Constant.CITY_NUMBER,cityNumber);
                    SpUtils.putInt(SelectActivity.this,Constant.CITY_NUMBER,cityNumber);
                    SpUtils.putString(SelectActivity.this,Constant.LOCATION+cityNumber,location);
                    startActivity(intent);
                    finish();
                }
                mMyAdapter.setDataList(dataList);
                mMyAdapter.notifyDataSetChanged();
                mSelectListView.setSelection(0);
            }
        });
    }
    /**
     * 选择省份
     */
    public void selectProvince() {
        Province province = new Province();
        dataList = province.getProvinceNameList(file);
        provinceIdList = province.getProvinceIdList(file);
    }
    /**
     * 选择城市
     */
    public void selectCity() {
        City city = new City();
        dataList = city.getCityNameList(file,selectedProvinceId);
        cityIdList = city.getCityIdList(file,selectedProvinceId);

    }

    /**
     * 选择县区
     */
    public void selectCounty() {
        County county = new County();
        dataList = county.getCountyNameList(file,selectedCityId);
        weatherIdList = county.getWeatherIdList(file,selectedCityId);
    }

    /**
     * @param item
     * @return
     * 设置返回按钮功能
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (currentLevel == PROVINCE){
                    finish();
                }else if (currentLevel ==CITY ){
                    selectProvince();
                    currentLevel = PROVINCE;
                }else if (currentLevel == COUNTY){
                    selectCity();
                    currentLevel = CITY;
                }
                mMyAdapter.setDataList(dataList);
                mMyAdapter.notifyDataSetChanged();
                mSelectListView.setSelection(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 设置返回键功能
     */
    @Override
    public void onBackPressed() {
        if (currentLevel == PROVINCE){
            finish();
        }else if (currentLevel ==CITY ){
            selectProvince();
            currentLevel = PROVINCE;
        }else if (currentLevel == COUNTY){
            selectCity();
            currentLevel = CITY;
        }
        mMyAdapter.setDataList(dataList);
        mMyAdapter.notifyDataSetChanged();
        mSelectListView.setSelection(0);
    }


}
