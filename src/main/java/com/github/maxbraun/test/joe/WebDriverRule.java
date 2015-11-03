package com.github.maxbraun.test.joe;

import net.sf.cglib.core.Local;
import org.junit.rules.ExternalResource;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.Set;


public class WebDriverRule extends ExternalResource implements WebDriver, JavascriptExecutor {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private Description description;


    @Override
    public Statement apply(Statement base, Description description) {
        this.description = description;
        return super.apply(base, description);
    }


    @Override
    protected void before() throws Throwable {
        super.before();

        if (webDriver() != null) {
            throw new IllegalStateException("webDriver is not null");
        }
        startWebDriver();
    }

    private void startWebDriver() {
        driver.set(LocalDriverFactory.createDriver(description.getTestClass().getAnnotation(WithBrowser.class)));
    }

    private WebDriver webDriver() {
        return driver.get();
    }

    @Override
    protected void after() {
        super.after();

        stopWebDriver();
    }

    private void stopWebDriver() {
        if(webDriver() != null) {
            webDriver().quit();
        }
        driver.set(null );
    }


    @Override
    public Object executeScript(String s, Object... objects) {
        return ((JavascriptExecutor) webDriver()).executeScript(s, objects);
    }

    @Override
    public Object executeAsyncScript(String s, Object... objects) {
        return ((JavascriptExecutor) webDriver()).executeAsyncScript(s, objects);
    }

    @Override
    public void get(String s) {
        webDriver().get(s);
    }

    @Override
    public String getCurrentUrl() {
        return webDriver().getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return webDriver().getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return webDriver().findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return webDriver().findElement(by);
    }

    @Override
    public String getPageSource() {
        return webDriver().getPageSource();
    }

    @Override
    public void close() {
        webDriver().close();
    }

    @Override
    public void quit() {
        webDriver().quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver().getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return webDriver().getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return webDriver().switchTo();
    }

    @Override
    public Navigation navigate() {
        return webDriver().navigate();
    }

    @Override
    public Options manage() {
        return webDriver().manage();
    }
}
