package com.github.maxbraun.test.joe;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.internal.Streams;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;

import java.util.*;

public class ActionLog {

    private String browserDetails;
    private String url;
    private Collection<Cookie> cookies;
    private Map<String, List<String>> logs = new HashMap<>();

    public static class Builder {
        public ActionLog build(WebDriver webDriver) {
            ActionLog actionLog = new ActionLog();
            actionLog.cookies = webDriver.manage().getCookies();
            actionLog.url = webDriver.getCurrentUrl();
            actionLog.browserDetails = webDriver.manage().toString();
            Logs logs = webDriver.manage().logs();

            for (String logType : logs.getAvailableLogTypes()) {
                List<String> logentries = new ArrayList<>();
                for (LogEntry logEntry : logs.get(logType).getAll()) {
                    logentries.add(logEntry.toString());
                }
                if (logentries.size() > 0) {
                    actionLog.logs.put(logType, logentries);
                }

            }


            return actionLog;
        }
    }
}
