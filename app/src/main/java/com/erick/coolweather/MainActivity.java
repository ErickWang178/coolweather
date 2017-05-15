package com.erick.coolweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.erick.httputil.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "wyg";
    private TextView tvShowAllCity = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvShowAllCity = (TextView) findViewById(R.id.tvShowAllCity);
        requestAllCityId(HttpUtil.CITY_ID_ALL_URL);
    }

    public void requestAllCityId(final String urlStr){
        HttpUtil.openHttpConnection(urlStr, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String content = response.body().string();
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvShowAllCity.setText(content);
                    }
                });

            }
        });

        return;
    }

    public String requesetCityWeather(String cityID){
        String content = null;

        return content;
    }

}
