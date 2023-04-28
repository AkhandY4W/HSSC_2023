package com.youth4work.HSSC_2023.network.model.request;

public class GcmRegister {

    private long userid;
    private String DeviceId;
    private String GcmTokenID;
    private String emailid;
    private String AppName;

    public GcmRegister(long userId, String deviceId, String gcmToken, String emailID,String appName) {
        this.userid = userId;
        this.DeviceId = deviceId;
        this.GcmTokenID = gcmToken;
        this.emailid = emailID;
        this.AppName=appName;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getDeviceid() {
        return DeviceId;
    }

    public void setDeviceid(String deviceid) {
        DeviceId = deviceid;
    }

    public String getGcmTokenID() {
        return GcmTokenID;
    }

    public void setGcmTokenID(String gcmTokenID) {
        GcmTokenID = gcmTokenID;
    }

    public String getEmailID() {
        return emailid;
    }

    public void setEmailID(String emailID) {
        emailid = emailID;
    }
}
