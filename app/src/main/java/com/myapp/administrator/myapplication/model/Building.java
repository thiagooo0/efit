package com.myapp.administrator.myapplication.model;

/**
 * Created by Administrator on 2015/9/19.
 */
public class Building {
    private double latitude;//纬度
    private double longitude;//经度
    private String buildingName;//建筑名
    private int buildingId;//建筑Id
    public Building(){

    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }
    public double getLongitude() {
        return longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public int getBuildingId() {
        return buildingId;
    }
}
