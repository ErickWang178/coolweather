package com.erick.json;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Erick on 2017/6/5.
 */

public class Weather {
    @SerializedName("alarms")
    public List<Alarm> alarmList;

    public AQI aqi;

    public Basic basic;

    @SerializedName("daily_forecast")
    public List<DailyForecast> dailyForecastList;

    public Now now;

    public String status;

    public Suggestion suggestion;

    @Override
    public String toString() {
        return "Weather{" +
                "alarmList=" + alarmList +
                ", aqi=" + aqi +
                ", basic=" + basic +
                ", dailyForecastList=" + dailyForecastList +
                ", now=" + now +
                ", status='" + status + '\'' +
                ", suggestion=" + suggestion +
                '}';
    }
}
