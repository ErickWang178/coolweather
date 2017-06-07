package com.erick.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erick on 2017/6/5.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;

    public WeatherCondition cond;
    public class WeatherCondition{
        @SerializedName("txt")
        public String info;
    }

    public Wind wind;
    public class Wind{
        @SerializedName("dir")
        public String direction;

        @SerializedName("sc")
        public String speed;
    }
}
