package com.github.maxbraun.test.joe;

import org.openqa.selenium.WebDriver;

import com.thoughtworks.selenium.SeleniumException;


public class SpecificWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private final Browser browser;

    public SpecificWebDriverCreationStrategy(Browser browser) {
        this.browser = browser;
    }

    @Override
    public WebDriver createWebDriver() {
        if (null == browser || null == browser.getClazzUrl()) {
            throw new SeleniumException("Could not start browser. Browser or value was null.");
        }
        try {
            return browser.getClazz().newInstance();
        } catch (ClassNotFoundException e){
            throw new SeleniumException(browser + " is not in your classpath. To fix this problem, add the driver to your dependency management", e);
        } catch (Exception e) {
            throw new SeleniumException(e);
        }
    }
}
