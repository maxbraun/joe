package com.github.maxbraun.test.joe;

import com.thoughtworks.selenium.SeleniumException;
import org.openqa.selenium.WebDriver;

import java.util.Optional;


public class SpecificWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private final Browser browser;

    public SpecificWebDriverCreationStrategy(Browser browser) {
        this.browser = browser;
    }

    @Override
    public WebDriver createWebDriver() {
        if (null == browser || null == browser.getClazz()) {
            throw new SeleniumException("Could not start browser. Browser or value was null.");
        }
        try {
            return browser.getClazz().newInstance();
        } catch (Exception e) {
            throw new SeleniumException(e);
        }
    }
}
