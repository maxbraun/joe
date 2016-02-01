package com.github.maxbraun.test.joe;


import org.junit.Rule;
import org.junit.Test;

@WithBrowser(value = Browser.ANY)
public class ComparingAcceptanceTest {

    @Rule
    public WebDriverRule prod = new WebDriverRule();

    @Test
    public void testTest() throws Exception {
        prod.get("https://google.com");

    }
}
