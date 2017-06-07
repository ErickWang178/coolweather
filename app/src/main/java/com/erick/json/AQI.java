package com.erick.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erick on 2017/6/5.
 */

public class AQI {
    @SerializedName("city")
    public AQICity aqiCity;

    public class AQICity{
        public String aqi;

        public String pm25;

        @SerializedName("qlty")
        public String quality;
    }
}
