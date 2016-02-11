package com.github.maxbraun.test.joe;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;


public class ProbingWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private static final Logger LOGGER = Logger.getLogger(ProbingWebDriverCreationStrategy.class.getName());

    @Override
    public Optional<? extends WebDriver> createWebDriver() {
        try {
            return Optional.of(new FirefoxDriver());
        } catch (Exception e) {
            LOGGER.log(Level.FINEST,"Failed to create FirefoxDriver", e);
        }
        try {
            return Optional.of(new ChromeDriver());
        } catch (Exception e) {
            LOGGER.log(Level.FINEST,"Failed to create ChromeDriver", e);
        }
        try {
            return Optional.of(new SafariDriver());
        } catch (Exception e) {
            LOGGER.log(Level.FINEST,"Failed to create SafariDriver", e);
        }

        try {
            return Optional.of(new InternetExplorerDriver());
        } catch (Exception e) {
            LOGGER.log(Level.FINEST,"Failed to create InternetExplorerDriver", e);
        }
        return Optional.of(new HtmlUnitDriver());

    }
}
