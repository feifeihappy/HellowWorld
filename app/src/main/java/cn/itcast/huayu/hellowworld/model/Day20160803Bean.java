package cn.itcast.huayu.hellowworld.model;

import java.io.Serializable;

/**
 * @author ln：zpf on 2016/7/29
 */
public class Day20160803Bean implements Serializable {


    /**
     * temperature : 28℃~35℃
     * weather : 多云
     * weather_id : {"fa":"01","fb":"01"}
     * wind : 东南风微风
     * week : 星期日
     * date : 20160731
     */

    private String temperature;
    private String weather;
    /**
     * fa : 01
     * fb : 01
     */

    private WeatherIdBean weather_id;
    private String wind;
    private String week;
    private String date;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public WeatherIdBean getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(WeatherIdBean weather_id) {
        this.weather_id = weather_id;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
