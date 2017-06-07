package com.erick.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erick on 2017/6/5.
 */

public class DailyForecast {
    public WeatherCondition cond;
    public class WeatherCondition{
        @SerializedName("txt_d")
        public String info;
    }

    public String date;

    public Temperatuer tmp;
    public class Temperatuer{
        public String max;

        public String min;
    }

    public Wind wind;
    public class Wind{
        @SerializedName("dir")
        public String direction;

        @SerializedName("sc")
        public String speed;
    }

    @Override
    public String toString() {
        return "DailyForecast{" +
                "cond=" + cond +
                ", date='" + date + '\'' +
                ", tmp=" + tmp +
                ", wind=" + wind +
                '}';
    }
}
