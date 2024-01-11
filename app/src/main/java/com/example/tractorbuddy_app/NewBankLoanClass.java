package com.example.tractorbuddy_app;

public class NewBankLoanClass {
    private String loanId;
    private String bankId;
    private String userId;
    private String bankName;
    private String branchName;
    private String pincode;
    private String address;
    private String ifsccode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public NewBankLoanClass(String loanId, String bankId, String userId, String bankName, String branchName, String pincode, String address, String ifsccode, String status) {
        this.bankId = bankId;
        this.userId = userId;
        this.bankName = bankName;
        this.branchName = branchName;
        this.pincode = pincode;
        this.address = address;
        this.ifsccode = ifsccode;
        this.loanId=loanId;
        this.status=status;
    }

    public NewBankLoanClass() {
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
