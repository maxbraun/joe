package com.github.maxbraun.test.joe;


import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;

public enum Browser {
    ANY(null),
    FIREFOX(FirefoxDriver.class),
    GOOGLECHROME(ChromeDriver.class),
    SAFARI(SafariDriver.class),
    OPERA(OperaDriver.class),
    EDGE(EdgeDriver.class),
    IEXPLORE(InternetExplorerDriver.class),
    CHROME(ChromeDriver.class),
    HTMLUNIT(HtmlUnitDriver.class),
    PHANTOMJS(PhantomJSDriver.class),
    JBROWSER(JBrowserDriver.class);

    private final Class<? extends WebDriver> clazz;

    Browser(Class<? extends WebDriver> driverClass) {
        this.clazz = driverClass;
    }

    public Class<? extends WebDriver> getClazz() {
        return clazz;
    }
}
