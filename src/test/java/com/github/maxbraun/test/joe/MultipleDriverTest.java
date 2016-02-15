package com.github.maxbraun.test.joe;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MultipleDriverTest {

    @Test
    public void TestFoo() throws Throwable {
        JUnitCore jUnitCore = new JUnitCore();

        Result result = jUnitCore.run(Request.aClass(MultipleDrivers.class).getRunner());
        assertEquals(1, result.getFailureCount());
        Failure failure = result.getFailures().get(0);
        assertEquals(IllegalStateException.class, failure.getException().getClass());
        assertEquals("webDriver is not null", failure.getException().getMessage());
    }


    public static class MultipleDrivers {
        @Rule
        public WebDriverRule webDriver1 = new WebDriverRule();
        @Rule
        public WebDriverRule webDriver2 = new WebDriverRule();

        @Test
        public void test() throws Exception {
            webDriver1.get("https://google.com");
            webDriver2.get("https://google.com");
        }
    }
}
