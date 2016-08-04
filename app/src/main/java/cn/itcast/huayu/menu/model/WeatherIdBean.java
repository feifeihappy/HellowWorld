package cn.itcast.huayu.menu.model;

import java.io.Serializable;

/**
 * @author ln：zpf on 2016/7/29
 */
public class WeatherIdBean implements Serializable{


    /**
     * fa : 04
     * fb : 01
     */

    private String fa;
    private String fb;

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }
}
