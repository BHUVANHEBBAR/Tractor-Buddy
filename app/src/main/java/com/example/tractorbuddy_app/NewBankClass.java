package com.example.tractorbuddy_app;

public class NewBankClass {
    private String bankId;
    private String bankName;
    private String branchName;
    private String pincode;
    private String address;
    private String ifsccode;
    private String status;
    public NewBankClass() {
    }

    public NewBankClass(String bankId, String bankName, String branchName, String pincode, String address, String ifsccode) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.branchName = branchName;
        this.pincode = pincode;
        this.address = address;
        this.ifsccode = ifsccode;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIfsccode() {
        return ifsccode;
    }

    public void setIfsccode(String ifsccode) {
        this.ifsccode = ifsccode;
    }
}
