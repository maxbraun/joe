package com.github.maxbraun.test.joe;


import org.openqa.selenium.WebDriver;

import java.util.Optional;


public class LocalDriverFactory {
    public static WebDriver createDriver(Browser annotation) {
        Optional<? extends WebDriver> webDriver;

        webDriver = new SpecificWebDriverCreationStrategy(annotation).createWebDriver();

        if (webDriver.isPresent()) {
            return webDriver.get();
        }

        webDriver = new ProbingWebDriverCreationStrategy().createWebDriver();

        return webDriver.get();
    }
}
