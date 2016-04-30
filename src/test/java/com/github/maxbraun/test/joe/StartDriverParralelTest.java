package com.github.maxbraun.test.joe;

import net.oneandone.sushi.fs.Node;
import net.oneandone.sushi.fs.World;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@WithBrowser(Browser.ANY)
@RunWith(Parameterized.class)
public class StartDriverParralelTest {
    @Rule
    public WebDriverRule webDriver = new WebDriverRule(Browser.FIREFOX);

    private World world = World.createMinimal();

    @Parameterized.Parameters( name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {{ "google.com" }, { "facebook.com" }});
    }

    @Parameterized.Parameter
    public String url;

    @Before
    public void executeCommand() throws Exception {
        webDriver.get("https://" + url);

    }

    @Test
    public void check() throws Exception {
        checkDirectory();
    }

    public void checkLog() throws Exception {
        assertTrue(directory().join("1_afterNavigate.log").exists());
        assertTrue(directory().join("1_afterNavigate.log").isFile());
    }

    public void checkScreenshot() throws Exception {
        assertTrue(directory().join("1_afterNavigate.png").exists());
        assertTrue(directory().join("1_afterNavigate.png").isFile());
    }

    public void checkDirectory() throws Exception {
        assertTrue(directory().exists());
        assertTrue(directory().isDirectory());
    }

    private Node directory(){
        return world.guessProjectHome(this.getClass()).join("target", "test-screenshots",
                this.getClass().getSimpleName(),"check","" + url);
    }
}
