package com.erick.httputil;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Erick on 2017/5/15.
 */

public class HttpUtil {
    private static final String TAG = "wyg";

    public static final String WEATHER_MAIN_URL = "https://free-api.heweather.com/v5/weather";
    public static final String CITY_ID_ALL_URL = "https://cdn.heweather.com/china-city-list.json";
    public static final String USER_KEY = "a1bf7dfcc20546729407c341e6dd766f";

    public static final String LOACL_URL = "http://192.168.0.101:8080/studentInfo/china_city_list.json";

    public static void openHttpConnection(String urlStr,okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(urlStr).build();
        client.newCall(request).enqueue(callback);

        return;
    }




}
