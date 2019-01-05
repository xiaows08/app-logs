package cn.xiaows.dubbo.service;

import cn.xiaows.logs.common.AppLogger;

import java.util.List;

public interface HelloService {

    public String sayHello(String name);

    public List<AppLogger> getloggers();
}
