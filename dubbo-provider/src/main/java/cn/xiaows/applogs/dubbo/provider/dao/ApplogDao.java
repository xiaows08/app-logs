package cn.xiaows.applogs.dubbo.provider.dao;


import cn.xiaows.dubbo.api.entity.AppLog;

import java.util.List;

public interface ApplogDao {

    // @Select("SELECT * FROM ext_app_log")
    List<AppLog> getall();
}
