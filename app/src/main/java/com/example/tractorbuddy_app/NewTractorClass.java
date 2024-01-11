package com.example.tractorbuddy_app;

public class NewTractorClass {
    private String tractorId;
    private String tractorName;
    private String tractorType;
    private String vehicleNum;
    private String chasisNum;
    private String mileage;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String price;


    public NewTractorClass(String tractorId, String tractorName, String tractorType, String vehicleNum, String chasisNum, String mileage, String imageId, String price) {
        this.tractorId = tractorId;
        this.tractorName = tractorName;
        this.tractorType = tractorType;
        this.vehicleNum = vehicleNum;
        this.chasisNum = chasisNum;
        this.mileage = mileage;
        this.imageId = imageId;
        this.price = price;
    }

    public String getTractorId() {
        return tractorId;
    }

    public void setTractorId(String tractorId) {
        this.tractorId = tractorId;
    }

    public String getTractorName() {
        return tractorName;
    }

    public void setTractorName(String tractorName) {
        this.tractorName = tractorName;
    }

    public String getTractorType() {
        return tractorType;
    }

    public void setTractorType(String tractorType) {
        this.tractorType = tractorType;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    public String getChasisNum() {
        return chasisNum;
    }

    public void setChasisNum(String chasisNum) {
        this.chasisNum = chasisNum;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    private String imageId;


    public NewTractorClass(){}
}
