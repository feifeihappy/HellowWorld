package cn.itcast.huayu.menu.model;

import java.io.Serializable;

/**
 * @author ln：zpf on 2016/7/29
 */
public class WeatherResult implements Serializable {


    private SkBean sk;
    private TodayBean today;
    private FutureBean future;

    public SkBean getSk() {
        return sk;
    }

    public void setSk(SkBean sk) {
        this.sk = sk;
    }

    public TodayBean getToday() {
        return today;
    }

    public void setToday(TodayBean today) {
        this.today = today;
    }

    public FutureBean getFuture() {
        return future;
    }

    public void setFuture(FutureBean future) {
        this.future = future;
    }




}
