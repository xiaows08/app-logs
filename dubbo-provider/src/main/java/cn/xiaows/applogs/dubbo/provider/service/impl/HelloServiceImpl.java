package cn.xiaows.applogs.dubbo.provider.service.impl;

import cn.xiaows.applogs.dubbo.provider.dao.ApplogDao;
import cn.xiaows.dubbo.api.HelloService;
import cn.xiaows.dubbo.api.entity.AppLog;
import cn.xiaows.logs.common.AppLogger;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private ApplogDao applogDao;

    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public List<AppLogger> getloggers() {
        List<AppLog> appLoggers = applogDao.getall();

        return null;
    }
}
