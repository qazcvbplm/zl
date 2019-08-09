package com.dingdian.order.bean;

import com.dingdian.order.utils.DeviceUtil;

public class VersionBean {
    private String version;
    private String androidUrl;
    private String iosUrl;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }

    public boolean checkUpdate(){
        //app版本和服务器版本对比，小于则更新，
        int needUpdate = DeviceUtil.INSTANCE.compareVersion(DeviceUtil.INSTANCE.getCurrentVersion(),version);
        if (needUpdate >= 0) return false;
        return true;
    }
}

