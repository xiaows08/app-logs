package cn.xiaows.logs.common;

public class AppLogger extends BaseLogger {
    private String tenantId;
    private String appVersion;
    private String appChannel;
    private String appPlatFrom;

    private String country;
    private String province;
    private String network;
    private String carrier;
    private String deviceStyle;

    private String screenSize;
    private String osType;
    private String brand;
    private String eventID;

    public String getTenantId() {
        return tenantId;
    }

    public AppLogger setTenantId(String tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public AppLogger setAppVersion(String appVersion) {
        this.appVersion = appVersion;
        return this;
    }

    public String getAppChannel() {
        return appChannel;
    }

    public AppLogger setAppChannel(String appChannel) {
        this.appChannel = appChannel;
        return this;
    }

    public String getAppPlatFrom() {
        return appPlatFrom;
    }

    public AppLogger setAppPlatFrom(String appPlatFrom) {
        this.appPlatFrom = appPlatFrom;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AppLogger setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public AppLogger setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getNetwork() {
        return network;
    }

    public AppLogger setNetwork(String network) {
        this.network = network;
        return this;
    }

    public String getCarrier() {
        return carrier;
    }

    public AppLogger setCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    public String getDeviceStyle() {
        return deviceStyle;
    }

    public AppLogger setDeviceStyle(String deviceStyle) {
        this.deviceStyle = deviceStyle;
        return this;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public AppLogger setScreenSize(String screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    public String getOsType() {
        return osType;
    }

    public AppLogger setOsType(String osType) {
        this.osType = osType;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public AppLogger setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getEventID() {
        return eventID;
    }

    public AppLogger setEventID(String eventID) {
        this.eventID = eventID;
        return this;
    }

    @Override
    public String toString() {
        return "AppLogger{" +
                "tenantId='" + tenantId + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", appChannel='" + appChannel + '\'' +
                ", appPlatFrom='" + appPlatFrom + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", network='" + network + '\'' +
                ", carrier='" + carrier + '\'' +
                ", deviceStyle='" + deviceStyle + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", osType='" + osType + '\'' +
                ", brand='" + brand + '\'' +
                ", eventID='" + eventID + '\'' +
                '}';
    }
}
