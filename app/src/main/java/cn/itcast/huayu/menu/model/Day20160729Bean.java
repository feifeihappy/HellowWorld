package cn.itcast.huayu.menu.model;

import java.io.Serializable;

/**
 * @author ln：zpf on 2016/7/29
 */
public class Day20160729Bean implements Serializable{


    /**
     * temperature : 29℃~39℃
     * weather : 雷阵雨转多云
     * weather_id : {}
     * wind : 南风微风
     * week : 星期五
     * date : 20160729
     */

    private String temperature;
    private String weather;
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
