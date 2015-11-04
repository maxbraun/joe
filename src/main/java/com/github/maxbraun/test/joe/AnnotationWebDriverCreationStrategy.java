package com.github.maxbraun.test.joe;

import com.thoughtworks.selenium.SeleniumException;
import org.openqa.selenium.WebDriver;

import java.util.Optional;


public class AnnotationWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private final WithBrowser annotation;

    public AnnotationWebDriverCreationStrategy(WithBrowser annotation) {
        this.annotation = annotation;
    }

    @Override
    public Optional<? extends WebDriver> createWebDriver() {
        if (null == annotation || null == annotation.value() || null == annotation.value().getClazz()) {
            return Optional.empty();
        }
        try {
            return Optional.of(annotation.value().getClazz().newInstance());
        } catch (Exception e) {
            throw new SeleniumException(e);
        }
    }
}
