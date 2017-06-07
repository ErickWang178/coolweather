package com.erick.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erick on 2017/6/5.
 */

public class Alarm {
    public String level;
    public String title;
    public String type;

    @SerializedName("txt")
    public String info;
}
