package com.dbins.android.hellowandroid.netservice.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SKILLSUPPORT on 2017-11-17.
 */

public class WeatherResponse extends BaseResponse {
    private HashMap<String, String> coord;
    private List<HashMap<String,Object>> weather;
    private String base;
    private HashMap<String , Object> main;
    private HashMap<String , Object> sys;

    public HashMap<String, Object> getSys() {
        return sys;
    }

    public void setSys(HashMap<String, Object> sys) {
        this.sys = sys;
    }

    public void setCoord(HashMap<String, String> coord) {
        this.coord = coord;
    }

    public void setWeather(List<HashMap<String, Object>> weather) {
        this.weather = weather;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setMain(HashMap<String, Object> main) {
        this.main = main;
    }

    public HashMap<String, String> getCoord() {

        return coord;
    }

    public List<HashMap<String, Object>> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public HashMap<String, Object> getMain() {
        return main;
    }
}
