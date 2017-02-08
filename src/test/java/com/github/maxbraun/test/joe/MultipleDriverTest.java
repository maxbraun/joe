package com.github.maxbraun.test.joe;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class MultipleDriverTest {

    @Rule
    public WebDriverRule webDriver1 = new WebDriverRule(false, true);
    @Rule
    public WebDriverRule webDriver2 = new WebDriverRule();

    @Test
    public void test() throws Exception {
        webDriver1.get("https://google.com");
        webDriver2.get("https://facebook.com");
    }

    @Test
    public void webdriver2MustBeAFireFox() throws Exception {
        webDriver2.getTitle();

    }
}
