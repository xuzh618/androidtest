package com.xuzh.androidtest.share;

public class ShareEntity {

    private int appIcon;
    private String appName;

    public ShareEntity() {
        this.appIcon = -1;
        this.appName = "";
    }

    public ShareEntity(int appIcon, String appName) {
        this.appIcon = appIcon;
        this.appName = appName;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppName() {
        return appName;
    }

    public void setName(String appName) {
        this.appName = appName;
    }

}
