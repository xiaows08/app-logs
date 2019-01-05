package cn.xiaows.applogs.dubbo.provider.service.impl;

import cn.xiaows.dubbo.service.HelloService;
import cn.xiaows.logs.common.AppLogger;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service
public class HelloServiceImpl implements HelloService {


    @Override
    public String sayHello(String name) {
        return "hello " + name;
    }

    @Override
    public List<AppLogger> getloggers() {

        return null;
    }
}
