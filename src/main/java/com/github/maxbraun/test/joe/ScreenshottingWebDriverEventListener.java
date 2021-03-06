package com.github.maxbraun.test.joe;

import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ScreenshottingWebDriverEventListener extends AbstractWebDriverEventListener {
    private final Description description;
    private final LogDirectory logDirectory;

    public ScreenshottingWebDriverEventListener(Description description, LogDirectory logDirectory) {
        this.description = description;
        this.logDirectory = logDirectory;
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
        takeScreenshot(driver, "afterNavigate");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        super.beforeClickOn(element, driver);
        takeScreenshot(driver, "beforeClick");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        super.afterClickOn(element, driver);
        takeScreenshot(driver, "afterClick");
    }

    protected void takeScreenshot(WebDriver driver, String action) {
        while (driver instanceof EventFiringWebDriver) {
            EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) driver;
            driver = eventFiringWebDriver.getWrappedDriver();
        }
        TakesScreenshot takesScreenshotWebDriver = (TakesScreenshot) driver;
        byte[] screenshot = takesScreenshotWebDriver.getScreenshotAs(OutputType.BYTES);
        logDirectory.save(screenshot, action, description.getMethodName());
    }

}
