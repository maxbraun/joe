package com.github.maxbraun.test.joe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class LoggingWebDriverEventListener extends AbstractWebDriverEventListener {
    private final Description description;
    private final LogDirectory logDirectory;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public LoggingWebDriverEventListener(Description description, LogDirectory logDirectory) {
        this.description = description;
        this.logDirectory = logDirectory;
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
        log(driver, "afterNavigate");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        super.beforeClickOn(element, driver);
        log(driver, "beforeClick");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        super.afterClickOn(element, driver);
        log(driver, "afterClick");
    }

    protected void log(WebDriver driver, String action) {
        while (driver instanceof EventFiringWebDriver) {
            EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) driver;
            driver = eventFiringWebDriver.getWrappedDriver();
        }
        ActionLog actionLog = new ActionLog.Builder().build(driver);
        logDirectory.save(gson.toJson(actionLog), action, description.getMethodName());
    }


}
