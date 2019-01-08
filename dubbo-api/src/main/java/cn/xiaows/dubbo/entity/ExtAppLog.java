package cn.xiaows.dubbo.entity;

import java.io.Serializable;

/**
 *
 * @author: xiaows
 * @create: 2019-01-08 14:57
 * @version: v1.0
 */
public class ExtAppLog implements Serializable {
    private String appchannel;
    private String appplatfrom;
    private String appversion;
    private String brand;
    private String carrier;
    private String country;
    private String currentdate;
    private String devicestyle;
    private String eventid;
    private String network;
    private String ostype;
    private String province;
    private String remoteaddr;
    private String screensize;
    private String tenantid;
    private String ym;
    private String day;
    private String hm;

    @Override
    public String toString() {
        return "ExtAppLog{" +
                "appchannel='" + appchannel + '\'' +
                ", appplatfrom='" + appplatfrom + '\'' +
                ", appversion='" + appversion + '\'' +
                ", brand='" + brand + '\'' +
                ", carrier='" + carrier + '\'' +
                ", country='" + country + '\'' +
                ", currentdate='" + currentdate + '\'' +
                ", devicestyle='" + devicestyle + '\'' +
                ", eventid='" + eventid + '\'' +
                ", network='" + network + '\'' +
                ", ostype='" + ostype + '\'' +
                ", province='" + province + '\'' +
                ", remoteaddr='" + remoteaddr + '\'' +
                ", screensize='" + screensize + '\'' +
                ", tenantid='" + tenantid + '\'' +
                ", ym='" + ym + '\'' +
                ", day='" + day + '\'' +
                ", hm='" + hm + '\'' +
                '}';
    }

    public String getAppchannel() {
        return appchannel;
    }

    public void setAppchannel(String appchannel) {
        this.appchannel = appchannel;
    }

    public String getAppplatfrom() {
        return appplatfrom;
    }

    public void setAppplatfrom(String appplatfrom) {
        this.appplatfrom = appplatfrom;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getDevicestyle() {
        return devicestyle;
    }

    public void setDevicestyle(String devicestyle) {
        this.devicestyle = devicestyle;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getOstype() {
        return ostype;
    }

    public void setOstype(String ostype) {
        this.ostype = ostype;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRemoteaddr() {
        return remoteaddr;
    }

    public void setRemoteaddr(String remoteaddr) {
        this.remoteaddr = remoteaddr;
    }

    public String getScreensize() {
        return screensize;
    }

    public void setScreensize(String screensize) {
        this.screensize = screensize;
    }

    public String getTenantid() {
        return tenantid;
    }

    public void setTenantid(String tenantid) {
        this.tenantid = tenantid;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHm() {
        return hm;
    }

    public void setHm(String hm) {
        this.hm = hm;
    }
}
