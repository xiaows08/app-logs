package cn.xiaows.logs.collect.web;

import cn.xiaows.logs.collect.util.KafkaUtils;
import cn.xiaows.logs.collect.util.TopicConstant;
import cn.xiaows.logs.common.AppLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 *
 * @author: xiaows
 * @create: 2019-01-08 14:47
 * @version: v1.0
 */
@Slf4j
@Controller
public class CollectLoggerController {

    @PostMapping("collect")
    @ResponseBody
    public String collectLoginLogger(HttpServletRequest request, @RequestBody List<AppLogger> loggers) {
        log.info("--> {}", loggers);
        String addr = request.getRemoteAddr();
        long time = System.currentTimeMillis();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);

        for (AppLogger logger : loggers) {
            if (logger instanceof AppLogger) {
                AppLogger appLogger = (AppLogger) logger;
                appLogger.setRemoteAddr(addr);
                String currentDate = appLogger.getCurrentDate();
                if (currentDate == null) {
                    appLogger.setCurrentDate(currentTime);
                }
            }
        }
        KafkaUtils.sendLog(TopicConstant.TOPIC_APP_LOG, loggers);
        return "success";
    }
}
