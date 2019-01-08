package cn.xiaows.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 *
 * @author: xiaows
 * @create: 2019-01-08 14:56
 * @version: v1.0
 */
@Slf4j
@Controller
public class PageController {

    @GetMapping("/log")
    @ResponseBody
    public String log() {
        log.debug("debug");
        log.warn("warn");
        log.error("error");
        return "hello";
    }

    @GetMapping("/index")
    public String indexPage() {
        log.info("indexPage");
        return "index";
    }
}
