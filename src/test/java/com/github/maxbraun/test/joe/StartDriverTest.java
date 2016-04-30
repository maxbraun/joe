package com.github.maxbraun.test.joe;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.Parameterized;

@WithBrowser(Browser.ANY)
public class StartDriverTest {
    @Rule
    public WebDriverRule  webDriver = new WebDriverRule();

    @Test
    public void TestFoo() throws Exception {
        webDriver.get("https://google.de");

    }
}
