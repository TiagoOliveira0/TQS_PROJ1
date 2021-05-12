package com.demo.app;// Generated by Selenium IDE
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class AirQualityNowTest {
  private WebDriver driver;

  @BeforeEach
  public void setUp()
  {
    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    options.addArguments("--headless");
    driver = new ChromeDriver(options);
    driver.navigate().to("https://the-internet.herokuapp.com/login");
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(120, TimeUnit.MILLISECONDS);
  }

  @AfterEach
  public void tearDown(){
    if (driver != null) {
      driver.quit();
    }
  }

  @Test
  void airQualityNowTest() {
    driver.get("http://localhost:8080/");
    driver.manage().window().setSize(new Dimension(1848, 1053));
    driver.findElement(By.name("local")).click();
    driver.findElement(By.name("local")).sendKeys("Aveiro");
    driver.findElement(By.cssSelector(".btn")).click();
    long unixTime = Instant.now().getEpochSecond()*1000;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");
    String date = dateFormat.format(unixTime);
    assertThat(driver.findElement(By.cssSelector("td:nth-child(1)")).getText(), is(date));
  }
}
