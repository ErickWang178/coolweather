package com.erick.json;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Erick on 2017/6/5.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    public class Comfort{
        @SerializedName("txt")
        public String info;
    }

    @SerializedName("cw")
    public CarWash carWash;
    public class CarWash{
        @SerializedName("txt")
        public String info;
    }

    public Sport sport;
    public class Sport{
        @SerializedName("txt")
        public String info;
    }

    @SerializedName("trav")
    public Travel travel;
    public class Travel{
        @SerializedName("txt")
        public String info;
    }

}
