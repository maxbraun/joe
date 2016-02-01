# Joe is a WebDriver Testing Library
While writing tests you're repeating yourself with writing logs and taking screenshots. This is where Joe comes up. 
Joe takes care about taking screenshots, when you click on an Element or when you navigate.
He also takes care ab creating a WebDriver. Just use the JUnit Rule Feature.
```java
    @Rule
    public WebDriverRule prod = new WebDriverRule();
```

```xml
    <dependency>
        <groupId>com.github.maxbraun.test</groupId>
        <artifactId>joe</artifactId>
        <version>0.1.0</version>
    </dependency>
```
