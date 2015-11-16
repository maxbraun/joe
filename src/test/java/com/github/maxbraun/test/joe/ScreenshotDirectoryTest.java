package com.github.maxbraun.test.joe;

import net.oneandone.sushi.fs.Node;
import net.oneandone.sushi.fs.World;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScreenshotDirectoryTest {

    @Test
    public void testDirectoryNaming() throws Exception {
        ScreenshotDirectory screenshotDirectory = new ScreenshotDirectory(this.getClass(), new World());
        Node node = screenshotDirectory.methodDirectory("method[foo|bar]");
        assertEquals("bar", node.getName());
        assertEquals("foo", node.getParent().getName());
        assertEquals("method", node.getParent().getParent().getName());
    }
}
