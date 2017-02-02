package com.github.maxbraun.test.joe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * checks if the classes defined in {@link Browser} are available in current class path
 */
public class BrowserClassExistenceTest {

    @Test
    public void createInstance() throws Exception {
        for (Browser browser : Browser.values()) {
            if (browser.getClazzUrl() == null) {
                continue;
            }
            Assert.assertNotNull(browser.getClazz());
        }


    }
}
