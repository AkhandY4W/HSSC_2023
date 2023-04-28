package com.youth4work.HSSC_2023.network.model.request;

import com.google.gson.annotations.SerializedName;

public class UserUpgrade {

    @SerializedName("userid")
    private Long userid;

    @SerializedName("Amount")
    private int Amount;

    @SerializedName("serviceid")
    private int serviceid;

    @SerializedName("transID")
    private String transID;

    @SerializedName("appname")
    private String appName;

    @SerializedName("CouponCode")
    private String CouponCode;

    @SerializedName("Actualamount")
    private String Actualamount;

    @SerializedName("AppVersionId")
    private String AppVersionId;

    @SerializedName("DomainName")
    private String DomainName;

    @SerializedName("TestCategoryIdUpgrade")
    private int TestCategoryIdUpgrade;

    @SerializedName("taxamount")
    private String taxamount;




    public UserUpgrade(Long userid, int amount, int serviceid, String transID, String appName, String couponCode, String actualamount, String appVersionId, String domainName, int testCategoryIdUpgrade, String taxamount) {
        this.userid = userid;
        this.Amount = amount;
        this.serviceid = serviceid;
        this.transID = transID;
        this.appName = appName;
        this.CouponCode = couponCode;
        this.Actualamount = actualamount;
        this.AppVersionId = appVersionId;
        this.DomainName = domainName;
        this.TestCategoryIdUpgrade = testCategoryIdUpgrade;
        this.taxamount = taxamount;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getTransID() {
        return transID;
    }

    public void setTransID(String transID) {
        this.transID = transID;
    }

    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    public String getActualamount() {
        return Actualamount;
    }

    public void setActualamount(String actualamount) {
        Actualamount = actualamount;
    }

    public String getAppVersionId() {
        return AppVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        AppVersionId = appVersionId;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }

    public int getTestCategoryIdUpgrade() {
        return TestCategoryIdUpgrade;
    }

    public void setTestCategoryIdUpgrade(int testCategoryIdUpgrade) {
        TestCategoryIdUpgrade = testCategoryIdUpgrade;
    }

    public String getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(String taxamount) {
        this.taxamount = taxamount;
    }
}
