package com.erick.entitys;

import org.litepal.crud.DataSupport;

/**
 * Created by Erick on 2017/5/15.
 */

public class County extends DataSupport {
    private String weatherId;
    private String cityZh;
    private String provinceZh;
    private String leaderZh;

    public String getLeaderZh() {
        return leaderZh;
    }

    public void setLeaderZh(String leaderZh) {
        this.leaderZh = leaderZh;
    }

    private String lat;
    private String lon;

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getCityZh() {
        return cityZh;
    }

    public void setCityZh(String cityZh) {
        this.cityZh = cityZh;
    }

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "County{" +
                ", weatherId='" + weatherId + '\'' +
                ", cityZh='" + cityZh + '\'' +
                ", provinceZh='" + provinceZh + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
