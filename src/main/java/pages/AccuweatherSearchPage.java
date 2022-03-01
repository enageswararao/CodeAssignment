package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccuweatherSearchPage {

    By iUnderstand = By.xpath("//div[text()='I Understand']");
    By searchByCityName = By.name("query");
    By searchResults = By.xpath("//div[@class='results-container']/div");
    By displayTemp= By.xpath("//span[@class='header-temp']");

    public void searchCityName(WebDriver driver, String cityName) {
        driver.findElement(searchByCityName).sendKeys(cityName);
    }

    public void selectCityName(WebDriver driver,String selectCityName) throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> elementLists = driver.findElements(searchResults);
        for (int i = 0; i < elementLists.size(); i++) {
            System.out.println(elementLists.get(i));
            if (elementLists.get(i).getText().equals(selectCityName)) {
                elementLists.get(i).click();
                break;
            }

        }
    }
    public String  getTemperature(WebDriver driver) throws InterruptedException {
        Thread.sleep(5000);
        return driver.findElement(displayTemp).getText();

    }
}

