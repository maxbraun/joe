package com.github.maxbraun.test.joe;

import net.oneandone.sushi.fs.Node;
import net.oneandone.sushi.fs.World;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LogDirectoryTest {

    @Test
    public void testDirectoryNaming() throws Exception {
        LogDirectory logDirectory = new LogDirectory(this.getClass(), World.createMinimal());
        Node node = logDirectory.methodDirectory("method[foo|bar]");
        assertEquals("bar", node.getName());
        assertEquals("foo", node.getParent().getName());
        assertEquals("method", node.getParent().getParent().getName());
    }
}
