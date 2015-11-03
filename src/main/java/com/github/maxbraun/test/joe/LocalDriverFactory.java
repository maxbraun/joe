package com.github.maxbraun.test.joe;


import org.openqa.selenium.WebDriver;

import java.util.Optional;

/**
 * Simple Factory nearing to the next local browser
 */
public class LocalDriverFactory {
    public static WebDriver createDriver(WithBrowser annotation) {
        Optional<? extends WebDriver> webDriver;

        webDriver = new AnnotationWebDriverCreationStrategy(annotation).createWebDriver();

        if (webDriver.isPresent()) {
            return webDriver.get();
        }

        System.out.println("falling back to a browser which is installed");
        webDriver = new ProbingWebDriverCreationStrategy().createWebDriver();

        return webDriver.get();
    }
}
