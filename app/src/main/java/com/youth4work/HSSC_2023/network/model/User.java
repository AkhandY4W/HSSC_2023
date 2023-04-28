package com.youth4work.HSSC_2023.network.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("contactNo")
    private String ContactNo;
    @SerializedName("emailID")
    private String EmailID;
    @SerializedName("imgUrl")
    private String ImgUrl;
    @SerializedName("name")
    private String Name;
    @SerializedName("prepPlan")
    private String PrepPlan;
    @SerializedName("prepPlanID")
    private int PrepPlanID;
    @SerializedName("userId")
    private long UserId;
    @SerializedName("userName")
    private String UserName;
    @SerializedName("userStatus")
    private String UserStatus;
    @SerializedName("userType")
    private String UserType;
    @SerializedName("planEndDate")
    private String planEndDate;
    @SerializedName("planStartDate")
    private String planStartDate;
    @SerializedName("selectedCatID")
    private int selectedCatID;
    @SerializedName("isMobileVerified")
    private boolean isMobileVerified;
    public boolean isMobileVerified() {
        return isMobileVerified;
    }

    public void setMobileVerified(boolean mobileVerified) {
        isMobileVerified = mobileVerified;
    }


    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String ContactNo) {
        this.ContactNo = ContactNo;
    }

    public String getEmailID() {
        return EmailID;
    }

    public void setEmailID(String EmailID) {
        this.EmailID = EmailID;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrepPlan() {
        return PrepPlan;
    }

    public void setPrepPlan(String PrepPlan) {
        this.PrepPlan = PrepPlan;
    }

    public int getPrepPlanID() {
        return PrepPlanID;
    }

    public void setPrepPlanID(int PrepPlanID) {
        this.PrepPlanID = PrepPlanID;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserStatus() {
        return UserStatus;
    }

    public void setUserStatus(String UserStatus) {
        this.UserStatus = UserStatus;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public String getPlanEndDate() {
        planEndDate = planEndDate.replace("/Date(", "");
        planEndDate = planEndDate.replace("+0530)/", "");

        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    public String getPlanStartDate() {
        planStartDate = planStartDate.replace("/Date(", "");
        planStartDate = planStartDate.replace("+0530)/", "");

        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public int getSelectedCatID() {
        return selectedCatID;
    }

    public void setSelectedCatID(int selectedCatID) {
        this.selectedCatID = selectedCatID;
    }
}
