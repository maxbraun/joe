# Joe is a WebDriver Testing Library
While writing tests you're repeating yourself with writing logs and taking screenshots. This is where Joe comes up. 
He also takes care ab creating a WebDriver. Just use the JUnit Rule Feature.
```java
    @Rule
    public WebDriverRule prod = new WebDriverRule();
```

```xml
    <dependency>
        <groupId>com.github.maxbraun.test</groupId>
        <artifactId>joe</artifactId>
        <version>0.2.0</version>
    </dependency>
```

##Logging and Screenshots
While you wrote tests you you took screenhots all the time? Joe takes care about this.
Joe takes a screenshot and writes a logfile after operations like clicking or navigating. You can find these files under ``target/test-screenshots/$test-class/$test-method/``. 

##Parameterized tests
Joe can work woith parameterized tests. When Working with these kind of Tests Joe will create seperate logging directories for this.
``target/test-screenshots/$test-class/$test-method/$parameters-name`` 
``$parameters-name`` is without further configuration the index of the test. You can also define another name for this by configuration a name in the ``@Parameters.Parameters (name="{0}")`` Annotation. ``{0}``will replace to parameters 1 ``{1}`` to parameter 2 and so on

##defensive dependencies
Joe knows a lot webdriver implementations. But doesn't force all these dependencies into your classpath. 
You define which webdriver implementation you Need add them to your classpath.
