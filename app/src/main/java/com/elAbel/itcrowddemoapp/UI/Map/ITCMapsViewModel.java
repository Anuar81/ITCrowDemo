package com.elAbel.itcrowddemoapp.UI.Map;

import androidx.lifecycle.ViewModel;

public class ITCMapsViewModel extends ViewModel {
    private Double lat;
    private Double lon;
    private String cityName;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
