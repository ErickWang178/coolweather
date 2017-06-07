package com.erick.httputil;

import com.erick.entitys.City;
import com.erick.entitys.County;
import com.erick.entitys.Province;
import com.erick.json.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Erick on 2017/5/16.
 */

public class JsonUtil {

    public static boolean putALLCityInfo(String response){
        putProviceInfo(response);
        putCityInfo(response);
        putCountyInfo(response);

        return true;
    }

    public static boolean putProviceInfo(String response){
        try {
            JSONArray provinceArray = new JSONArray(response);
            Set<String> provinceSet = new HashSet<String> ();
            for (int i=0; i<provinceArray.length(); i++){
                JSONObject obj = provinceArray.getJSONObject(i);
                String provinceName = obj.getString("provinceZh");
                provinceSet.add(provinceName);
            }
            Iterator<String> iter = provinceSet.iterator();
            while (iter.hasNext()){
                Province province = new Province();
                province.setProvinceZh(iter.next());

                province.save();
            }
            return  true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean putCityInfo(String response){
        try {
            JSONArray cityArray = new JSONArray(response);
            for (int i=0; i<cityArray.length(); i++){
                JSONObject obj = cityArray.getJSONObject(i);
                if (obj.getString("cityZh").equalsIgnoreCase(obj.getString("leaderZh"))){
                    City city = new City();
                    city.setWeatherId(obj.getString("id"));
                    city.setProvinceZh(obj.getString("provinceZh"));
                    city.setLeaderZh(obj.getString("leaderZh"));
                    city.setLat(obj.getString("lat"));
                    city.setLon(obj.getString("lon"));

                    city.save();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean putCountyInfo(String response){
        try {
            JSONArray arrayCounty = new JSONArray(response);
            for (int i=0; i<arrayCounty.length(); i++){
                JSONObject countyObj = arrayCounty.getJSONObject(i);
                County county = new County();
                county.setWeatherId(countyObj.getString("id"));
                county.setCityZh(countyObj.getString("cityZh"));
                county.setLeaderZh(countyObj.getString("leaderZh"));
                county.setLat(countyObj.getString("lat"));
                county.setLon(countyObj.getString("lon"));

                county.save();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject obj = new JSONObject(response);
            JSONArray jsonArray = obj.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
