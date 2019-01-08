package cn.xiaows.logs.collect.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 *
 * @author: xiaows
 * @create: 2019-01-08 14:47
 * @version: v1.0
 */
@Slf4j
@Controller
public class PageController {

    @GetMapping("/")
    public String index() {
        return "/";
    }

}
