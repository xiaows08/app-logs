package cn.xiaows.logs.common;

/**
 *
 *
 * @author: xiaows
 * @create: 2019-01-08 14:47
 * @version: v1.0
 */
public class UserLoginLogger extends BaseLogger{
    private String orgName;
    private String iamOpenId;
    private String username;
    private String mobile;
    private String workno;
    private String userEmail;
    private String emailList;
    private String iamWechatUnionId;
    private String iamWechatOpenId;

    private String id;// 用于反序列化es中的id
    private String loginTime;//时间格式:yyyy-MM-dd HH:mm:ss
    private String systemId;//所属那个系统

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIamOpenId() {
        return iamOpenId;
    }

    public void setIamOpenId(String iamOpenId) {
        this.iamOpenId = iamOpenId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getEmailList() {
        return emailList;
    }

    public void setEmailList(String emailList) {
        this.emailList = emailList;
    }

    public String getIamWechatUnionId() {
        return iamWechatUnionId;
    }

    public void setIamWechatUnionId(String iamWechatUnionId) {
        this.iamWechatUnionId = iamWechatUnionId;
    }

    public String getIamWechatOpenId() {
        return iamWechatOpenId;
    }

    public void setIamWechatOpenId(String iamWechatOpenId) {
        this.iamWechatOpenId = iamWechatOpenId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }
}
