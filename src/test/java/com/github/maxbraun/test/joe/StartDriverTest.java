package com.github.maxbraun.test.joe;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

@WithBrowser(Browser.ANY)
public class StartDriverTest {
    @Rule
    public WebDriverRule  webDriver = new WebDriverRule(false, false);


    @Test
    public void TestFoo() throws Exception {
        webDriver.get("https://google.de");

    }
}
