package com.gopi.selenium.testng;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.testng.annotations.*;

public class GUITest {
  private WebDriver se;

  @BeforeSuite
  @Parameters({"browser", "firefox", "firefoxDriver", "chrome", "chromeDriver"})
  public void setUp(String browser, String firefox, String firefoxDriver, String chrome, String chromeDriver) throws Exception {
      switch(browser) {
          case "firefox":
              FirefoxOptions firefoxOptions = new FirefoxOptions();
              firefoxOptions.setBinary(firefox);
              System.setProperty("webdriver.gecko.driver",firefoxDriver);
              se = new FirefoxDriver(firefoxOptions);
              break;
          case "chrome":
              ChromeOptions chromeOptions = new ChromeOptions();
              chromeOptions.setBinary(chrome);
              System.setProperty("webdriver.chrome.driver",chromeDriver);
              se = new ChromeDriver(chromeOptions);              
              break;
          default:
              throw new RuntimeException("Only Firefox and Chrome browsers are supported.");
      }
    se.manage().window().setSize(new Dimension(550, 691));
  }
  
  @Test
  @Parameters({"homePage"})
  public void checkLinks(String homePage) {
    se.get(homePage);
    se.findElement(By.linkText("Apache NetBeans")).click();
    se.findElement(By.linkText("Documentation")).click();
    se.findElement(By.linkText("Java SE Applications")).click();
  }

  @AfterSuite
  public void tearDown() {
    se.quit();
  }

}
