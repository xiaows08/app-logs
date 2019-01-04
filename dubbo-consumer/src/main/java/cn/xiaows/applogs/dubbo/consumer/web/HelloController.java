package cn.xiaows.applogs.dubbo.consumer.web;

import cn.xiaows.dubbo.api.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Reference
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        String hello = helloService.sayHello("awx");
        return hello;
    }
}
