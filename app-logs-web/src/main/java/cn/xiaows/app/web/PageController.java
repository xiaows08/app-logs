package cn.xiaows.app.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
