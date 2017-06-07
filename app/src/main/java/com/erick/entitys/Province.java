package com.erick.entitys;

import org.litepal.crud.DataSupport;

/**
 * Created by Erick on 2017/5/15.
 */

public class Province extends DataSupport {
    private String provinceZh;

    public String getProvinceZh() {
        return provinceZh;
    }

    public void setProvinceZh(String provinceZh) {
        this.provinceZh = provinceZh;
    }
}
