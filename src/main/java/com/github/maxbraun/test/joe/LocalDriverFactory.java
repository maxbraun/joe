package com.github.maxbraun.test.joe;


import org.openqa.selenium.WebDriver;

import java.util.Optional;


public class LocalDriverFactory {
    public static WebDriver createDriver(WithBrowser annotation) {
        Optional<? extends WebDriver> webDriver;

        webDriver = new AnnotationWebDriverCreationStrategy(annotation).createWebDriver();

        if (webDriver.isPresent()) {
            return webDriver.get();
        }

        webDriver = new ProbingWebDriverCreationStrategy().createWebDriver();

        return webDriver.get();
    }
}
