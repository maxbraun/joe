package com.github.maxbraun.test.joe;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.SeleniumException;


public class ProbingWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private static final Logger LOGGER = Logger.getLogger(ProbingWebDriverCreationStrategy.class.getName());

    @Override
    public WebDriver createWebDriver() {
        for (Browser browser : Browser.values()) {
            if (null == browser || null == browser.getClazzUrl()) {
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
