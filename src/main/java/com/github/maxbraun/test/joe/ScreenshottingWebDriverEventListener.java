package com.github.maxbraun.test.joe;

import net.oneandone.sushi.fs.World;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ScreenshottingWebDriverEventListener extends AbstractWebDriverEventListener {
    private final Description description;
    private final ScreenshotDirectory screenshotDirectory;

    public ScreenshottingWebDriverEventListener(Description description) {
        this.description = description;
        this.screenshotDirectory = new ScreenshotDirectory(description.getTestClass(), new World());
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
        logAndTakeSnapShot(driver, "afterNavigate");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        super.beforeClickOn(element, driver);
        logAndTakeSnapShot(driver, "beforeClick");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        super.afterClickOn(element, driver);
        logAndTakeSnapShot(driver, "afterClick");
    }

    protected void logAndTakeSnapShot(WebDriver driver, String action) {
        if (driver instanceof EventFiringWebDriver) {
            EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) driver;
            driver = eventFiringWebDriver.getWrappedDriver();
        }
        takeScreenshot(driver, action);
    }

    private void takeScreenshot(WebDriver webDriver, String action) {
        if (webDriver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshotWebDriver = (TakesScreenshot) webDriver;
            byte[] screenshot = takesScreenshotWebDriver.getScreenshotAs(OutputType.BYTES);
            screenshotDirectory.save(screenshot, action, description.getMethodName());
        }
    }
}
