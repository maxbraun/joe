package com.github.maxbraun.test.joe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;

public class ActionLog {
    private static final Logger LOGGER = Logger.getLogger("Joe");

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

        public static ActionLog fail(UnsupportedCommandException e) {
            LOGGER.log(Level.WARNING, "action log not supported. falling back", e);
            ActionLog actionLog = new ActionLog();
            actionLog.browserDetails = "not supported" +  e.getMessage();
            return actionLog;
        }
    }

    public String getBrowserDetails() {
        return browserDetails;
    }
    public String getUrl() {
        return url;
    }
    public Collection<Cookie> getCookies() {
        return cookies;
    }
    public Map<String, List<String>> getLogs() {
        return logs;
    }
}
