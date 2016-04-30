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
    public Optional<? extends WebDriver> createWebDriver() {
        if (null == browser || null == browser.getClazz()) {
            return Optional.empty();
        }
        try {
            return Optional.of(browser.getClazz().newInstance());
        } catch (Exception e) {
            throw new SeleniumException(e);
        }
    }
}
