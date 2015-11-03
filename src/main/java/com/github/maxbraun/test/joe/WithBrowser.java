package com.github.maxbraun.test.joe;

import org.openqa.selenium.remote.BrowserType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface WithBrowser {

     Browser value();
}
