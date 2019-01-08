package cn.xiaows.logs.common;

import java.util.Date;

/**
 *
 *
 * @author: xiaows
 * @create: 2019-01-08 14:47
 * @version: v1.0
 */
public class UserActiveLogger extends BaseLogger {
    private String username;
    private String userId;
    private Date date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
