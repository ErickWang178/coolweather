package com.erick.coolweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.erick.entitys.County;
import com.erick.entitys.Province;
import com.erick.httputil.HttpUtil;
import com.erick.httputil.JsonUtil;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "wyg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAllCityId(HttpUtil.LOACL_URL);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (preferences.getString("weather",null) != null){
            Intent intent = new Intent(this,WeatherActivity.class);
            startActivity(intent);
            finish();
        }

        ChooseAreaFragment fragment = new ChooseAreaFragment();
        replaceFragment(fragment);
        County county = fragment.getSelectedCounty();
        requesetCityWeather(county);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frag_area,fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void requestAllCityId(final String urlStr){
        if(isHaveData() == true) return;
        Log.d(TAG, "requestAllCityId: ***************************");
        HttpUtil.openHttpConnection(urlStr, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JsonUtil.putALLCityInfo(content); //解析JSON数据获取所有城市信息
                    }
                });

            }
        });

        return;
    }

    public void requesetCityWeather(County county){
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    boolean isHaveData(){
        List<Province> list = DataSupport.findAll(Province.class);

        if (list.size() > 0){
            return true;
        }else{
            return false;
        }
    }

}
