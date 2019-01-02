package cn.xiaows.logs.common;

import java.util.List;

public class LoggerEntity {
    private List<Logger> loggers;

    @Override
    public String toString() {
        return "LoggerEntity{" +
                "loggers=" + loggers +
                '}';
    }

    public List<Logger> getLoggers() {
        return loggers;
    }

    public void setLoggers(List<Logger> loggers) {
        this.loggers = loggers;
    }
}
