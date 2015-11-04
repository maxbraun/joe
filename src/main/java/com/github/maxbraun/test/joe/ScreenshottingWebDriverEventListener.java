package com.github.maxbraun.test.joe;

import net.oneandone.sushi.fs.Node;
import net.oneandone.sushi.fs.World;
import net.oneandone.sushi.fs.file.FileNode;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;

public class ScreenshottingWebDriverEventListener extends AbstractWebDriverEventListener {
    private static final World world = new World();
    private final Description description;
    private final FileNode reportDir = world.guessProjectHome(this.getClass()).join("target", "test-screenshots");

    public ScreenshottingWebDriverEventListener(Description description) {
        this.description = description;
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        super.afterNavigateTo(url, driver);
        logAndTakeSnapShot(driver);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        super.onException(throwable, driver);
        logAndTakeSnapShot(driver);
    }

    protected void logAndTakeSnapShot(WebDriver driver) {
        if (driver instanceof EventFiringWebDriver) {
            EventFiringWebDriver eventFiringWebDriver = (EventFiringWebDriver) driver;
            driver = eventFiringWebDriver.getWrappedDriver();
        }
        takeScreenshot(driver);
    }

    private void takeScreenshot(WebDriver webDriver) {
        if (webDriver instanceof TakesScreenshot) {
            TakesScreenshot takesScreenshotWebDriver = (TakesScreenshot) webDriver;
            byte[] screenshot = takesScreenshotWebDriver.getScreenshotAs(OutputType.BYTES);
            try {
                Node file = testClassDirectory().join(System.currentTimeMillis() + ".png");
                file.writeBytes(screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private Node testMethodsDirectory() throws IOException {
        return testClassDirectory().join(description.getMethodName()).mkdirsOpt();

    }

    private Node testClassDirectory() throws IOException {
        return reportDir.join(description.getTestClass().getSimpleName()).mkdirsOpt();
    }


}
