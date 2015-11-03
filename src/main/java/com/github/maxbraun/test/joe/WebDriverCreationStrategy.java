package com.github.maxbraun.test.joe;

import org.openqa.selenium.WebDriver;

import java.util.Optional;


public interface WebDriverCreationStrategy {
    Optional<? extends WebDriver> createWebDriver();
}
