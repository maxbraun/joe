package com.github.maxbraun.test.joe;


import org.openqa.selenium.WebDriver;
public enum Browser {
    ANY(null),
    FIREFOX("org.openqa.selenium.firefox.FirefoxDriver"),
    GOOGLECHROME("org.openqa.selenium.chrome.ChromeDriver"),
    SAFARI("org.openqa.selenium.safari.SafariDriver"),
    OPERA("org.openqa.selenium.opera.OperaDriver"),
    EDGE("org.openqa.selenium.edge.EdgeDriver"),
    IEXPLORE("org.openqa.selenium.ie.InternetExplorerDriver"),
    CHROME("org.openqa.selenium.chrome.ChromeDriver"),
    HTMLUNIT("org.openqa.selenium.htmlunit.HtmlUnitDriver"),
    /**
     * PhantomJs Webdriver implemetation is far behind selenium 3
     * https://github.com/detro/ghostdriver/commit/f976007a431e634a3ca981eea743a2686ebed38e
     */
    @Deprecated
    PHANTOMJS("org.openqa.selenium.phantomjs.PhantomJSDriver"),
    JBROWSER("com.machinepublishers.jbrowserdriver.JBrowserDriver");

    private final String clazz;

    Browser(String driverClass) {
        this.clazz = driverClass;
    }

    public Class<? extends WebDriver> getClazz() throws ClassNotFoundException {
        return (Class<? extends WebDriver>) this.getClass().getClassLoader().loadClass(clazz);
    }
    public String getClazzUrl() {
        return clazz;
    }


}
