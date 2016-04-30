package com.github.maxbraun.test.joe;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.thoughtworks.selenium.SeleniumException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.safari.SafariDriver;


public class ProbingWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private static final Logger LOGGER = Logger.getLogger(ProbingWebDriverCreationStrategy.class.getName());

    @Override
    public WebDriver createWebDriver() {
        for (Browser browser : Browser.values()) {
            if (null == browser || null == browser.getClazz()) {
                continue;
            }
            try {
                return browser.getClazz().newInstance();
            } catch (Throwable e) {
                LOGGER.log(Level.FINEST,"Failed to create " + browser.name(), e);

            }
        }

        throw new SeleniumException("Could not start any browser. Maybe a dependency to the driver is missing");

    }
}
