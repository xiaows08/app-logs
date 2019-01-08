package cn.xiaows.dubbo.service;

import cn.xiaows.logs.common.AppLogger;

import java.util.List;

/**
 *
 * @author: xiaows
 * @create: 2019-01-08 14:57
 * @version: v1.0
 */
public interface HelloService {

    public String sayHello(String name);

    public List<AppLogger> getloggers();
}
