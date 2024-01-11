package com.example.tractorbuddy_app;

public class NewTractorRentClass {
    private String userId;
    private String appliedId;
    private String tractorId;
    private String tractorName;
    private String tractorType;
    private String vehicleNum;
    private String chasisNum;
    private String mileage;
    private String rentprice;
    private String accnum;
    private  String bankname;
    private String branchname;
    private String ifsccode;
    private     String numofdays;
    private String totalprice;

    private String status;
    public NewTractorRentClass() {
    }

    public NewTractorRentClass(String userId, String appliedId, String tractorId, String tractorName, String tractorType, String vehicleNum, String chasisNum, String mileage,
                               String status, String rentprice, String accnum, String bankname, String branchname, String ifsccode,
                               String numofdays, String totalprice) {
        this.userId = userId;
        this.appliedId = appliedId;
        this.tractorId = tractorId;
        this.tractorName = tractorName;
        this.tractorType = tractorType;
        this.vehicleNum = vehicleNum;
        this.chasisNum = chasisNum;
        this.mileage = mileage;
        this.status = status;
        this.rentprice=rentprice;
        this.accnum=accnum;
        this.bankname=bankname;
        this.branchname=branchname;
        this.ifsccode=ifsccode;
        this.numofdays=numofdays;
        this.totalprice=totalprice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppliedId() {
        return appliedId;
    }

    public void setAppliedId(String appliedId) {
        this.appliedId = appliedId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public String getRentprice() {
        return rentprice;
    }

    public void setRentprice(String rentprice) {
        this.rentprice = rentprice;
    }

    public String getAccnum() {
        return accnum;
    }

    public void setAccnum(String accnum) {
        this.accnum = accnum;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getBranchname() {
        return branchname;
    }

    public void setBranchname(String branchname) {
        this.branchname = branchname;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }

    public String getNumofdays() {
        return numofdays;
    }

    public void setNumofdays(String numofdays) {
        this.numofdays = numofdays;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }
}
