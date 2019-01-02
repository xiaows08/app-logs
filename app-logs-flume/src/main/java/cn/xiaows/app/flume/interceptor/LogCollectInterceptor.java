package cn.xiaows.app.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

import static org.apache.flume.interceptor.HostInterceptor.Constants.PRESERVE;
import static org.apache.flume.interceptor.HostInterceptor.Constants.PRESERVE_DFLT;

/**
 * 自定义拦截器
 *  从 kafka 多个 topic 中的数据解析出 目标存储路径
 */
public class LogCollectInterceptor implements Interceptor {
    private final boolean preserveExisting;

    public LogCollectInterceptor(boolean preserveExisting) {
        this.preserveExisting = preserveExisting;
    }

    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        Map<String, String> headers = event.getHeaders();
        byte[] json = event.getBody();
        String jsonStr = new String(json);
        // 从数据中获取 kafka 分区 到 HDFS 目录
        String logType = "";
        if (jsonStr.contains("eventId")) {
            logType = "event";
        } else if (jsonStr.contains("pageId")) {
            logType = "page";
        } else if (jsonStr.contains("error")) {
            logType = "error";
        } else if (jsonStr.contains("network")) {
            logType = "startup";
        }
        headers.put("logType", logType);
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            intercept(event);
        }
        return events;
    }

    @Override
    public void close() {

    }

    public static class Builder implements Interceptor.Builder {
        private boolean preserveExisting = PRESERVE_DFLT;

        @Override
        public Interceptor build() {
            return new LogCollectInterceptor(preserveExisting);
        }

        @Override
        public void configure(Context context) {
            preserveExisting = context.getBoolean(PRESERVE, PRESERVE_DFLT);
        }
    }

}
