package com.github.maxbraun.test.joe;

import org.openqa.selenium.WebDriver;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Created by max on 04/11/15.
 */
public class AnnotationWebDriverCreationStrategy implements WebDriverCreationStrategy {
    private final WithBrowser annotation;

    public AnnotationWebDriverCreationStrategy(WithBrowser annotation) {
        this.annotation = annotation;
    }

    @Override
    public Optional<? extends WebDriver> createWebDriver() {
        if (annotation == null) {
            return Optional.empty();
        }
        try {
            annotation.value().getClazz().newInstance();
        } catch (Exception e) {
            System.out.println(annotation.value() + " could not be initialised. " + e.getMessage());
        }
        return Optional.empty();
    }
}
