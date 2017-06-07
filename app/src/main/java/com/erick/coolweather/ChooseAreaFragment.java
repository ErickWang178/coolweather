package com.erick.coolweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.erick.entitys.City;
import com.erick.entitys.County;
import com.erick.entitys.Province;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erick on 2017/5/16.
 */

public class ChooseAreaFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "wyg";

    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTY = 2;
    private int currentLevel = LEVEL_PROVINCE;

    private TextView tvTitleShowArea;
    private Button btnAreaBack;
    private ListView listArea;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private Province selectedProvince;
    private City selectedCity;
    private County selectedCounty;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area_fragment, container, false);

        tvTitleShowArea = (TextView) view.findViewById(R.id.tv_title_area);
        btnAreaBack = (Button) view.findViewById(R.id.btnAreaBack);
        btnAreaBack.setOnClickListener(this);
        listArea = (ListView) view.findViewById(R.id.list_area);
        adapter = new ArrayAdapter<String>(
                container.getContext(),
                android.R.layout.simple_list_item_1,
                dataList);
        listArea.setAdapter(adapter);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
         listArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE){
                    selectedProvince = provinceList.get(position);
                    queryCity();
                } else if (currentLevel == LEVEL_CITY){
                    selectedCity = cityList.get(position);
                    queryCounty();
                } else if (currentLevel == LEVEL_COUNTY){
                    selectedCounty = countyList.get(position);
                    String weatherId = selectedCounty.getWeatherId();
                    Intent intent = new Intent(getActivity(),WeatherActivity.class);
                    intent.putExtra("weather_id",weatherId);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        queryProvince();

    }

    private void queryCounty() {
        tvTitleShowArea.setText(selectedCity.getLeaderZh());
        btnAreaBack.setVisibility(View.VISIBLE);

        countyList = DataSupport.where("leaderZh = ?",String.valueOf(selectedCity.getLeaderZh())).find(County.class);

        if (countyList.size() > 0){
            dataList.clear();
            for (County county:countyList) {
                dataList.add(county.getCityZh());
            }
            adapter.notifyDataSetChanged();
            listArea.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        } else {
            Toast.makeText(MyApplication.getContext(),"没有找到城市信息",Toast.LENGTH_SHORT).show();
        }
    }

    private void queryCity() {
        tvTitleShowArea.setText(selectedProvince.getProvinceZh());
        btnAreaBack.setVisibility(View.VISIBLE);

        cityList = DataSupport.where("provinceZh=?",String.valueOf(selectedProvince.getProvinceZh())).find(City.class);
        if (cityList.size() > 0){
            dataList.clear();

            for (City city:cityList) {
                dataList.add(city.getLeaderZh());
            }
            adapter.notifyDataSetChanged();
            listArea.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            Toast.makeText(MyApplication.getContext(),"没有找到城市信息",Toast.LENGTH_SHORT).show();
        }
    }

    private void queryProvince() {
        tvTitleShowArea.setText("中国-省");
        btnAreaBack.setVisibility(View.INVISIBLE);

        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0){
            dataList.clear();
            for (Province province : provinceList) {
                //Log.d(TAG, "queryProvince: " + province.getProvinceZh());
                dataList.add(province.getProvinceZh());
            }
            adapter.notifyDataSetChanged();
            listArea.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            Toast.makeText(MyApplication.getContext(),"没有找到省份信息",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAreaBack:
                if (currentLevel == LEVEL_COUNTY){
                    queryCity();
                } else if(currentLevel == LEVEL_CITY){
                    queryProvince();
                }
                break;
            default:
        }
    }

    public County getSelectedCounty(){
        return selectedCounty;
    }
}
