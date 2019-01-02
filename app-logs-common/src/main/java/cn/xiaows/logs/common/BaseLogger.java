package cn.xiaows.logs.common;

import java.io.Serializable;

public abstract class BaseLogger implements Serializable, Logger {
    private String remoteAddr;
    private String currentDate;

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}
