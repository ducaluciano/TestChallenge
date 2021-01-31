package Tests;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;


public class test {
    public String baseUrl = "http://demo.guru99.com/test/newtours/";
    String driverPath = "/Users/AiNt/chromedriver.exe";
    public WebDriver driver ; 
     
  @Test
  public void verifyHomepageTitle() {
       
      System.out.println("launching chrome browser"); 
      System.setProperty("webdriver.chrome.driver", driverPath);
      driver = new ChromeDriver();
      driver.get(baseUrl);
      String expectedTitle = "Welcome: Mercury Tours";
      String actualTitle = driver.getTitle();
      System.out.println(actualTitle);
      Assert.assertEquals(actualTitle, expectedTitle);
      driver.close();
  }
}