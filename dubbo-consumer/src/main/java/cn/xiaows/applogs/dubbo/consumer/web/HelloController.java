package cn.xiaows.applogs.dubbo.consumer.web;

import cn.xiaows.dubbo.service.HelloService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author: xiaows
 * @create: 2019-01-08 14:57
 * @version: v1.0
 */
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
