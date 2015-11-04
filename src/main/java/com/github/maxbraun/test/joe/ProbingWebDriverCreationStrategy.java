package com.github.maxbraun.test.joe;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Optional;


public class ProbingWebDriverCreationStrategy implements WebDriverCreationStrategy {
    @Override
    public Optional<? extends WebDriver> createWebDriver() {
        try {
            return Optional.of(new FirefoxDriver());
        } catch (Exception e) {
            //ignore
        }
        try {
            return Optional.of(new ChromeDriver());
        } catch (Exception e) {
            //ignore
        }
        try {
            return Optional.of(new SafariDriver());
        } catch (Exception e) {
            //ignore
        }

        try {
            return Optional.of(new InternetExplorerDriver());
        } catch (Exception e) {
            //ignore
        }
        return Optional.of(new HtmlUnitDriver());

    }
}
