package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
     WebDriver driver;
    public WebDriver launchingBrowser(String browser) {
        if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\drivers\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        driver = new ChromeDriver(chromeOptions);
         }
        else if(browser.equals("firefox")){
            //to do logic opening firefox
        }
        driver.manage().window().maximize();
        return driver;
    }
}
